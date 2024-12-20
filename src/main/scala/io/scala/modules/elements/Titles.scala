package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLHeadingElement

object Titles:
  def apply(name: String, modifiers: Modifier[ReactiveHtmlElement[HTMLHeadingElement]]*) = h1(
    name,
    className := "page-title",
    modifiers
  )

  def main(title: String) = h1(title, className := "title")

  def medium(title: String, modifiers: Modifier[ReactiveHtmlElement[HTMLHeadingElement]]*) = h2(
    title,
    className := "content-title",
    modifiers
  )

  def small(title: String, modifiers: Modifier[ReactiveHtmlElement[HTMLHeadingElement]]*) = h3(
    title,
    className := "small-title bold",
    modifiers
  )

  def smallThin(title: String, modifiers: Modifier[ReactiveHtmlElement[HTMLHeadingElement]]*) = h3(
    title,
    className := "small-title",
    modifiers
  )

  def withSub(title: String, subtitle: HtmlElement) = div(
    h1(title),
    subtitle.amend(className := "page-subtitle"),
    className := "page-title"
  )
