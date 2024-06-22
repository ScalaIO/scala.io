package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLLIElement

object Lists:
  def flat(elements: ReactiveHtmlElement[HTMLLIElement]*) =
    ul(
      className := "list-flat",
      elements
    )

  def innerDiscs(elements: ReactiveHtmlElement[HTMLLIElement]*) =
    ul(
      className := "list-inner-discs",
      elements
    )
