package io.scala.domaines

import io.scala.Lexicon.Footer.Newsletter.description

import com.raquo.laminar.api.L.{svg => _, *, given}
import io.scala.modules.elements.Icon

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
        Icon("location"),
        p(s"$town - $address")
      ),
      span(
        Icon("clock"),
        p(s"${dateTime.startDay} ${dateTime.startHour} - ${dateTime.endDay} ${dateTime.endHour}")
      ),
      p(description)
    )
  )
