package io.scala.app.schedule

import com.raquo.laminar.api.L.*
import org.scalajs.dom.HTMLDivElement

import io.scala.models.Act
import io.scala.models.Break
import io.scala.models.Session
import io.scala.models.Special
import io.scala.modules.syntax.*

object ScheduleDay {
  def apply(events: List[Act]) =
    val talksByTime = events.groupBy(_.time).toList.sortBy(_._1)
    val rooms: List[Session.Room] =
      events.collect { case t: Session => t.info.room.nn }.distinct.sorted

    val elements = for
      (time, events) <- talksByTime
      cards = rooms.map(room =>
        events
          .find:
            case t: Session            => t.info.room == room
            case _: Break | _: Special => true // ! Will cause problem for multi-track events
          .map(_.render)
          .getOrElse(div(className := "blank-card"))
      )
      completeLine = cards.appendedAll(List.fill(rooms.size - cards.size)(div()))
      talks = div(
        className := "timeslot-talks",
        completeLine
      )
      timeSlot <- List(time.render(), talks)
    yield timeSlot

    div(
      // Will extract it to CSS file later in the PR :)
      styleAttr :=
        s"""|display: grid;
            |grid-template-columns: 3em repeat(${rooms.size}, 1fr);
            |width: 100%;
            |row-gap: 2em;
            |column-gap: 1em;
            |""".stripMargin,
      elements
    )
}
