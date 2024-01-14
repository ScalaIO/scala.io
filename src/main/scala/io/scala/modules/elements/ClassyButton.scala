package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.Lexicon
import io.scala.Page
import io.scala.svgs.Logo
import io.scala.utils.ButtonKind
import io.scala.utils.ButtonKind.Submit
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.html

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
      className.toggle("classy-button-highlight") := isImportant,
    )
}
