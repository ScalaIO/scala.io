package io.scala.modules

import io.scala.Lexicon

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Line {
  def apply(): HtmlElement = hr(className := "line")
}
