package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import io.scala.models.Act
import io.scala.models.Break
import io.scala.models.CompleteAct
import io.scala.models.Special
import io.scala.models.Talk
import io.scala.modules.syntax.*
import org.scalajs.dom.HTMLDivElement

import java.time.LocalTime

case class ScheduleDay(eventsList: Map[LocalTime, Seq[Act]], startingTimes: Seq[LocalTime], rooms: Seq[Talk.Room]):
  def body =
    for
      time   <- startingTimes
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
    yield div(className := "timeslot", time.render(), cards)

object ScheduleDay {
  def apply(events: Seq[CompleteAct]) =
    val talksByTime: Map[LocalTime, Seq[Act]] = events.groupBy(_.time)
    val startingTimes                         = events.map(_.time).distinct.sorted
    val rooms: Seq[Talk.Room] = events.collect{ case t: Talk if t.info.room != null => t.info.room.nn }.distinct.sorted

    new ScheduleDay(talksByTime, startingTimes, rooms)
}
