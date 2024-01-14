package io.scala
package views

import io.scala.data.ScheduleInfo
import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.domaines.Break
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.utils.Screen

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom
import org.scalajs.dom.Element

object ScheduleState:
  val selectedTalk: Var[Option[Talk]] = Var(None)

case object ScheduleView extends SimpleView {
  val selectedDay: Var[ConfDay] = Var(ConfDay.Thursday)
  inline def width: Screen      = io.scala.utils.Screen.fromWidth(dom.window.innerWidth)
  val screenKind: Var[Screen]   = Var(width)

  lazy val globalHours: Div = div(
    span(
      "Hours",
      className := "subtitle"
    ),
    div(
      className := "hours",
      span(),
      span("Thursday"),
      span("Friday"),
      span("Opening"),
      Lexicon.Schedule.opening.map(_.render(span)),
      span("Launch"),
      Lexicon.Schedule.launch.map(_.render(span)),
      span("Closing"),
      Lexicon.Schedule.closing.map(_.render(span)),
      span("Community party"),
      Lexicon.Schedule.communityParty.render(span)
    )
  )

  def renderSchedule(eventsByDay: Map[ConfDay, List[Event]]) =
    div(
      className := "schedule",
      div(
        className := "tab",
        ConfDay.values.map { day =>
          div(
            button(
              className := "tablinks",
              onClick --> { _ => selectedDay.set(day) },
              h2(day.toString())
            ),
            Line(margin = 24, size = 3, kind = LineKind.Colored).amend(display <-- selectedDay.signal.map { d =>
              if d == day then "block"
              else "none"
            })
          )
        }
      ),
      ConfDay.values.map { day =>
        div(
          className := "tab-content",
          display <-- selectedDay.signal.map { d =>
            if d == day then "block"
            else "none"
          },
          child <-- selectedDay.signal.map {
            case i if i == day =>
              ScheduleDay(eventsByDay.get(day).getOrElse(Seq.empty)).body
            case _ => emptyNode
          }
        )
      }
    )

  def bodyContent(talks: List[Talk]): HtmlElement =
    val eventsByDay: Map[ConfDay, List[Event]] =
      ScheduleInfo.blankSchedule.groupBy {
        case t: Talk  => t.day.get
        case b: Break => b.day
      }

    sectionTag(
      className := "container",
      Title("Schedule"),
      p(
        "The schedule will be available soon.",
        className := "catch-phrase"
      ),
      Line(margin = 55),
      globalHours,
      Line(margin = 55),
      renderSchedule(eventsByDay),
      TalkModal(ScheduleState.selectedTalk),
      onClick.compose {
        _.withCurrentValueOf(ScheduleState.selectedTalk.signal)
          .collect { case (event, Some(_)) =>
            event.target match
              case e: Element if e.classList.contains("card-overlay") =>
                ScheduleState.selectedTalk.set(None)
          }
      } --> ()
    )

  def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(TalksInfo.allTalks)
    else
      bodyContent {
        TalksInfo.allTalks.filter { talk =>
          talk.speakers.forall(_.confirmed)
        }
      }

  override def title: String = "Schedule"
}
