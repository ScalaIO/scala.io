package io.scala.modules

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.svgs.Logo
import io.scala.{Lexicon, Page}
import org.scalajs.dom.html

object ClassyButton {
  def apply(text: String): Button =
    button(
      text,
      typ       := "submit",
      className := "classy-button"
    )
}
