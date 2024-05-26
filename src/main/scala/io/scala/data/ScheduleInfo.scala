package io.scala.data

import io.scala.data.TalksInfo.allTalks
import io.scala.domaines._

import java.time.LocalDateTime

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

  val minStart           = Time(9, 0)
  val maxEnd             = Time(19, 0)
  val pxByHour           = 600
  lazy val schedule      = allTalks ++ breaks
}
