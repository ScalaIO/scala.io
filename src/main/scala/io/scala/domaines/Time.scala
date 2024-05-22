package io.scala.domaines

import com.raquo.laminar.api.L._
import com.raquo.laminar.tags.HtmlTag
import org.scalajs.dom

case class DayOfYear(year: Int, month: Int, day: Int):
  override def toString(): String = s"$day/$month/$year"

case class Time(h: Int, m: Int) {
  val toHour = h + m / 60.0
  override def toString(): String = f"$h%02d:$m%02d"
  def render(tag: HtmlTag[dom.html.Element] = div) = tag(
    span(
      f"$h%02d",
      className := "time_hour"
    ),
    span(
      f"$m%02d",
      className := "time_minute"
    )
  )
}
object Time:
  given Ordering[Time] = Ordering[(Int, Int)].on(t => (t.h, t.m))