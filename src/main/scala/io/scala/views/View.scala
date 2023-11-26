package io.scala.views

import io.scala.modules.*

import com.raquo.laminar.api.L.{*, given}

trait View {
  def body: HtmlElement

  def render: HtmlElement = div(
    Headband(),
    child <-- Header(),
    body,
    Footer()
  )
}
