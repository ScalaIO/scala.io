package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLAnchorElement

object Links:
  type LinkTarget = "_blank" | "_self" | "_parent" | "_top"

  def flat(
      content: Modifier[ReactiveHtmlElement[HTMLAnchorElement]]*
  ) =
    a(content, target := "_blank")

  def highlighted(
      content: Modifier[ReactiveHtmlElement[HTMLAnchorElement]]*
  ) =
    a(className := "link-highlight", content, target := "_blank")

  def highlightedInPlace(
      content: Modifier[ReactiveHtmlElement[HTMLAnchorElement]]*
  ) =
    a(className := "link-highlight", content, target := "_self")
