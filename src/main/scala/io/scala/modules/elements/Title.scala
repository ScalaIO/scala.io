package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}

object Title {
  def apply(name: String) = h1(
    name,
    className := "title"
  )

  def withSub(title: String, subtitle: HtmlElement) = div(
    h1(title),
    subtitle.amend(className := "subtitle"),
    className := "title"
  )
}
