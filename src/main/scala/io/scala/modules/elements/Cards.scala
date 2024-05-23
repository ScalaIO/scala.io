package io.scala.modules.elements

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLElement

object Cards:
  def container(elements: ReactiveHtmlElement[HTMLElement]*) =
    div(
      className := "card-container",
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