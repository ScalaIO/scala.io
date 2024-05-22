package io.scala.modules.elements

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom.HTMLAnchorElement
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.SVGSVGElement

object Links:
  def flat(
      ref: String,
      content: ReactiveHtmlElement[HTMLElement] | String,
      name: String = "",
      targt: String = "_blank",
      modifiers: Modifier[ReactiveHtmlElement[HTMLAnchorElement]]*
  ) =
    content match
      case content: String =>
        a(href := ref, nameAttr := name, content, target := targt, modifiers)
      case content: ReactiveHtmlElement[_] =>
        a(href := ref, content, modifiers)

  def highlighted(
      ref: String,
      content: ReactiveSvgElement[SVGSVGElement] | ReactiveHtmlElement[HTMLElement] | String,
      name: String = "",
      targt: String = "_blank",
      modifiers: Modifier[ReactiveHtmlElement[HTMLAnchorElement]]*
  ) =
    content match
      case content: String =>
        a(className := "link-highlight", href := ref, nameAttr := name, content, target := targt, modifiers)
      case content @ (_: ReactiveHtmlElement[_] | _: ReactiveSvgElement[_]) =>
        a(className := "link-highlight", href := ref, nameAttr := name, content, target := targt, modifiers)
