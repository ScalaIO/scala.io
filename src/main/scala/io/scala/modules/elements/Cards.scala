package io.scala.modules.elements

import com.raquo.laminar.api.L.*

object Cards:
  def shadowed(
      header: HtmlElement = div(),
      body: HtmlElement = div(),
      footer: HtmlElement = div()
  ) =
    div(
      className := "card-blank",
      header,
      body,
      footer
    )

  def withMedia(
      header: HtmlElement = div(),
      media: HtmlElement = div(),
      body: HtmlElement = div(),
      footer: HtmlElement = div()
  ) =
    div(
      className := "card-blank",
      header,
      media,
      body,
      footer
    )
