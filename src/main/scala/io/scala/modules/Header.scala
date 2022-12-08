package io.scala.modules

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Header {
  def apply(): Div = div("header", className := "header")
}
