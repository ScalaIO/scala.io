package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLButtonElement
import org.scalajs.dom.HTMLElement

import io.scala.utils.ButtonKind

object Buttons:
  def classyNew(
      isImportant: Boolean = true,
      modifier: Modifier[ReactiveHtmlElement[HTMLButtonElement]]*
  ) =
    button(
      className                     := "classy-button",
      className.toggle("highlight") := isImportant,
      modifier
    )

  def classy(
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

  def shiny(text: String): Button =
    button(
      text,
      typ       := "submit",
      className := "shiny-button"
    )
