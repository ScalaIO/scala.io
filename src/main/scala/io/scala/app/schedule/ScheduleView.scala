package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.Lexicon
import io.scala.SchedulePage
import io.scala.data.ScheduleInfo
import io.scala.data.ScheduleInfo.minStart
import io.scala.data.ScheduleInfo.pxByHour
import io.scala.models.Act
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.modules.layout.Tabs
import io.scala.modules.syntax.*
import io.scala.views.ReactiveView

import java.time.DayOfWeek
import java.time.LocalTime
import scala.collection.SortedMap

case object ScheduleView extends ReactiveView[SchedulePage] {
  val selectedDay: Var[DayOfWeek] = Var(DayOfWeek.THURSDAY)

  lazy val globalHours: Seq[HtmlElement] =
    def renderHours(name: String, hours: Seq[LocalTime]) =
      div(
        className := "row",
        span(name),
        hours.map(_.render())
      )
    Seq(
      Titles.medium("Hours"),
      div(
        className := "hours",
        div(
          className := "row",
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

  def render(eventsByDay: SortedMap[DayOfWeek, List[Act]]) =
    val events = eventsByDay.toSeq
    Tabs(
      events.map:
        case (day, events) => (day, ScheduleDay(events)),
      _.toString,
      events.tail.head._1
    ).render.amend(className := "schedule")

  def computeTop(time: LocalTime, count: Int) =
    (time.toHour - minStart.toHour) * pxByHour

  def body(signal: Signal[SchedulePage]): HtmlElement =
    sectionTag(
      className := "container",
      Titles("Schedule"),
      div(),
      globalHours,
      Line(margin = 4, sizeUnit = "rem"),
      child <-- signal.map(_ => render(SortedMap.from(ScheduleInfo.schedule.groupBy(_.startingDay))))
    )
}
