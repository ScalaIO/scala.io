package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import java.time.LocalTime
import org.scalajs.dom.HTMLDivElement

import io.scala.models.{Act, Break, Special, Talk}
import io.scala.modules.syntax.*

case class ScheduleDay(eventsList: Map[LocalTime, Seq[Act]], startingTimes: Seq[LocalTime], rooms: Seq[Talk.Room]):
  def body =
    for
      time <- startingTimes
      timeSlot = div(className := "timeslot", time.render())
      events <- eventsList
      if events._1 == time
      cards = rooms.map(room =>
        events._2
          .find:
            case t: Talk               => t.info.room == room
            case _: Break | _: Special => true // ! Will cause problem for multi-track events
          .map(_.render)
          .getOrElse(div(className := "blank-card"))
      )
    yield timeSlot.amend(cards)

object ScheduleDay {
  def apply(events: Seq[Act]) =
    val scheduledEvents = events
      .filter:
        case t: Talk => t.time != null && t.info.room.show != null
        case e       => e.time != null
    val talksByTime: Map[LocalTime, Seq[Act]] = scheduledEvents.groupBy(_.time)
    val startingTimes = scheduledEvents
      .map(_.time)
      .distinct
      .sorted
    val rooms = events
      .foldLeft(Set.empty[Talk.Room]): (acc, event) =>
        event match
          case t: Talk => acc + t.info.room
          case _       => acc
      .toSeq
      .sortBy(_.show)

    new ScheduleDay(talksByTime, startingTimes, rooms)
}
