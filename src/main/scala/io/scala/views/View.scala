package io.scala.views

import io.scala.modules.*

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
trait View {
  val width:Var[Double] = Var(dom.window.innerWidth)
  dom.window.onresize = { _ => width.set(dom.window.innerWidth) }
  def body: HtmlElement

  def render: HtmlElement = div(
    Headband(),
    child <-- Header(),
    body,
    Footer()
  )
}
