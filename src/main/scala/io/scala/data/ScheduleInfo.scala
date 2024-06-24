package io.scala.data

import java.time.LocalDateTime
import java.time.LocalTime

import io.scala.models.{Break, Special}

object ScheduleInfo {
  val breaks = List(
    Break(
      dateTime = LocalDateTime.of(2024, 2, 15, 10, 30),
      kind = Break.Kind.Large,
      overrideDuration = Some(20)
    ),
    Break(
      dateTime = LocalDateTime.of(2024, 2, 15, 12, 30),
      kind = Break.Kind.Lunch
    ),
    Break(
      dateTime = LocalDateTime.of(2024, 2, 15, 16, 25),
      kind = Break.Kind.Large
    ),
    Special(
      dateTime = LocalDateTime.of(2024, 2, 15, 18, 35),
      kind = Special.Kind.End
    ),
    Break(
      dateTime = LocalDateTime.of(2024, 2, 16, 10, 35),
      kind = Break.Kind.Large
    ),
    Break(
      dateTime = LocalDateTime.of(2024, 2, 16, 12, 30),
      kind = Break.Kind.Lunch
    ),
    Break(
      dateTime = LocalDateTime.of(2024, 2, 16, 16, 25),
      kind = Break.Kind.Large
    ),
    Special(
      dateTime = LocalDateTime.of(2024, 2, 16, 18, 0),
      kind = Special.Kind.End
    )
  )

  val minStart      = LocalTime.of(9, 0)
  val maxEnd        = LocalTime.of(19, 0)
  val pxByHour      = 600
  lazy val schedule = TalksHistory.talksForConf(None)
}
