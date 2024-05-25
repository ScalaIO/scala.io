package io.scala.data

import io.scala.domaines.Meetup
import knockoff.Chunk
import knockoff.HeaderChunk
import org.scalajs.dom.console

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object MeetupsInfo:
  import Parsers.parser.*
  val frenchDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
  val frenchTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
  val sectionParser       = Parsers.headerN(3) ~ (Parsers.list ~ (Parsers.textBlock - Parsers.header).+).?

  def dateParser(str: String) =
    val dateTimeSep          = str.indexOf("|")
    val (date, time)         = str.splitAt(dateTimeSep)
    val startEndTimeSep      = time.indexOf("-")
    val (startTime, endTime) = time.splitAt(startEndTimeSep)
    (
      LocalDate.parse(date.trim, frenchDateFormatter),
      LocalTime.parse(startTime.drop(1).trim, frenchTimeFormatter),
      LocalTime.parse(endTime.drop(1).trim, frenchTimeFormatter)
    )

  def basicInfo =
    Parsers.headerN(1) ~ Parsers.list ~ Parsers.parser.textLine.+ <~ Parsers.emptyLine map {
      case HeaderChunk(_, name) ~ practicalInfo ~ description =>
        val infoMap                    = Parsers.listToMap(practicalInfo, ":")
        val (date, startTime, endTime) = dateParser(infoMap("Date"))
        Meetup.BasicInfo(
          name,
          date,
          startTime,
          endTime,
          infoMap("Location"),
          infoMap.get("Link"),
          description.map(_.content)
        )
      case chunk =>
        console.error(s"Error while parsing ${chunk._1._1.content}")
        Meetup.BasicInfo.empty
    }

  def talks =
    (Parsers.headerN(2) ~> sectionParser.+) ^^ { sections =>
      sections.map {
        case HeaderChunk(_, title) ~ Some(speakersLink ~ description) =>
          val speakers = speakersLink.map {
            _.content.dropRight(1) match
              case Parsers.linkRegex(name, link) => (name -> link)
          }

          Meetup.Talk(title, speakers, description.map(_.content).mkString("\n"))
        case _ ~ None =>
          Meetup.Talk.empty
        case chunk ~ _ =>
          console.error(s"Error while parsing ${chunk.content}")
          Meetup.Talk.empty
      }
    }

  def parseText(source: String) = Parsers.parser.parse(
    (basicInfo ~ talks) ^^ { case basicInfo ~ talks =>
      Meetup(basicInfo, talks)
    },
    source
  )
