package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.Page.*
import io.scala.views.View

case object Test extends View {
  override def body: HtmlElement = button("Go to index", className := "test", navigateTo(IndexPage))
}