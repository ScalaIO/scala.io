package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.Lexicon
import io.scala.Page
import io.scala.svgs.Logo
import org.scalajs.dom.html

object ShinyButton {
  def apply(text: String): Button =
    button(
      text,
      typ       := "submit",
      className := "shiny-button"
    )

  def light(text: String): Button =
    button(
      text,
      typ       := "submit",
      className := "shiny-button-light"
    )

  def link(text: String): Anchor =
    a(
      text,
      className := "shiny-button"
    )

  def linkLight(text: String): Anchor =
    a(
      text,
      className := "shiny-button-light"
    )
}
