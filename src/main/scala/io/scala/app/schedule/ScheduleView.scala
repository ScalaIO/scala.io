package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import java.time.DayOfWeek
import java.time.LocalTime
import scala.collection.immutable.Queue
import scala.collection.mutable

import io.scala.Lexicon
import io.scala.data.ScheduleInfo
import io.scala.data.ScheduleInfo.maxEnd
import io.scala.data.ScheduleInfo.minStart
import io.scala.data.ScheduleInfo.pxByHour
import io.scala.models.{Act, Break, Durable}
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.modules.syntax.*
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar
import io.scala.views.SimpleViewWithDraft

case object ScheduleView extends SimpleViewWithDraft {
  val selectedDay: Var[DayOfWeek] = Var(DayOfWeek.THURSDAY)

  lazy val globalHours: Div =
    def renderHours(name: String, hours: Seq[LocalTime]) =
      div(
        className := "row",
        span(name),
        hours.map(_.render())
      )
    div(
      Titles.medium("Hours"),
      div(
        className := "hours",
        div(
          className := "column-header",
          span(),
          span("Thursday"),
          span("Friday")
        ),
        renderHours("Opening", Lexicon.Schedule.opening),
        renderHours("First talk", Lexicon.Schedule.firstTalk),
        renderHours("Lunch", Lexicon.Schedule.lunch),
        renderHours("End of talks", Lexicon.Schedule.endOfTalks),
        renderHours("Community party", Lexicon.Schedule.communityParty)
      )
    )

  def renderSmall(eventsByDay: Map[DayOfWeek, List[Act]], sortedDays: Seq[DayOfWeek]) =
    div(
      className := "schedule small",
      div(
        className := "tabs",
        sortedDays.map { day =>
          div(
            button(
              onClick --> { _ => selectedDay.set(day) },
              h2(day.toString())
            ),
            Line(margin = 8, size = 3, kind = LineKind.Colored).amend(display <-- selectedDay.signal.map { d =>
              if d == day then "flex"
              else "none"
            })
          )
        }
      ),
      div(
        sortedDays.map { day =>
          div(
            className := "content",
            display <-- selectedDay.signal.map { d =>
              if d == day then "flex"
              else "none"
            },
            children <-- selectedDay.signal.map {
              case d if d == day =>
                ScheduleDay(eventsByDay.get(day).getOrElse(Seq.empty)).body
              case _ => Seq(emptyNode)
            }
          )
        }
      )
    )

  def computeTop(event: Act, count: Int, day: DayOfWeek) =
    val base = (event.time.get.toHour - minStart.toHour) * pxByHour
    if day == DayOfWeek.THURSDAY then base + (count + 1) * 32
    else base + count * 32

  // ? We suppose that the events are sorted by starting time
  def renderLarge(eventsByDay: Map[DayOfWeek, List[Act]], sortedDays: Seq[DayOfWeek]) =
    div(
      className := "schedule large",
      div(),
      div(
        className := "tabs",
        sortedDays.map: day =>
          div(
            className := "tab",
            h2(day.toString()),
            Line(margin = 8, size = 3, kind = LineKind.Colored)
          ),
      ),
      div(
        className := "times",
        renderTimeline(eventsByDay)
      ),
      div(
        height := s"${(maxEnd.toHour - minStart.toHour) * (pxByHour + 40)}px",
        sortedDays.map: day =>
          eventsByDay.get(day) match
            case None => div()
            case Some(events) =>
              div(
                className := "content",
                events.foldLeft(Queue.empty[Div]): (acc, event) =>
                  acc :+ placeCard(event, acc.length, day)
              )
      )
    )

  def renderTimeline(eventsByDay: Map[DayOfWeek, Seq[Act]]) =
    val inserted = mutable.Set.empty[LocalTime]
    eventsByDay.keySet.toSeq.flatMap: day => // ? Needed for each day to advance independently in the timeline
      eventsByDay
        .get(day)
        .getOrElse(Seq())
        .foldLeft(Queue.empty[Element]): (acc, event) =>
          event match
            case b: Break if b.kind != Break.Kind.Lunch => acc :+ span()
            case e: Act if inserted.contains(e.time.get)    => acc :+ span()
            case _ =>
              inserted.add(event.time.get)
              acc :+
                event.time.get
                  .render()
                  .amend(top := s"${computeTop(event, acc.length, day)}px")

  def placeCard(event: Act, index: Int, day: DayOfWeek): Div =
    val duration = event match
      case d: Durable => d.duration
      case _          => 15
    event.render.amend(
      className := "event",
      top       := s"${computeTop(event, index, day)}px",
      height    := s"${duration / 60.0 * pxByHour}px"
    )

  def renderSchedule(eventsByDay: Map[DayOfWeek, List[Act]]) =
    val sortedDays = eventsByDay.keys.toSeq.sorted
    screenVar.signal.map {
      case Screen.Desktop => renderLarge(eventsByDay, sortedDays)
      case _              => renderSmall(eventsByDay, sortedDays)
    }

  def body(withDraft: Boolean, conference: Option[String]): HtmlElement =
    val eventsByDay: Map[DayOfWeek, List[Act]] =
      ScheduleInfo.schedule
        .filter(_.day != null)
        .groupBy { _.day }
        .map((k, v) => (k.get, v.sortBy(_.time.get)))

    sectionTag(
      className := "container",
      Titles("Schedule"),
      globalHours,
      Line(margin = 55),
      child <-- renderSchedule(eventsByDay)
    )
}
