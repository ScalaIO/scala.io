package io.scala
package modules.layout

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.{window, HTMLButtonElement, HTMLElement}

import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Links
import io.scala.svgs.Icons

object Header {
  val logo: ReactiveHtmlElement[HTMLButtonElement] = button(
    className  := "logo",
    aria.label := "home page",
    Icons.logo(),
    Page.navigateTo(IndexPage())
  )

  val burgerClicked: Var[Boolean] = Var(false)
  window.addEventListener(
    "click",
    _ => burgerClicked.set(false)
  )

  def render: ReactiveHtmlElement[HTMLElement] =
    headerTag(
      logo,
      div(
        display.flex,
        alignItems.center,
        inlineLinks,
        burgerMenu,
        Buttons.shiny(
          "Tickets",
          marginLeft := "0.5em",
          padding    := "0.5rem 0.5rem",
          Page.navigateTo(IndexPage(), Some("tickets")),
          disabled := true
        )
      )
    )

  private def links =
    Seq(
      "Sessions"        -> SessionsPage(),
      "Sponsors"        -> SponsorsPage(),
      "Venue"           -> VenuePage,
      "Schedule"        -> SchedulePage(),
      "Scala-FR events" -> EventsPage,
      "FAQ"             -> FAQPage
    ).map(Links.innerPage)

  inline def inlineLinks = div(
    className := "links inline",
    links
  )

  def burgerMenu =
    Buttons.dropdown("links", burgerClicked.signal)(
      Icons.burger,
      onClick.stopImmediatePropagation.mapTo(!burgerClicked.now()) --> burgerClicked
    )(
      links
    )
}
