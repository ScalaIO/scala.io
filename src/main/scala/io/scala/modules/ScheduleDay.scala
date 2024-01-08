package io.scala.modules

import io.scala.domaines.Room
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.domaines.Time
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

val rooms = Room.values

case class ScheduleDay(definedTalks: Map[Time, Seq[Talk]], startingTimes: Seq[Time]):
  def body = div(
      startingTimes.map(time =>
        div(
          className := "schedule__day",
          time.render,
          div(
            className := "card-container",
            definedTalks
              .find { _._1 == time }
              .map { speakers =>
                rooms.map(room =>
                  speakers._2
                    .find(_.room.get == room)
                    .map(TalkCard(_))
                    .getOrElse(div(className := "empty-talk-card"))
                )
              }
              .getOrElse(Array.fill(Room.values.size)(emptyNode))
          )
        )
      )
    )

object ScheduleDay {
  def apply(speakers: Seq[Talk]) =
    val definedTalks: Map[Time, Seq[Talk]] = speakers
      .filter(s => s.room.isDefined && s.start.isDefined)
      .groupBy(_.start.get)
    val startingTime = speakers
      .collect { case s if s.start.isDefined => s.start.get }
      .distinct
      .sorted
    new ScheduleDay(definedTalks, startingTime)
}
