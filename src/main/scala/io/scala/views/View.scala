package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.layout.*


trait SimpleView extends GenericView {
  def title:String
  def body(withDraft:Boolean):HtmlElement

  final def render(withDraft:Boolean):HtmlElement = {
    div(
      className := "fullscreen",
      Headband.render,
      child <-- Header.render,
      body(withDraft).amend(flex := "1"),
      Footer.render
    )
  }
}

trait GenericView {
  def render(withDraft:Boolean):HtmlElement
}




