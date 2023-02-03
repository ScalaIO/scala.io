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
}
