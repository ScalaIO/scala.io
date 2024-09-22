package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import java.time.DayOfWeek
import java.time.LocalTime
import scala.collection.SortedMap
import scala.collection.immutable.Queue

import io.scala.Lexicon
import io.scala.SchedulePage
import io.scala.data.ScheduleInfo
import io.scala.data.ScheduleInfo.maxEnd
import io.scala.data.ScheduleInfo.minStart
import io.scala.data.ScheduleInfo.pxByHour
import io.scala.models.Act
import io.scala.models.Durable
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.modules.layout.Tabs
import io.scala.modules.syntax.*
import io.scala.views.ReactiveView

case object ScheduleView extends ReactiveView[SchedulePage] {
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

  def renderSmall(eventsByDay: SortedMap[DayOfWeek, List[Act]]) =
    Tabs(
      eventsByDay.toSeq.map:
        case (day, events) => (day, ScheduleDay(events))
    ).render.amend(className := "schedule small")

  def computeTop(time: LocalTime, count: Int) =
    (time.toHour - minStart.toHour) * pxByHour

  // ? We suppose that the events are sorted by starting time
  // Will be removed as there is not enough space to display both days with multiple tracks. Keeping it for the timeline mechanism.
  def renderLarge(eventsByDay: SortedMap[DayOfWeek, Seq[Act]]) =
    div(
      className := "schedule large",
      div(),
      div(
        className := "tabs",
        eventsByDay.keySet.toSeq.map: day =>
          div(
            className := "tab",
            h2(day.toString()),
            Line(margin = 0.5, size = 3, kind = LineKind.Colored)
          ),
      ),
      div(
        className := "times",
        renderTimeline(eventsByDay)
      ),
      div(
        height := s"${(maxEnd.toHour - minStart.toHour) * (pxByHour + 40)}px",
        eventsByDay.keySet.toSeq.map: day =>
          eventsByDay
            .get(day)
            .fold(div()): events =>
              div(
                className := "content",
                events.foldLeft(Queue.empty[Div]): (acc, event) =>
                  acc :+ placeCard(event, acc.length, day)
              )
      )
    )

  def renderTimeline(eventsByDay: SortedMap[DayOfWeek, Seq[Act]]) =
    eventsByDay.values
      .flatMap(_.distinctBy(_.time).map(_.time))
      .foldLeft(Queue.empty[Element]): (acc, time) =>
        acc :+
          time
            .render()
            .amend(top := s"${computeTop(time, acc.length)}px")

  def placeCard(event: Act, index: Int, day: DayOfWeek): Div =
    val duration = event match
      case d: Durable => d.duration
      case _          => 15
    event.render.amend(
      className := "event",
      top       := s"${computeTop(event.time, index)}px",
      height    := s"${duration / 60.0 * pxByHour}px"
    )

  def body(signal: Signal[SchedulePage]): HtmlElement =
    val eventsByDay =
      signal.map: args =>
        if args.withDraft.exists(d => d) then SortedMap.from(ScheduleInfo.schedule.groupBy(_.day))
        else SortedMap.from(ScheduleInfo.blankSchedule.groupBy(_.day))

    sectionTag(
      className := "container",
      Titles("Schedule"),
      globalHours,
      Line(margin = 4, sizeUnit = "rem"),
      child <-- eventsByDay.map(renderSmall)
    )
}
