package io.scala.views

import io.scala.modules.layout.*

import com.raquo.laminar.api.L.{*, given}

trait GenericView {
}

trait SimpleView extends GenericView:
  def body(): HtmlElement

  final def render(): HtmlElement =
    div(
      className := "fullscreen",
      Headband.render,
      child <-- Header.render,
      body().amend(flex := "1"),
      Footer.render
    )

trait SimpleViewWithDraft extends GenericView:
  def body(withDraft: Boolean): HtmlElement

  final def render(withDraft: Boolean): HtmlElement =
    div(
      className := "fullscreen",
      Headband.render,
      child <-- Header.render,
      body(withDraft).amend(flex := "1"),
      Footer.render
    )

trait ReactiveView[A] extends GenericView:
  def body(signal: Signal[A]): HtmlElement

  final def render(signal: Signal[A]): HtmlElement =
    div(
      className := "fullscreen",
      Headband.render,
      child <-- Header.render,
      body(signal).amend(flex := "1"),
      Footer.render
    )
