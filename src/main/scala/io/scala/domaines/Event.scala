package io.scala.domaines


import com.raquo.laminar.api.L.{svg => _, _}
import io.scala.svgs.Icons

case class TimeRange(
    startDay: DayOfYear,
    startHour: Time,
    endDay: DayOfYear,
    endHour: Time
)

case class Event(
    town: String,
    townImg: String,
    name: String,
    address: String,
    dateTime: TimeRange,
    description: String
):
  def render() = div(
    className := "event",
    div(
      className := "hero",
      div(
        className := "overlay",
        h2(town)
      )
    ),
    div(
      className := "info",
      h2(name),
      span(
        Icons.location,
        p(s"$town - $address")
      ),
      span(
        Icons.clock,
        p(s"${dateTime.startDay} ${dateTime.startHour} - ${dateTime.endDay} ${dateTime.endHour}")
      ),
      p(description)
    )
  )
