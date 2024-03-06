package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}

object Separator {
  def apply(element: Element*): Div =
    div(
      className := "container separator",
      element
    )
}
