package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}

object Title {
  def apply(name: String) = h1(
    className := "page-title",
    name
  )

  def small(title: String) = h2(
    className := "content-title",
    title
  )

  def withSub(title: String, subtitle: HtmlElement) = div(
    className := "page-title",
    h1(title),
    subtitle.amend(className := "page-subtitle")
  )
}
