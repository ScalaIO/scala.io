package io.scala.modules.elements

import com.raquo.laminar.api.L.*

enum LineKind:
  case Normal, Contrasted, Colored

type SizeUnit = "em" | "rem"

object Line {
  import LineKind.*
  def apply(margin: Float = 0, size: Float = 1, sizeUnit: SizeUnit = "em", kind: LineKind = Normal): HtmlElement =
    val line = hr(
      className := "line",
      styleAttr := s"margin: ${margin}$sizeUnit 0; height: ${size}px;"
    )
    kind match
      case Normal     => line
      case Contrasted => line.amend(className := "line--contrasted")
      case Colored    => line.amend(className := "line--colored")

  def separator(vMargin: Int = 0, width: Int = 0, height: Int = 1) =
    hr(
      className := "line--colored",
      styleAttr := s"margin: ${vMargin}rem auto; height: ${height}px; width: ${width}%; border-radius: 8px"
    )
}
