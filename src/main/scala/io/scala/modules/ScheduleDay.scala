package io.scala.modules

import io.scala.domaines.*

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ChildNode
import org.scalajs.dom.Comment
import org.scalajs.dom.HTMLDivElement
import org.scalajs.dom.console
import io.scala.domaines.{Talk, Act, Special, Break, Time}

val rooms = Room.values
case class ScheduleDay(eventsList: Map[Time, Seq[Act]], startingTimes: Seq[Time]):
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
                .find {
                  case t: Talk               => t.room == room
                  case _: Break | _: Special => true // ! Will cause problem for multi-track events
                }
                .map(_.render)
                .getOrElse(div(className := "blank-card"))
            )
          }
          .getOrElse(Array.fill(Room.values.size)(emptyNode))
      )
    )

object ScheduleDay {
  def apply(events: Seq[Act]) =
    val scheduledEvents = events
      .filter:
        case t: Talk => t.start != null && t.room != null
        case e       => e.start != null
    val definedTalks: Map[Time, Seq[Act]] =
      scheduledEvents.groupBy(_.start)
    val startingTimes = scheduledEvents
      .map(_.start)
      .distinct
      .sorted

    new ScheduleDay(definedTalks, startingTimes)
}
