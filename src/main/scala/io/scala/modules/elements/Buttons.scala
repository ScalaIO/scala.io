package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.utils.ButtonKind
import org.scalajs.dom.HTMLButtonElement

object Buttons:
  type Button = ReactiveHtmlElement[HTMLButtonElement]
  extension (b: Button) inline def important = b.amend(className := "highlight")

  def classyNew(modifier: Modifier[Button]*) =
    button(
      className := "classy-button",
      modifier
    )

  def classy(
      text: String,
      kind: ButtonKind = ButtonKind.Submit
  ) =
    kind match
      case ButtonKind.Submit     => button(text, typ := "submit", className := "classy-button")
      case ButtonKind.Href(link) => a(text, href := link, target := "_blank", className := "classy-button")

  def shiny(text: String, modifiers: Modifier[Button]*): Button =
    button(
      text,
      typ       := "submit",
      className := "shiny-button",
      modifiers
    )
