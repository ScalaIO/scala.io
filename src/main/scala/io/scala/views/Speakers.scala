package io.scala.views

import io.scala.Page.*
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

case object Speakers extends View {
  override def body: HtmlElement = div("Speakers page")
}
