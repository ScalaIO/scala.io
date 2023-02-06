package io.scala.modules

import io.scala.Lexicon

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Line {
  def apply(padding: Int = 0, isContrasted: Boolean = false): HtmlElement = hr(
    className                            := "line",
    className.toggle("line--contrasted") := isContrasted,
    styleAttr                            := s"margin: ${padding}px 0;"
  )
}
