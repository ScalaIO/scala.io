package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.layout.*

trait View {
  def render(body: HtmlElement): HtmlElement = div(
    Headband.render,
    display.flex,
    flexDirection.column,
    minHeight := "100vh",
    child <-- Header.render,
    body.amend(flex := "1"),
    Footer.render
  )
}
