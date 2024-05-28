package io.scala.data.parsers

import io.scala.domaines.Meetup
import io.scala.domaines.Social
import io.scala.domaines.Sponsor
import io.scala.domaines.Talk
import io.scala.modules.elements.Links

import com.raquo.laminar.api.L.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import knockoff.Chunk
import knockoff.ChunkParser
import knockoff.HeaderChunk
import org.scalajs.dom.console
import scala.collection.immutable.Queue

object Parsers:
  val parser = new ChunkParser
  import parser.*

  val header    = parser.atxHeader <~ emptyLine.?
  val list      = parser.bulletItem.* <~ emptyLine.?
  val textBlock = parser.textBlock <~ emptyLine.?
  val linkRegex = """\[([^\[\]]+)\]\s*\(([^\(\)]+)\)""".r

  val description     = (textBlock - headerN(1) - headerN(2)).+ <~ emptyLine.?
  def headerN(n: Int) = header.filter { case HeaderChunk(`n`, _) => true; case _ => false } <~ emptyLine.?

  def listToMap(list: List[Chunk], sep: String): Map[String, String] =
    list
      .map: chunk =>
        val sepIdx = chunk.content.indexOf(sep)
        val key    = chunk.content.take(sepIdx).trim
        val value  = chunk.content.drop(sepIdx + 1).trim
        key -> value
      .toMap

  val dateFormatter     = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  val timeFormatter     = DateTimeFormatter.ofPattern("HH:mm")
  val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

  object MeetupTalk:
    private val talkParser = headerN(3) ~ (list ~ description).?

    private def dateParser(str: String) =
      val dateTimeSep          = str.indexOf("|")
      val (date, time)         = str.splitAt(dateTimeSep)
      val startEndTimeSep      = time.indexOf("-")
      val (startTime, endTime) = time.splitAt(startEndTimeSep)
      (
        LocalDate.parse(date.trim, dateFormatter),
        LocalTime.parse(startTime.drop(1).trim, timeFormatter),
        LocalTime.parse(endTime.drop(1).trim, timeFormatter)
      )

    private def basicInfo =
      headerN(1) ~ list ~ description map {
        case HeaderChunk(_, name) ~ practicalInfo ~ description =>
          val infoMap = listToMap(practicalInfo, ":")
          Meetup.BasicInfo(
            name,
            dateParser(infoMap("Date")),
            infoMap("Location"),
            infoMap.get("Link"),
            description.map(_.content)
          )
        case chunk =>
          console.error(s"Error while parsing ${chunk._1._1.content}")
          Meetup.BasicInfo.empty
      }

    private def talks =
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
            console.error(s"Error while parsing ${chunk.content}")
            Meetup.Talk.empty
        }
      }

    def fromText(source: String): Meetup = parser
      .parse(
        (basicInfo ~ talks) ^^ { case basicInfo ~ talks =>
          Meetup(basicInfo, talks)
        },
        source
      )
      .getOrElse(Meetup.empty)

  object ConferenceTalk:
    private def basicInfo =
      headerN(1) ~ list map {
        case HeaderChunk(_, name) ~ practicalInfo =>
          val infoMap = listToMap(practicalInfo, ":")
          Talk.BasicInfo(
            name,
            infoMap("Slug"),
            Talk.Kind.valueOf(infoMap("Kind")),
            infoMap("Category"),
            LocalDateTime.parse(infoMap("DateTime"), dateTimeFormatter),
            Talk.Room(infoMap.get("Room").getOrElse(null)),
            infoMap.get("Slides"),
            infoMap.get("Replay")
          )
        case chunk =>
          console.error(s"Error while parsing ${chunk._1.content}")
          Talk.BasicInfo.empty
      }

    private def abstractParser =
      headerN(2) ~> description map (_.map(_.content).mkString("\n"))

    private def speakerParser =
      headerN(3) ~ list ~ headerN(4) ~ list ~ headerN(4) ~ description map {
        case HeaderChunk(_, name) ~ info ~ HeaderChunk(4, "Links") ~ socials ~ HeaderChunk(4, "Bio") ~ bio =>
          val infoMap = listToMap(info, ":")
          val socialsMap =
            socials.flatMap { chunk =>
              linkRegex.findFirstMatchIn(chunk.content).map { link =>
                link.group(1) -> link.group(2)
              }
            }
          Talk.Speaker(
            name,
            infoMap("photoRelPath"),
            infoMap("job"),
            infoMap.get("confirmed").map(_.toBoolean).getOrElse(false),
            socialsMap.map { case (name, link) =>
              Social(Social.Kind.valueOf(name), link)
            }.toList,
            bio.map(_.content).mkString("\n")
          )
        case chunk =>
          console.error(s"Error while parsing ${chunk._1._1._1._1._1.content}")
          Talk.Speaker.empty
      }

    val talkParser = basicInfo ~ abstractParser ~ (headerN(2) ~> speakerParser.+) map {
      case basicInfo ~ description ~ speakers =>
        Talk(basicInfo, description, speakers)
    }

    def fromText(source: String): Talk = parser.parse(talkParser, source) match
      case Success(talk, _) => talk
      case failure          => Talk.empty

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
