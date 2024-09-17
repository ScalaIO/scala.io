package io.scala.data

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

import io.scala.extensions.*
import io.scala.models.Act
import io.scala.models.Session
import io.scala.models.Session.Room

type TimeDefinedTalk = { val dateTime: LocalDateTime; val time: LocalTime; val day: DayOfWeek }
object ScheduleInfo {
  val breaks: List[Act] = List()

  val minStart = LocalTime.of(9, 0)
  val maxEnd   = LocalTime.of(19, 0)
  val pxByHour = 600

  lazy val blankSchedule: List[Act] =
    for
      day  <- List(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
      time <- List.iterate(LocalTime.of(9, 30), len = 10)(_.plusHours(1))
      dateTime = day match
        case DayOfWeek.THURSDAY => LocalDateTime.of(2024, 11, 7, time.getHour, time.getMinute)
        case DayOfWeek.FRIDAY   => LocalDateTime.of(2024, 11, 8, time.getHour, time.getMinute)
      room <- List(Room("X"), Room("Y"), Room("Z"))
    yield Session.empty.copy(info =
      Session.BasicInfo.empty.copy(
        dateTime = dateTime,
        room = room,
        title = "To be announced",
        confirmed = true
      )
    )

  lazy val schedule: List[Act] =
    SessionsHistory
      .sessionsForSchedule
      .keepAs:
        case t: Session if t.info.dateTime.isDefined => t.asInstanceOf[Session & TimeDefinedTalk]
}
