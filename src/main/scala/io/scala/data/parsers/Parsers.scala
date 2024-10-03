package io.scala.data.parsers

import com.raquo.laminar.api.L.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
import knockoff.Chunk
import knockoff.ChunkParser
import knockoff.HeaderChunk
import org.scalajs.dom.console
import scala.collection.immutable.Queue

import io.scala.extensions.*
import io.scala.models.Meetup
import io.scala.models.Session
import io.scala.models.Social
import io.scala.models.Sponsor
import io.scala.modules.elements.Links

val linkExtractorRegex = """\(([^\(\)]+)\)""".r
def extractLink(link: String) =
  linkExtractorRegex.findFirstMatchIn(link).map(_.group(1))

object Parsers:
  val parser = new ChunkParser
  import parser.*

  val header    = parser.atxHeader <~ emptyLine.?
  val list      = parser.bulletItem.+ <~ emptyLine.?
  val textBlock = parser.textBlock <~ emptyLine.?
  val linkRegex = """\[([^\[\]]+)\]\s*\(([^\(\)]+)\)""".r
  val codeBlock =
    (parser.regex("""```\n""".r))
      ~> ((parser.textLine <~ parser.emptyLine.?) - parser.regex("```".r)).+
      <~ parser.regex("""```\n""".r)

  val description     = codeBlock <~ emptyLine.?
  def headerN(n: Int) = header.filter { case HeaderChunk(`n`, _) => true; case _ => false } <~ emptyLine.?

  def listToMap(list: List[Chunk], sep: String): Map[String, String] =
    list
      .map: chunk =>
        val sepIdx = chunk.content.indexOf(sep)
        val key    = chunk.content.take(sepIdx).trim
        val value  = chunk.content.drop(sepIdx + 1).trim
        key -> value
      .toMap

  object MeetupTalk:
    val talkParser = headerN(3) ~ (list ~ description).? <~ emptyLine.?

    def dateParser(str: String) =
      val startEnd = str.split("\\|")
      (
        LocalDateTime.parse(startEnd(0).trim, ISO_LOCAL_DATE_TIME),
        LocalDateTime.parse(startEnd(1).trim)
      )

    def basicInfo =
      headerN(1) ~ list map {
        case HeaderChunk(_, name) ~ practicalInfo =>
          val infoMap = listToMap(practicalInfo, ":")
          Meetup.BasicInfo(
            name,
            dateParser(infoMap("Date")),
            infoMap("Location"),
            infoMap.get("Link").flatMap(extractLink)
          )
        case chunk =>
          console.log(s"Error while parsing ${chunk._1.content}")
          Meetup.BasicInfo.empty
      }

    def talks =
      (headerN(2) ~> talkParser.+) ^^ { sections =>
        sections.map {
          case HeaderChunk(_, title) ~ Some(speakersLink ~ description) =>
            val speakers = speakersLink.map {
              _.content.dropRight(1) match
                case linkRegex(name, link) => (name -> link)
            }

            Meetup.Talk(title, speakers, description.map(_.content).mkString("\n"))
          case _ ~ None =>
            Meetup.Talk.empty
          case chunk ~ _ =>
            console.log(s"Error while parsing ${chunk.content}")
            Meetup.Talk.empty
        }
      }

    def fromText(source: String): Meetup = parser
      .parse(
        (basicInfo ~ talks) map { case basicInfo ~ talks =>
          Meetup(basicInfo, talks)
        },
        source
      )
      .getOrElse(Meetup.empty)

  object ConferenceSession:
    def basicInfo =
      headerN(1) ~ list map {
        case HeaderChunk(_, title) ~ practicalInfo =>
          val infoMap         = listToMap(practicalInfo, ":")
          val kind            = Session.Kind.valueOf(infoMap("Kind"))
          val slug            = infoMap("Slug")
          val category        = infoMap("Category")
          val confirmed       = infoMap.get("confirmed").map(_.toBoolean).getOrElse(false)
          val room            = infoMap.getOrElse("Room", null).nullMap(Session.Room(_))
          val slides          = Session.BasicInfo.Slides(infoMap.get("Slides"))
          val replay          = Session.BasicInfo.Replay(infoMap.get("Replay"))
          val dateTimesOption = infoMap.getOrElse("DateTime", null).nullMap(parseDateTime)
          val baseInfo =
            Session.BasicInfo(title, slug, kind, category, confirmed, null, room, slides, replay)
          dateTimesOption.nullFold(List(baseInfo)): dateTimes =>
            dateTimes.zipWithIndex.map {
              case (dateTime, _) if dateTimes.size == 1 => baseInfo.copy(dateTime = dateTime)
              case (dateTime, idx) =>
                baseInfo.copy(title = s"${baseInfo.title} ${idx + 1}/${dateTimes.size}", dateTime = dateTime)
            }

        case chunk =>
          console.log(s"Error while parsing ${chunk._1.content}")
          List(Session.BasicInfo.empty)
      }

    def parseDateTime(str: String): List[LocalDateTime] =
      str.split("\\|").map(LocalDateTime.parse(_, ISO_LOCAL_DATE_TIME)).toList

    def abstractParser = headerN(2) ~> description map (_.map(_.content).mkString("\n"))

    def speakerParser =
      headerN(3) ~ list ~ headerN(4) ~ list ~ headerN(4) ~ description.? map {
        case HeaderChunk(_, name) ~ info ~ HeaderChunk(4, "Links") ~ socials ~ HeaderChunk(4, "Bio") ~ bio =>
          val infoMap = listToMap(info, ":")
          val socialsMap =
            socials.flatMap { chunk =>
              linkRegex.findFirstMatchIn(chunk.content).map { link =>
                link.group(1) -> link.group(2)
              }
            }
          Session.Speaker(
            name,
            infoMap("photoRelPath"),
            infoMap("job"),
            socialsMap.map { case (name, link) =>
              Social(Social.Kind.valueOf(name), link.stripSuffix("/"))
            }.toList,
            bio.getOrElse(List()).map(_.content).mkString("\n")
          )
        case chunk =>
          console.log(s"Error while parsing ${chunk._1._1._1._1._1.content}")
          Session.Speaker.empty
      }

    def speakersParser = (headerN(2) ~> speakerParser.+)

    val sessionParser = basicInfo ~ abstractParser ~ speakersParser map { case basicInfo ~ description ~ speakers =>
      basicInfo.map(Session(_, description, speakers))
    }

    def fromText(source: String): List[Session] = parser.parse(sessionParser, source) match
      case Success(talk, _) => talk
      case failure          => List(Session.empty)

  object Description:
    val talk =
      parser.phrase((header.? ~ textBlock).+)

    def parseTalk(md: String) =
      parser.parse(talk, md) match
        case Success(result, next) =>
          result.map:
            case header ~ textBlock =>
              val initialQueue: Queue[HtmlElement] =
                Queue.from { header.map { case HeaderChunk(_, content) => h4(content) } }

              val text = textBlock.content
              val withLinks = linkRegex
                .findAllMatchIn(text)
                .foldLeft((initialQueue, 0)):
                  case ((queue, start), next) =>
                    (
                      queue
                        .enqueue(span(text.substring(start, next.start)))
                        .enqueue(
                          Links.highlighted(href := next.group(2), next.group(1), aria.label := next.group(2))
                        ),
                      next.end
                    )
              withLinks._1.enqueue(span(text.substring(withLinks._2)))
        case _ =>
          List(Queue(p("To be announced")))

  object ConferenceSponsor:
    val sponsor     = headerN(2) ~> bulletItem.+ ~ textBlock.?
    val sponsorFile = headerN(1) ~> sponsor.+

    def fromText(source: String): List[Sponsor] =
      parser
        .parse(sponsorFile, source)
        .map:
          _.map:
            case List(name, link, logo, rank) ~ dimensions =>
              val Array(colSpan, rowSpan) = dimensions
                .map {
                  _.content.split(":").map(_.trim.toInt)
                }
                .getOrElse(Array(1, 1))

              Sponsor(
                name.content.trim,
                link.content.trim,
                logo.content.trim,
                Sponsor.Rank.valueOf(rank.content.trim),
                colSpan,
                rowSpan
              )
            case _ => Sponsor.empty
        .getOrElse(List())
