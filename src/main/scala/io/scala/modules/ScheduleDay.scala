package io.scala.modules

import io.scala.domaines.Talk
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import io.scala.domaines.Speaker

object ScheduleDay {
  def apply(speakers: Seq[Speaker]) =
    val definedTalks = speakers
      .filter(s => s.talk.room.isDefined && s.talk.start.isDefined)
      .groupBy(s => (s.talk.start.get, s.talk.room.get))
      .mapValues(_.head)
      .force
      .toSeq
      .sortBy { case ((start, room), _) => (start, room) }
    val rooms = speakers
      .collect { case s if s.talk.room.isDefined => s.talk.room.get }
      .distinct
      .sorted
    val startingTime = speakers
      .collect { case s if s.talk.start.isDefined => s.talk.start.get }
      .distinct
      .sorted

    div(
      // className := "schedule__day",
      startingTime.map(time =>
        div(
          className := "schedule__day",
          time.render,
          div(
            className := "schedule__day__timeslot",
            rooms.map(room =>
              definedTalks
                .find { case ((start, r), _) => start == time && r == room }
                .map { case (_, speaker) => TalkCard(speaker) }
                .getOrElse(span())
            )
          )
        )
      )
    )
}
