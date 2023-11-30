package io.scala.modules

import io.scala.Lexicon

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

enum LineKind:
  case Normal, Contrasted, Colored

type SizeUnit = "px" | "em" | "rem" | "vh" | "vw" | "%"

object Line {
  import LineKind.*
  def apply(margin: Int = 0, size: Int = 1, sizeUnit: SizeUnit = "px", kind: LineKind = Normal): HtmlElement =
    val line = hr(
      className := "line",
      styleAttr := s"margin: ${margin}px 0; height: ${size}px;"
    )
    kind match
      case Normal     => line
      case Contrasted => line.amend(className := "line--contrasted")
      case Colored    => line.amend(className := "line--colored")

  def separator(vMargin: Int = 0, width: Int = 0, height: Int = 1) =
    hr(
      className := "line--colored",
      styleAttr := s"margin: ${vMargin}px auto; height: ${height}px; width: ${width}%;"
    )
}
