package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.*

trait View {
  def body: HtmlElement

  def render: HtmlElement = div(
    Headband(),
    Header(),
    body
  )
}