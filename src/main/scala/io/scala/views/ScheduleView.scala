package io.scala
package views

import io.scala.data.ScheduleInfo
import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.domaines.Break
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.dom.window

object ScheduleState:
  val selectedTalk: Var[Option[Talk]] = Var(None)

case object ScheduleView extends SimpleView {
  val selectedDay: Var[ConfDay] = Var(ConfDay.Thursday)

  lazy val globalHours: Div =
    def renderHours(name: String, hours: Time*) =
      div(
        className := "row",
        span(name),
        hours.map(_.render(span))
      )
    div(
      h2(
        "Hours",
        className := "content-title"
      ),
      div(
        className := "hours",
        div(
          className := "column-header",
          span(),
          span("Thursday"),
          span("Friday"),
        ),
        renderHours("Opening", Lexicon.Schedule.opening: _*),
        renderHours("First talks", Lexicon.Schedule.firstTalk: _*),
        renderHours("Lunch", Lexicon.Schedule.lunch: _*),
        renderHours("End of talks", Lexicon.Schedule.endOfTalks: _*),
        renderHours("Community party", Lexicon.Schedule.communityParty)
      )
    )

  def renderSmall(eventsByDay: Map[ConfDay, List[Event]]) =
    div(
      className := "schedule",
      div(
        className := "tab",
        ConfDay.values.map { day =>
          div(
            button(
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

  def renderLarge(eventsByDay: Map[ConfDay, List[Event]]) =
    div(
      className := "schedule",
      ConfDay.values.map { day =>
        div(
          className := "day",
          div(
            className := "tab",
            div(
              h2(day.toString()),
              Line(margin = 24, size = 3, kind = LineKind.Colored)
            )
          ),
          div(
            className := "content",
            div(
              className := "tab-content",
              ScheduleDay(eventsByDay.get(day).getOrElse(Seq.empty)).body
            )
          )
        )
      }
    )

  def renderSchedule(eventsByDay: Map[ConfDay, List[Event]]) =
    screenVar.signal.map {
      case Screen.Desktop => renderLarge(eventsByDay)
      case _              => renderSmall(eventsByDay)
    }

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
      child <-- renderSchedule(eventsByDay),
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
