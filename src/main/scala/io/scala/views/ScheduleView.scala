package io.scala.views

import io.scala.Lexicon
import io.scala.data.ScheduleInfo
import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar

import com.raquo.laminar.api.L.{*, given}

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
      Title.small("Hours"),
      div(
        className := "hours",
        div(
          className := "column-header",
          span(),
          span("Thursday"),
          span("Friday")
        ),
        renderHours("Opening", Lexicon.Schedule.opening: _*),
        renderHours("First talk", Lexicon.Schedule.firstTalk: _*),
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
          children <-- selectedDay.signal.map {
            case i if i == day =>
              ScheduleDay(eventsByDay.get(day).getOrElse(Seq.empty)).body
            case _ => Seq(emptyNode)
          }
        )
      }
    )

  val spanVar: Var[HtmlElement] = Var(div())

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
            className := "tab-content",
            child <-- spanVar.signal.map {
              case span if day.ordinal == 0 => span
              case _                        => emptyNode
            },
            ScheduleDay(eventsByDay.get(day).getOrElse(Seq.empty)).body
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
      ScheduleInfo.blankSchedule
        .collect {
          case event if event.day != null => event
        }
        .groupBy { _.day }

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
      child <-- renderSchedule(eventsByDay)
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
