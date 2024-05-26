import io.scala.data.TalksInfo
import io.scala.domaines.ConfDay
import io.scala.domaines.Social
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.domaines.Time
import io.scala.modules.elements.SelfHosted
import io.scala.modules.elements.Slides
import io.scala.modules.elements.WebHosted

import java.nio.file.Files
import java.nio.file.Paths
import io.scala.domaines.TalkInfo

val outPath =
  Paths.get("/Users/lucas.nouguier/Projects/scala.io/public/conferences/nantes2024")

object TalkToMd:
  def dayToMd(day: ConfDay): String =
    day match
      case ConfDay.Thursday => "15-02-2024"
      case ConfDay.Friday   => "16-02-2024"

  def timeToMd(time: Time): String =
    s"${time.h}:${time.m}"

  def slidesToMd(slides: Option[Slides]): String =
    slides match
      case Some(WebHosted(url)) => s"$url"
      case Some(self: SelfHosted) =>
        s"https://scala.io${self.url}"
      case None => ""

  def replayToMd(replay: Option[String]): String =
    replay.getOrElse("")

  def speakersToMd(speakers: List[Speaker]): String =
    def socialsToMd(socials: List[Social]): String =
      socials
        .map: social =>
          s"- [${social.kind}](${social.url})"
        .mkString("\n")
    speakers
      .map: speaker =>
        s"""|### ${speaker.name}
            |
            |- photoRelPath: ${speaker.photoPath}
            |- job: ${speaker.job} @ ${speaker.company}
            |- confirmed
            |
            |#### Links
            |
            |${socialsToMd(speaker.socials)}
            |
            |#### Bio
            |
            |${speaker.description}""".stripMargin
      .mkString("\n")

  def talkToMd(talk: Talk) =
    val talkPathOut = outPath.resolve(s"${talk.slug}.md")
    val content =
      s"""|# ${talk.title}
          |
          |- kind: ${talk.kind}
          |- category: ${talk.category}
          |- dateTime: ${dayToMd(talk.day)} | ${timeToMd(talk.start)}
          |- room: ${talk.room.ordinal}
          |- slides: ${slidesToMd(talk.slides)}
          |- replay: ${talk.replay.getOrElse("")}
          |
          |## Abstract
          |
          |${talk.description.linesIterator.drop(2).mkString("\n")}
          |
          |## Speakers
          |
          |${speakersToMd(talk.speakers)}
          |""".stripMargin
    Files.write(talkPathOut, content.getBytes)

@main def migrateTalksToMd =
  TalksInfo.allTalks.foreach(TalkToMd.talkToMd)
