package io.scala.modules.elements

import com.raquo.laminar.api.L.*

object ShinyButton {
  def apply(text: String): Button =
    button(
      text,
      typ       := "submit",
      className := "shiny-button"
    )

  def link(text: String): Anchor =
    a(
      text,
      className := "shiny-button"
    )
}
