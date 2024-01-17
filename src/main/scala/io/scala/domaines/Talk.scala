package io.scala.domaines

import io.scala.svgs
import io.scala.svgs.Logo

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
    speakers: List[Speaker],
    category: Talk.Category = Talk.Category.NA
):
  lazy val renderDescription = description.split("\n").map(p(_))

object Talk:
  enum Kind:
    case Lightning, Short, Speech, Keynote

    override def toString = this match
      case Speech    => "Talk"
      case Short     => "Short"
      case Keynote   => "Keynote"
      case Lightning => "Lightning"

    def toStyle = this match
      case Speech    => "presentation-talk"
      case Short     => "presentation-short"
      case Keynote   => "presentation-keynote"
      case Lightning => "presentation-lightning"

  enum Category:
    case ScalaStuff, Libs, Idk, DataEng, NA

    def toName = this match
      case Idk   => "I don't know (yet)"
      case DataEng   => "Data engineering"
      case ScalaStuff => "Scala & Language Stuff"
      case Libs  => "Libraries & Tools"
      case NA    => "N/A"

    def toStyle = this match
      case Idk   => "category-one"
      case DataEng   => "category-two"
      case ScalaStuff => "category-three"
      case Libs  => "category-four"
      case NA    => "category-na"

case class Break(
    day: ConfDay,
    start: Time,
    kind: Break.Kind
)

object Break:
  enum Kind:
    case Coffee, Large, Launch

    def toIcon = this match
      case Coffee => svgs.Coffee()
      case Large  => svgs.Chat()
      case Launch => svgs.Food()
    def duration = this match
      case Coffee => 5
      case Large  => 15
      case Launch => 60
  object Kind:
    val max = Kind.values.map(_.duration).max

case class Special(
    day: ConfDay,
    start: Time,
    kind: Special.Kind
):
  def render = kind match
    case Special.Kind.End            => div(className := "blank-card", Logo("#222222"))
    case Special.Kind.CommunityParty => div(className := s"blank-card community-party")

object Special:
  enum Kind:
    case End, CommunityParty
