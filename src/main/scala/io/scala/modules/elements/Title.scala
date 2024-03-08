package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLHeadingElement

object Title {
  def apply(name: String, modifiers: Modifier[ReactiveHtmlElement[HTMLHeadingElement]]*) = h1(
    name,
    className := "page-title",
    modifiers
  )

  def small(title: String, modifiers: Modifier[ReactiveHtmlElement[HTMLHeadingElement]]*) = h2(
    title,
    className := "content-title",
    modifiers
  )

  def withSub(title: String, subtitle: HtmlElement) = div(
    className := "page-title",
    h1(title),
    subtitle.amend(className := "page-subtitle")
  )
}
