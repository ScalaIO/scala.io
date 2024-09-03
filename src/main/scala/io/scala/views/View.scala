package io.scala.views

import com.raquo.laminar.api.L.*

trait GenericView {}

trait SimpleView extends GenericView:
  def body(): HtmlElement

  final def render(): HtmlElement =
    body().amend(flex := "1")

trait ReactiveView[A] extends GenericView:
  def body(signal: Signal[A]): HtmlElement

  final def render(signal: Signal[A]): HtmlElement =
    body(signal).amend(flex := "1")
