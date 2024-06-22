package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLElement

object Cards:
  def container(elements: ReactiveHtmlElement[HTMLElement]*) =
    div(
      className := "card-container",
      elements
    )

  def containerFlex(elements: ReactiveHtmlElement[HTMLElement]*) =
    div(
      className := "card-container-flex",
      elements
    )

  def shadowed(
      header: ReactiveHtmlElement[HTMLElement] = div(),
      body: ReactiveHtmlElement[HTMLElement] = div(),
      footer: ReactiveHtmlElement[HTMLElement] = div()
  ) =
    div(
      className := "card-blank",
      header,
      body,
      footer
    )

  def withMedia(
      header: ReactiveHtmlElement[HTMLElement] = div(),
      media: ReactiveHtmlElement[HTMLElement] = div(),
      body: ReactiveHtmlElement[HTMLElement] = div(),
      footer: ReactiveHtmlElement[HTMLElement] = div()
  ) =
    div(
      className := "card-blank",
      header,
      media,
      body,
      footer
    )
