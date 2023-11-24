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

  override def body: HtmlElement = div(
    className := "container",
    Title("Schedule"),
    p(
      "The schedule will be available soon.",
      className := "catch-phrase"
    ),
    ClassyButton(Lexicon.Speakers.callToAction),
    Line(padding = 55),
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
            case Some(i) if i == id => Line(padding = 50, size = 3, kind = LineKind.Colored)
            case _                  => emptyNode
          }
        )
      }
    ),
    Lexicon.Schedule.days.map { (id, day) =>
      div(
        idAttr    := id,
        className := "schedule__tabcontent",
        styleAttr <-- selectedDay.signal.map {
          case Some(i) if i == id => "display: block;"
          case _                  => "display: none;"
        },
        child <-- selectedDay.signal.map {
          case Some(i) if i == id =>
            ScheduleDay(talksByDay.get(Some(day)).getOrElse(Seq.empty))
          case _ => emptyNode
        }
      )
    }
  )

  def changeDay(id: String) = selectedDay.set(Some(id))

}
