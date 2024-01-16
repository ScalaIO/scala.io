package io.scala
package modules

import com.raquo.laminar.api.L.{*, given}
import io.scala.domaines.Break
import io.scala.domaines.Room
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.domaines.Time

val rooms = Room.values

case class ScheduleDay(definedTalks: Map[Time, Seq[Event]], startingTimes: Seq[Time]):
  def body = div(
    startingTimes.map(time =>
      div(
        className := "timeslot",
        time.render().amend(marginRight := "16px"),
        div(
          className := "card-container",
          definedTalks
            .find { _._1 == time }
            .map { speakers =>
              rooms.map(room =>
                speakers._2
                  .find {
                    case t: Talk  => t.room.get == room
                    case b: Break => true // ! Will cause problem for multi-track events
                  }
                  .map {
                    case t: Talk  => TalkCard(t)
                    case b: Break => BreakCard(b)
                  }
                  .getOrElse(div(className := "blank-card"))
              )
            }
            .getOrElse(Array.fill(Room.values.size)(emptyNode))
        )
      )
    )
  )

object ScheduleDay {
  def apply(events: Seq[Event]) =
    val definedTalks: Map[Time, Seq[Event]] = events
      .filter {
        case t: Talk  => t.room.isDefined && t.start.isDefined
        case b: Break => true
      }
      .groupBy {
        case t: Talk  => t.start.get
        case b: Break => b.start
      }
    val startingTimes = events
      .collect {
        case t: Talk if t.start.isDefined => t.start.get
        case b: Break                     => b.start
      }
      .distinct
      .sorted

    new ScheduleDay(definedTalks, startingTimes)
}
