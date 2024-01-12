package io.scala.modules.layout

import io.scala.Lexicon

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Headband {
  lazy val render: Div = div(
    span(
      Lexicon.Headband.left,
      className := "headband__information"
    ),
    span(
      Lexicon.Headband.right,
      className := "headband__information"
    ),
    className := "headband"
  )
}
