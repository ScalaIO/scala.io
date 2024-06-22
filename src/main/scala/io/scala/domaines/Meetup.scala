package io.scala.domaines

import com.raquo.laminar.api.L.*
import java.time.LocalDateTime

import io.scala.data.parsers.Parsers
import io.scala.modules.elements.Cards
import io.scala.modules.elements.Links
import io.scala.modules.elements.Lists
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles

case class Meetup(
    basicInfo: Meetup.BasicInfo,
    talks: List[Meetup.Talk]
):
  def render: HtmlElement =
    Cards.withMedia(
      header = div(
        Titles.small(basicInfo.name),
        Paragraphs.description(s"${basicInfo.start} - ${basicInfo.end}"),
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
  given Ordering[Meetup] = Ordering.by(_.basicInfo.start)

  def apply(content: String): Meetup =
    Parsers.MeetupTalk.fromText(content)

  def empty = new Meetup(BasicInfo.empty, List.empty)

  case class BasicInfo(
      name: String,
      start: LocalDateTime,
      end: LocalDateTime,
      location: String,
      link: Option[String]
  )

  object BasicInfo:
    def empty = BasicInfo("", LocalDateTime.MIN, LocalDateTime.MIN, "", None)
    def apply(
        name: String,
        dateTime: (LocalDateTime, LocalDateTime),
        location: String,
        link: Option[String]
    ): BasicInfo =
      BasicInfo(name, dateTime._1, dateTime._2, location, link)

  case class Talk(
      title: String,
      speaker: List[(String, String)],
      description: String
  )

  object Talk:
    def empty = Talk("TBA", List.empty, "")
