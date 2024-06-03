package io.scala.views

import com.raquo.laminar.api.L._
import io.scala.modules.layout._

trait GenericView {
}

trait SimpleView extends GenericView:
  def body(): HtmlElement

  final def render(): HtmlElement =
    div(
      className := "fullscreen",
      child <-- Header.render,
      body().amend(flex := "1"),
      Footer.render
    )

trait SimpleViewWithDraft extends GenericView:
  def body(withDraft: Boolean, conference: Option[String]): HtmlElement

  def render(withDraft: Boolean, conference: Option[String]): HtmlElement =
    div(
      className := "fullscreen",
      child <-- Header.render,
      body(withDraft, conference).amend(flex := "1"),
      Footer.render
    )

trait EmptyReactiveView[A] extends GenericView:
  def render(signal: Signal[A]): HtmlElement

trait ReactiveView[A] extends GenericView:
  def body(signal: Signal[A]): HtmlElement

  def render(signal: Signal[A]): HtmlElement =
    div(
      className := "fullscreen",
      child <-- Header.render,
      body(signal).amend(flex := "1"),
      Footer.render
    )
