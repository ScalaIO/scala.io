package io.scala.views

import io.scala.Page._
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

case object Index extends View {
  override def body: HtmlElement = div("Index page")
}
