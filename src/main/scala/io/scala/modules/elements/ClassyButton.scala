package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLElement

import io.scala.utils.ButtonKind

object ClassyButton {
  def apply(
      text: String,
      kind: ButtonKind = ButtonKind.Submit,
      isImportant: Boolean = true
  ) =
    val element: ReactiveHtmlElement[HTMLElement] = kind match
      case ButtonKind.Submit     => button(text, typ := "submit")
      case ButtonKind.Href(link) => a(text, href := link, target := "_blank")

    element.amend(
      className                     := "classy-button",
      className.toggle("highlight") := isImportant
    )
}
