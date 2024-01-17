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

sealed trait Event:
  def day: ConfDay
  def start: Time
sealed trait Durable:
  def duration: Int
case class Talk(
    title: String,
    slug: String,
    description: String,
    kind: Talk.Kind = Kind.Speech,
    day: ConfDay | Null = null,
    room: Room | Null = null,
    start: Time | Null = null,
    speakers: List[Speaker],
    category: Talk.Category = Talk.Category.NA
) extends Event
    with Durable:
  lazy val renderDescription = description.split("\n").map(p(_))
  def duration: Int          = kind.duration

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

    def duration = this match
      case Speech    => 45
      case Short     => 25
      case Keynote   => 60
      case Lightning => 15

  enum Category:
    case Algebra, DataEng, Community, ToolsEcosystem, Effects, Modeling, Cloud, AI, NA

    def slug = this match
      case Algebra        => "dash-of-algebra"
      case DataEng        => "data-engineering"
      case Community      => "community"
      case ToolsEcosystem => "tools-and-ecosystem"
      case Effects        => "effects-system"
      case Modeling       => "modeling"
      case Cloud          => "cloud"
      case AI             => "artificial-intelligence"
      case NA             => "not-assigned"

    def name = this match
      case Algebra        => "A Dash of Algebra"
      case DataEng        => "Data Engineering"
      case Community      => "Community"
      case ToolsEcosystem => "Tools & Ecosystems"
      case Effects        => "Effects"
      case Modeling       => "Modeling / Domain Modeling"
      case Cloud          => "Cloud"
      case AI             => "AI"
      case NA             => "N/A"

    def toStyle = this match
      case Algebra        => "category-algebra"
      case DataEng        => "category-data"
      case Community      => "category-community"
      case ToolsEcosystem => "category-tools"
      case Effects        => "category-effects"
      case Modeling       => "category-modeling"
      case Cloud          => "category-cloud"
      case AI             => "category-ai"
      case NA             => "category-na"

case class Break(
    day: ConfDay,
    start: Time,
    kind: Break.Kind
) extends Event
    with Durable:
  def duration: Int = kind.duration

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
) extends Event:
  def render = kind match
    case Special.Kind.End            => div(className := "blank-card", Logo("#222222"))
    case Special.Kind.CommunityParty => div(className := s"blank-card community-party")

object Special:
  enum Kind:
    case End, CommunityParty
