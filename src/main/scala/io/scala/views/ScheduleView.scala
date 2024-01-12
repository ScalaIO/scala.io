package io.scala
package views

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import io.scala.data.ScheduleInfo
import io.scala.data.TalksInfo
import io.scala.domaines.Break
import io.scala.domaines.*
import io.scala.modules.*
import io.scala.modules.elements.*
import org.scalajs.dom.Element

object ScheduleState:
  val selectedTalk: Var[Option[Talk]] = Var(None)

case object ScheduleView extends View {
  val selectedDay: Var[ConfDay] = Var(ConfDay.Thursday)

  def bodyContent(talks: List[Talk]): HtmlElement =
    val talksByDay: Map[ConfDay, List[Event]] = 
      ScheduleInfo.blankSchedule.groupBy{
        case t: Talk => t.day.get
        case b: Break => b.day
      }
    var days: Map[ConfDay, ScheduleDay] = Map()
    sectionTag(
      className := "container",
      Title("Schedule"),
      p(
        "The schedule will be available soon.",
        className := "catch-phrase"
      ),
      Line(margin = 55),
      div(
        h2("To be scheduled"),
        div(
          className := "card-container unassigned",
          talks.map(TalkCard(_))
        )
      ),
      Line(margin = 55),
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
              child <-- selectedDay.signal.map { d =>
                if d == day then Line(margin = 24, size = 3, kind = LineKind.Colored)
                else emptyNode
              }
            )
          }
        ),
        ConfDay.values.map { day =>
          div(
            className := "tab-content",
            display <-- selectedDay.signal.map { d =>
              if d == day then "flex"
              else "none"
            },
            child <-- selectedDay.signal.map {
              case i if i == day =>
                days.get(day).map(_.body).getOrElse {
                  val newDay = ScheduleDay(talksByDay.get(day).getOrElse(Seq.empty))
                  days += (day -> newDay)
                  newDay.body
                }
              case _ => emptyNode
            }
          )
        }
      ),
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
}
