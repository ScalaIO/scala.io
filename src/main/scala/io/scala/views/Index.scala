package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.views.View
import io.scala.Page._

case object Index extends View {
  override def body: HtmlElement = button("Go to test", className := "index", navigateTo(TestPage))
}