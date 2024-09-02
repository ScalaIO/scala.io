package io.scala.data

import java.time.LocalTime

import io.scala.models.Act

object ScheduleInfo {
  val breaks: List[Act] = List()

  val minStart      = LocalTime.of(9, 0)
  val maxEnd        = LocalTime.of(19, 0)
  val pxByHour      = 600
  lazy val schedule = TalksHistory.talksForConf(None)
}
