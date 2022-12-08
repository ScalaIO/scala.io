package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.Header

trait View {
  def body: HtmlElement

  def render: HtmlElement = div(
    Header(),
    body
  )
}