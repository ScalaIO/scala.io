package io.scala.modules.elements

import io.scala.{Lexicon, Page}
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object ShinyButton {
  def apply(text: String): Button =
    button(
      text,
      typ       := "submit",
      className := "shiny-button"
    )
}
