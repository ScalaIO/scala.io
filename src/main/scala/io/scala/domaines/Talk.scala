package io.scala.domaines

import io.scala.Lexicon.Footer.Newsletter.description
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

sealed trait TalkInfo[A <: TalkInfo[A]]:
  def ordinal: Int
object TalkInfo:
  given [A <: TalkInfo[A]]: Ordering[A] = Ordering[Int].on(_.ordinal)

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
  def +(minutes: Int): Time =
    val ending = m + minutes
    if ending >= 60 then Time(h + 1, ending - 60)
    else Time(h, ending)
  def render = div(
    className := "schedule__time",
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

// trait Event:
//   def day: Option[ConfDay]
//   def room: Option[Room]
//   def start: Option[Time]
//   def end: Option[Time]

case class Talk(
    title: String,
    description: String,
    kind: Talk.Kind = Kind.Speech,
    day: Option[ConfDay] = None,
    room: Option[Room] = None,
    start: Option[Time] = None,
    speakers: List[Speaker]
):
  val end: Option[Time] = start.map(_ + kind.duration)

object Talk:
  enum Kind extends TalkInfo[Kind]:
    case Lightning, Short, Speech, Keynote

    override def toString = this match
      case Lightning => "Lightning"
      case Short     => "Short"
      case Speech    => "Talk"
      case Keynote   => "Keynote"

    def toStyle = this match
      case Lightning => "presentation-lightning"
      case Short     => "presentation-short"
      case Speech    => "presentation-talk"
      case Keynote   => "presentation-keynote"

    // In minutes
    def duration: Int = this match
      case Lightning => 10
      case Short     => 25
      case Speech    => 45
      case Keynote   => 60

case class Break(
    day: ConfDay,
    start: Time,
    kind: Break.Kind
):
  val end: Time = start + kind.duration

object Break:
  enum Kind:
    case Coffee, Large, Launch, End

    def duration: Int = this match
      case Coffee => 5
      case Large  => 15
      case Launch => 60
      case End => -1