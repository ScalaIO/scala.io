package io.scala.modules.syntax

import com.raquo.laminar.api.L._
import java.time.LocalTime

extension (time: LocalTime)
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
