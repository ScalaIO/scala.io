package io.scala.domaines

import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

sealed trait TalkInfo[A <: TalkInfo[A]]:
  def ordinal: Int
object TalkInfo:
  given [A <: TalkInfo[A]]: Ordering[A] = Ordering[Int].on(_.ordinal)

enum Kind extends TalkInfo[Kind] {
  case Lightning, Talk, Keynote

  override def toString = this match
    case Kind.Lightning => "Lightning"
    case Kind.Talk      => "Talk"
    case Kind.Keynote   => "Keynote"

  def toStyle = this match
    case Kind.Lightning => "presentation-lightning"
    case Kind.Talk      => "presentation-talk"
    case Kind.Keynote   => "presentation-keynote"

  // In minutes
  def duration: Int = this match
    case Kind.Lightning => 20
    case Kind.Talk      => 45
    case Kind.Keynote   => 45
}

enum ConfDay extends TalkInfo[ConfDay] {
  case Thursday, Friday

  override def toString(): String =
    this match
      case ConfDay.Thursday => "Thursday 15 Feb."
      case ConfDay.Friday   => "Friday 16 Feb."

  def toId: String = "day" + this.ordinal
}

enum Room extends TalkInfo[Room]:
  case One
  def render = "Room " + this.ordinal

case class Time(h: Int, m: Int) {
  def render = div(
    className := "schedule__time",
    span(
      f"$h%02d",
      className := "schedule__time_hour"
    ),
    span(
      f"$m%02d",
      className := "schedule__time_minute"
    )
  )
}
object Time:
  given Ordering[Time] = Ordering[(Int, Int)].on(t => (t.h, t.m))

case class Talk(
    title: String,
    description: String,
    kind: Kind = Kind.Talk,
    day: Option[ConfDay] = None,
    room: Option[Room] = None,
    start: Option[Time] = None,
    speakers: List[Speaker]
):
  val end: Option[Time] = start.map { s =>
    val ending = s.m + kind.duration
    if ending >= 60 then Time(s.h + 1, ending - 60)
    else Time(s.h, ending)

  }
