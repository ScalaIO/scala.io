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

object View {
  // Special rendering for the index page so that the hero image takes the whole remaining space on screen
  def renderIndex = div(
    div(
      display.flex,
      flexDirection.column,
      minHeight := "100vh",
      Headband.render,
      child <-- Header.render,
      Index.hero
    ),
    Index.body,
    Footer.render
  )
}
