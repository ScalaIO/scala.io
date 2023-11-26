package io.scala.views

import io.scala.Lexicon
import io.scala.domaines.ConfDay
import io.scala.domaines.Talk
import io.scala.modules.{ClassyButton, Line, SpeakerCard, SpeakerModal, Title}
import io.scala.modules.LineKind
import io.scala.modules.ScheduleDay

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.{*, given}
import io.scala.domaines.ConfDay
import io.scala.domaines.Speaker

case object Schedule extends View {
  private val selectedDay: Var[Option[String]] = Var(Lexicon.Schedule.days.headOption.map(_._1))
  private val selectedTalk: Var[Option[Talk]]  = Var(None)
  private val tabLinkName: String              = "tablinks"
  private val talksByDay: Map[Option[ConfDay], Seq[Speaker]] =
    Lexicon.Speakers.speakers.groupBy(_.talk.day)
  private var days: Map[ConfDay, ScheduleDay] = Map()

  override def body: HtmlElement = div(
    className := "container",
    Title("Schedule"),
    p(
      "The schedule will be available soon.",
      className := "catch-phrase"
    ),
    ClassyButton(Lexicon.Speakers.callToAction),
    Line(margin = 55),
    div(
      className := "schedule__tab",
      Lexicon.Schedule.days.map { (id, day) =>
        div(
          button(
            idAttr    := id,
            className := tabLinkName,
            onClick --> { _ => changeDay(id) },
            h2(day.toString())
          ),
          child <-- selectedDay.signal.map {
            case Some(i) if i == id => Line(margin = 24, size = 3, kind = LineKind.Colored)
            case _                  => emptyNode
          }
        )
      }
    ),
    Lexicon.Schedule.days.map { (id, day) =>
      div(
        idAttr    := id,
        className := "schedule__tabcontent",
        display <-- selectedDay.signal.map {
          case Some(i) if i == id => "block"
          case _                  => "none"
        },
        child <-- selectedDay.signal.map {
          case Some(i) if i == id =>
            days.get(day).map(_.body).getOrElse{
              val newDay = ScheduleDay(talksByDay.get(Some(day)).getOrElse(Seq.empty))
              days = days + (day -> newDay)
              newDay.body
            }
          case _ => emptyNode
        }
      )
    }
  )

  def changeDay(id: String) = selectedDay.set(Some(id))

}
