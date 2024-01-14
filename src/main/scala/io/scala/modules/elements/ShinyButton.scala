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
}
