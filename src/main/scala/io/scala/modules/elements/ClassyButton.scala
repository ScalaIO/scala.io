package io.scala.modules.elements

import io.scala.utils.ButtonKind

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLElement

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
      className                                   := "classy-button",
      className.toggle("button-highlight") := isImportant
    )
}
