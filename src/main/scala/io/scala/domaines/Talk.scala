package io.scala.domaines

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.tags.HtmlTag
import org.scalajs.dom

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

case class Talk(
    title: String,
    slug: String,
    description: String,
    kind: Talk.Kind = Kind.Speech,
    day: Option[ConfDay] = None,
    room: Option[Room] = None,
    start: Option[Time] = None,
    speakers: List[Speaker]
):
  lazy val renderDescription = description.split("\n").map(p(_))

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

case class Break(
    day: ConfDay,
    start: Time,
    kind: Break.Kind
)

object Break:
  enum Kind:
    case Coffee, Large, Launch, End, CommunityParty
