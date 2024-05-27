package io.scala.modules

import io.scala.domaines.*
import io.scala.modules.syntax.*
import com.raquo.laminar.api.L.*
import java.time.LocalTime
import org.scalajs.dom.HTMLDivElement

case class ScheduleDay(eventsList: Map[LocalTime, Seq[Act]], startingTimes: Seq[LocalTime], rooms: Seq[Talk.Room]):
  def body =
    startingTimes.map(time =>
      div(
        className := "timeslot",
        time.render(),
        eventsList
          .find { _._1 == time }
          .map { events =>
            rooms.map(room =>
              events._2
                .find:
                  case t: Talk            => t.info.room.show == room.toString()
                  case _: Break | _: Special => true // ! Will cause problem for multi-track events
                .map(_.render)
                .getOrElse(div(className := "blank-card"))
            )
          }
          .getOrElse(Seq.fill(rooms.size)(emptyNode))
      )
    )

object ScheduleDay {
  def apply(events: Seq[Act]) =
    val scheduledEvents = events
      .filter:
        case t: Talk => t.time != null && t.info.room.show != null
        case e       => e.time != null
    val definedTalks: Map[LocalTime, Seq[Act]] =
      scheduledEvents.groupBy(_.time)
    val startingTimes = scheduledEvents
      .map(_.time)
      .distinct
      .sorted
    val rooms = events.collect:
      case t: Talk => t.info.room

    new ScheduleDay(definedTalks, startingTimes, rooms)
}
