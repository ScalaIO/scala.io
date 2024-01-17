package io.scala
package modules

import io.scala.domaines.*

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ChildNode
import org.scalajs.dom.Comment
import org.scalajs.dom.HTMLDivElement
import org.scalajs.dom.console

val rooms = Room.values
case class ScheduleDay(eventsList: Map[Time, Seq[Event]], startingTimes: Seq[Time]):
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
                  case t: Talk               => t.room.get == room
                  case _: Break | _: Special => true // ! Will cause problem for multi-track events
                }
                .map {
                  case t: Talk    => TalkCard(t)
                  case b: Break   => div(className := "blank-card", b.kind.toIcon, b.kind.toIcon)
                  case s: Special => s.render
                }
                .getOrElse(div(className := "blank-card"))
            )
          }
          .getOrElse(Array.fill(Room.values.size)(emptyNode))
      )
    )

object ScheduleDay {
  def apply(events: Seq[Event]) =
    val definedTalks: Map[Time, Seq[Event]] = events
      .filter {
        case t: Talk               => t.room.isDefined && t.start.isDefined
        case _: Break | _: Special => true
      }
      .groupBy {
        case t: Talk    => t.start.get
        case b: Break   => b.start
        case s: Special => s.start
      }
    val startingTimes = events
      .collect {
        case t: Talk if t.start.isDefined => t.start.get
        case b: Break                     => b.start
        case s: Special                   => s.start
      }
      .distinct
      .sorted

    new ScheduleDay(definedTalks, startingTimes)
}
