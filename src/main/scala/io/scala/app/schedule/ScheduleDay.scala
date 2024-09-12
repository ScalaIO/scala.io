package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import java.time.LocalTime
import org.scalajs.dom.HTMLDivElement

import io.scala.models.Act
import io.scala.models.Break
import io.scala.models.Session
import io.scala.models.Special
import io.scala.modules.syntax.*

case class ScheduleDay(eventsList: Map[LocalTime, Seq[Act]], startingTimes: Seq[LocalTime], rooms: Seq[Session.Room]):
  def body =
    for
      time   <- startingTimes
      events <- eventsList
      if events._1 == time
      cards = rooms.map(room =>
        events._2
          .find:
            case t: Session            => t.info.room == room
            case _: Break | _: Special => true // ! Will cause problem for multi-track events
          .map(_.render)
          .getOrElse(div(className := "blank-card"))
      )
    yield div(className := "timeslot", time.render(), cards)

object ScheduleDay {
  def apply(events: Seq[Act]) =
    val talksByTime: Map[LocalTime, Seq[Act]] = events.groupBy(_.time)
    val startingTimes                         = events.map(_.time).distinct.sorted
    val rooms: Seq[Session.Room] =
      events.collect { case t: Session if t.info.room != null => t.info.room.nn }.distinct.sorted

    new ScheduleDay(talksByTime, startingTimes, rooms)
}
