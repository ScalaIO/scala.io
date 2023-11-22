package io.scala.domaines

enum Presentation {
  case Talk
  case Keynote

  override def toString = this match
    case Presentation.Talk    => "Talk"
    case Presentation.Keynote => "Keynote"

  def toStyle = this match
    case Presentation.Talk    => "presentation-talk"
    case Presentation.Keynote => "presentation-keynote"

  // In minutes
  def duration: Int = this match
    case Presentation.Talk    => 45
    case Presentation.Keynote => 45
}

object Presentation:
  given Ordering[Presentation] = Ordering[Int].on(_.ordinal)

enum ConfDay {
  case Thursday, Friday

  override def toString(): String =
    this match
      case ConfDay.Thursday => "Thursday 15 Feb."
      case ConfDay.Friday   => "Friday 16 Feb."

  def toId: String = "day" + this.ordinal
}

object ConfDay:
  given Ordering[ConfDay] = Ordering[Int].on(_.ordinal)

case class Time(h: Int, m: Int) {
  override def toString(): String = f"$h%02d:$m%02d"
}

case class Talk(
    name: String,
    description: String,
    kind: Presentation = Presentation.Talk,
    day: Option[ConfDay] = None,
    start: Option[Time] = None
):
  val end: Option[Time] = start.map { s =>
    val ending = s.m + kind.duration
    if ending >= 60 then Time(s.h + 1, ending - 60)
    else Time(s.h, ending)

  }
