package io.scala.views

import io.scala.Lexicon
import io.scala.data.ScheduleInfo
import io.scala.data.ScheduleInfo.maxEnd
import io.scala.data.ScheduleInfo.minStart
import io.scala.data.ScheduleInfo.pxByHour
import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.domaines.Break.Kind.max
import io.scala.modules.*
import io.scala.modules.elements.*
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.console
import scala.collection.immutable.Queue
import scala.collection.mutable
import io.scala.domaines.{ConfDay, Act, Break, Durable, Time}

case object ScheduleView extends SimpleViewWithDraft {
  val selectedDay: Var[ConfDay] = Var(ConfDay.Thursday)

  lazy val globalHours: Div =
    def renderHours(name: String, hours: Seq[Time]) =
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
        renderHours("Opening", Lexicon.Schedule.opening),
        renderHours("First talk", Lexicon.Schedule.firstTalk),
        renderHours("Lunch", Lexicon.Schedule.lunch),
        renderHours("End of talks", Lexicon.Schedule.endOfTalks),
        renderHours("Community party", Lexicon.Schedule.communityParty)
      )
    )

  def renderSmall(eventsByDay: Map[ConfDay, List[Act]]) =
    div(
      className := "schedule small",
      div(
        className := "tabs",
        ConfDay.values.map { day =>
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
        ConfDay.values.map { day =>
          div(
            className := "content",
            display <-- selectedDay.signal.map { d =>
              if d == day then "flex"
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
    )

  def computeTop(event: Act, count: Int, day: ConfDay) =
    val base = (event.start.toHour - minStart.toHour) * pxByHour
    if day == ConfDay.Thursday then base + (count + 1) * 32
    else base + count * 32

  // ? We suppose that the events are sorted by starting time
  def renderLarge(eventsByDay: Map[ConfDay, List[Act]]) =
    div(
      className := "schedule large",
      div(),
      div(
        className := "tabs",
        ConfDay.values.map: day =>
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
        height := s"${(maxEnd.h - minStart.h) * (pxByHour + 40)}px",
        ConfDay.values.map: day =>
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

  def renderTimeline(eventsByDay: Map[ConfDay, Seq[Act]]) =
    val inserted = mutable.Set.empty[Time]
    ConfDay.values.flatMap: day => // ? Needed for each day to advance independently in the timeline
      eventsByDay
        .get(day)
        .getOrElse(Seq())
        .foldLeft(Queue.empty[Element]): (acc, event) =>
          event match
            case b: Break if b.kind != Break.Kind.Lunch => acc :+ span()
            case e: Act if inserted.contains(e.start) => acc :+ span()
            case _ =>
              inserted.add(event.start)
              acc :+
                event.start
                  .render()
                  .amend(top := s"${computeTop(event, acc.length, day)}px")

  def placeCard(event: Act, index: Int, day: ConfDay): Div =
    val duration = event match
      case d: Durable => d.duration
      case _          => 15
    event.render.amend(
      className := "event",
      top       := s"${computeTop(event, index, day)}px",
      height    := s"${duration / 60.0 * pxByHour}px"
    )

  def renderSchedule(eventsByDay: Map[ConfDay, List[Act]]) =
    screenVar.signal.map {
      case Screen.Desktop => renderLarge(eventsByDay)
      case _              => renderSmall(eventsByDay)
    }

  def bodyContent(eventsByDay: Map[ConfDay, List[Act]]): HtmlElement =
    sectionTag(
      className := "container",
      Title("Schedule"),
      globalHours,
      Line(margin = 55),
      child <-- renderSchedule(eventsByDay)
    )

  def body(withDraft: Boolean): HtmlElement =
    val eventsByDay: Map[ConfDay, List[Act]] =
      ScheduleInfo.schedule
        .filter(_.day != null)
        .groupBy { _.day }
        .map((k, v) => (k, v.sortBy(_.start)))
    bodyContent(eventsByDay)
}
