package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import org.scalajs.dom.HTMLDivElement

import io.scala.models.Act
import io.scala.models.Break
import io.scala.models.Session
import io.scala.models.Special
import io.scala.modules.syntax.*

object ScheduleDay {
  extension (events: List[Act])
    def toTimeslotLine(rooms: List[Session.Room]) =
      events match
        case List(k: Session) if k.isKeynote => List(k.render)
        case List(b: Break)                  => List(b.render)
        case List(s: Special)                => List(s.render)
        case evs =>
          rooms.map(room =>
            evs
              .find:
                case t: Session            => t.info.room == room
                case _: Break | _: Special => true // ! Will cause problem for multi-track events
              .map(_.render)
              .getOrElse(div(className := "blank-card"))
          )

  def apply(eventsOfDay: List[Act]) =
    val talksByTime = eventsOfDay.groupBy(_.time).toList.sortBy(_._1)
    val rooms: List[Session.Room] =
      eventsOfDay
        .collect { case t: Session => t.info.room.nn }
        .distinct
        .sorted

    val elements = for
      (time, events) <- talksByTime
      talks = div(
        className := "timeslot-talks",
        events.toTimeslotLine(rooms)
      )
      timeSlot <- List(time.render(), talks)
    yield timeSlot

    div(
      className := "day-view",
      elements
    )
}
