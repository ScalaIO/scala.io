package io.scala.domaines

import io.scala.modules.elements.Cards
import io.scala.modules.elements.Links
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles

import com.raquo.laminar.api.L._
import java.time.LocalDate
import java.time.LocalTime
import io.scala.modules.elements.Lists
import io.scala.data.parsers.Parsers

case class Meetup(
    basicInfo: Meetup.BasicInfo,
    talks: List[Meetup.Talk]
):
  def render: HtmlElement =
    Cards.withMedia(
      header = div(
        Titles.small(basicInfo.name),
        Paragraphs.description(s"${basicInfo.date} @ ${basicInfo.startTime} - ${basicInfo.endTime}"),
        Paragraphs.description(basicInfo.location)
      ),
      body = Lists.innerDiscs(
        talks.map { talk =>
          li(
            Titles.paragraph(talk.title),
            Lists.flat(
              talk.speaker.map { case (name, link) =>
                li(Links.flat(href := link, name))
              }*
            )
          )
        }*
      ),
      footer = basicInfo.link.fold(div())(link => Links.highlighted(href := link, "Learn more"))
    )

object Meetup:
  given Ordering[Meetup] = Ordering.by(_.basicInfo.date)

  def apply(content: String): Meetup =
    Parsers.MeetupTalk.fromText(content)

  def empty = new Meetup(BasicInfo.empty, List.empty)

  case class BasicInfo(
      name: String,
      date: LocalDate,
      startTime: LocalTime,
      endTime: LocalTime,
      location: String,
      link: Option[String],
      description: List[String]
  )

  object BasicInfo:
    def empty = BasicInfo("", LocalDate.MIN, LocalTime.MIN, LocalTime.MIN, "", None, List.empty)
    def apply(name: String, dateTime: (LocalDate, LocalTime, LocalTime), location: String, link: Option[String], description: List[String]): BasicInfo =
      BasicInfo(name, dateTime._1, dateTime._2, dateTime._3, location, link, description)

  case class Talk(
      title: String,
      speaker: List[(String, String)],
      description: String
  )

  object Talk:
    def empty = Talk("TBA", List.empty, "")
