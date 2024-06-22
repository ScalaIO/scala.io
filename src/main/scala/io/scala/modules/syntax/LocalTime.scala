package io.scala.modules.syntax

import com.raquo.laminar.api.L.*
import java.time.LocalTime

extension (time: LocalTime)
  def toHour = time.getHour() + time.getMinute() / 60.0

  def render() = div(
    span(
      f"${time.getHour()}%02d",
      className := "time_hour"
    ),
    span(
      f"${time.getMinute()}%02d",
      className := "time_minute"
    )
  )
