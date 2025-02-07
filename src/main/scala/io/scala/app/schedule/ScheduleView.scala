package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

import io.scala.Lexicon
import io.scala.SchedulePage
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.modules.layout.Tabs
import io.scala.modules.syntax.*
import io.scala.views.ReactiveView

case object ScheduleView extends ReactiveView[SchedulePage] {
  val selectedDay: Var[DayOfWeek] = Var(DayOfWeek.FRIDAY)

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

  def render(events: Seq[(DayOfWeek, HtmlElement)]) =
    Tabs(
      events,
      _.toString,
      events.tail.head._1
    ).render.amend(className := "schedule")

  def body(signal: Signal[SchedulePage]): HtmlElement =
    sectionTag(
      className := "container schedule",
      Titles("Schedule"),
      div(),
      globalHours,
      Line(margin = 4, sizeUnit = "rem")
      // child <-- signal.map(_ => render(ScheduleInfo.allDays))
    )
}
