package io.scala.modules.elements

import io.scala.{Lexicon, Page}
import io.scala.svgs.Logo
import io.scala.utils.ButtonKind
import io.scala.utils.ButtonKind.Submit

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.{html, HTMLElement}

object ClassyButton {
  def apply(
      text: String,
      kind: ButtonKind = ButtonKind.Submit,
      isImportant: Boolean = true
  ) =
    val element: ReactiveHtmlElement[HTMLElement] = kind match
      case Submit                => button(text, typ := "submit")
      case ButtonKind.Href(link) => a(text, href := link, target := "_blank")

    element.amend(
      className                                    := "classy-button",
      className.toggle("classy-button--important") := isImportant
    )
}
