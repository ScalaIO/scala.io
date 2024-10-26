package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLAnchorElement
import org.scalajs.dom.HTMLButtonElement
import io.scala.Page

object Links:
  type Anchor     = ReactiveHtmlElement[HTMLAnchorElement]
  type Button     = ReactiveHtmlElement[HTMLButtonElement]
  type LinkTarget = "_blank" | "_self" | "_parent" | "_top"

  def innerPage(text: String, page: Page, modifiers: Modifier[Anchor]*) =
    a(text, Page.navigateTo(page), modifiers)

  def flat(modifiers: Modifier[Anchor]*) =
    a(modifiers, target := "_blank")

  def highlighted(modifiers: Modifier[Anchor]*) =
    a(className := "link-highlight", modifiers, target := "_blank")

  def button(text: String, targetUrl: String, anchorModifiers: Modifier[Anchor]*)(buttonModifiers: Modifier[Button]*): Anchor =
    a(
      href := targetUrl,
      target := "_blank",
      Buttons.shiny(text, buttonModifiers),
      anchorModifiers
    )
