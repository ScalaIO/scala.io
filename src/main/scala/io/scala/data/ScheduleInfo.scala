package io.scala.data

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

import io.scala.extensions.*
import io.scala.models.Act
import io.scala.models.Talk

type TimeDefinedTalk = { val dateTime: LocalDateTime; val time: LocalTime; val day: DayOfWeek }
object ScheduleInfo {
  val breaks: List[Act] = List()

  val minStart                          = LocalTime.of(9, 0)
  val maxEnd                            = LocalTime.of(19, 0)
  val pxByHour                          = 600
  lazy val schedule: Seq[Act] =
    TalksHistory
      .talksForConf(None)
      .keepAs:
        case t: Talk if t.info.dateTime.isDefined => t.asInstanceOf[Talk & TimeDefinedTalk]
}
