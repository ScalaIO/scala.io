package io.scala.domaines

import java.time.LocalDate
import java.time.LocalTime

import com.raquo.laminar.api.L._

case class Meetup(
    basicInfo: Meetup.BasicInfo,
    talks: List[Meetup.Talk]
):
  def render: HtmlElement =
    div(
      className := "meetup",
      div(
        className := "basic-info",
        h2(basicInfo.name),
        p(basicInfo.date.toString),
        p(s"${basicInfo.startTime} - ${basicInfo.endTime}"),
        p(basicInfo.location),
        basicInfo.link.fold(span())(link => a(href := link, "More info"))
      ),
      div(
        className := "talks",
        talks.map { talk =>
          div(
            className := "talk",
            h3(talk.title),
            div(
              className := "speaker",
              talk.speaker.map { case (name, role) =>
                div(
                  className := "speaker",
                  p(name),
                  p(role)
                )
              }
            ),
            p(talk.description)
          )
        }
      )
    
    )

object Meetup:
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

  case class Talk(
      title: String,
      speaker: List[(String, String)],
      description: String
  )

  object Talk:
    def empty = Talk("", List.empty, "")
