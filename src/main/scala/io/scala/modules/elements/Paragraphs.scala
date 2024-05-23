package io.scala.modules.elements

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLParagraphElement

object Paragraphs:
  def description(content: Modifier[ReactiveHtmlElement[HTMLParagraphElement]]*) =
    p(
      className := "paragraph",
      content
    )
