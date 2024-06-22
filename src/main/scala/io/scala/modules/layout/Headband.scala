package io.scala.modules.layout

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement

import io.scala.Lexicon

object Headband {
  lazy val render: Div = div(
    span(Lexicon.Headband.left),
    span(Lexicon.Headband.right),
    className := "headband"
  )
}
