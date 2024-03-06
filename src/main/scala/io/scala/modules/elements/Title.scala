package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}

object Title {
  def apply(name: String) = h1(
    name,
    className := "page-title"
  )

  def small(title: String) = h2(
    title,
    className := "content-title"
  )

  def withSub(title: String, subtitle: HtmlElement) = div(
    h1(title),
    subtitle.amend(className := "page-subtitle"),
    className := "page-title"
  )
}
