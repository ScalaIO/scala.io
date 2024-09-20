package io.scala
package modules.layout

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.window

import io.scala.modules.elements.Links
import io.scala.svgs.Icons

object Header {
  val logo = button(
    className  := "logo",
    aria.label := "home page",
    Icons.logo(),
    Page.navigateTo(IndexPage())
  )

  val burgerClicked = Var(false)
  window.onclick = _ => burgerClicked.set(false)

  def render =
    headerTag(
      logo,
      inlineLinks.amend(media := "screen and (min-width: 768px)"),
      burgerMenu.amend(media  := "screen and (max-width: 768px)")
    )

  private val linksPage: Seq[(String, Page)] = Seq(
    "Sessions"        -> SessionsPage(),
    "Sponsors"        -> SponsorsPage(),
    "Venue"           -> VenuePage,
    "Schedule"        -> SchedulePage(),
    "Scala-FR events" -> EventsPage,
    "FAQ"             -> FAQPage
  )

  inline def inlineLinks = div(
    className := "links inline",
    linksPage.map(Links.innerPage)
  )

  def burgerMenu =
    div(
      className := "links burger",
      button(
        Icons.burger,
        onClick.stopImmediatePropagation.mapTo(!burgerClicked.now()) --> burgerClicked,
        className  := "burger",
        aria.label := "menu"
      ),
      div(
        className := "sidenav",
        linksPage.map(Links.innerPage),
        display <-- burgerClicked.signal.map {
          case true  => "block"
          case false => "none"
        }
      )
    )
}
