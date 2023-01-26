package io.scala.views

import io.scala.Page.*
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

case object Test extends View {
  override def body: HtmlElement = button("Go to index", className := "test", navigateTo(IndexPage))
}
