package io.scala
package modules.layout

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.modules.elements.ShinyButton
import io.scala.svgs.Icons
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar
import org.scalajs.dom
import org.scalajs.dom.window

object Header {
  var burgerClicked = Var(false)
  window.onclick = _ =>
    burgerClicked.update {
      case true  => false
      case false => false
    }

  lazy val render =
    screenVar.signal.map {
      case Screen.Mobile => mobileScreen
      case Screen.Tablet => tabletScreen
      case _             => laptopPlusScreen
    }

  private def navLink(name: String, page: Page): Anchor =
    a(
      name,
      className := "link",
      Page.navigateTo(page)
    )

  private val linksPage: Seq[(String, Page)] = Seq(
    Lexicon.Header.talks    -> TalksPage(),
    Lexicon.Header.sponsors -> SponsorsPage,
    Lexicon.Header.venue    -> VenuePage,
    Lexicon.Header.schedule -> SchedulePage(),
    "Scala-FR events"       -> EventsPage,
    "FAQ"                   -> FAQPage,
    "CoC"                   -> CoCPage
  )

  def links = div(
    className := "links",
    span(
      className := "accordion",
      "CONFERENCE"
    ),
    linksPage.map(navLink(_, _))
  )
  def mobileLinks = div(
    linksPage.map(navLink(_, _)),
    className := "sidenav"
  )

  val logo = button(
    className  := "logo",
    aria.label := "home page",
    Icons.logo(),
    Page.navigateTo(IndexPage())
  )

  val buyTicket = ShinyButton
    .link(Lexicon.Header.buyTicket)
    .amend(
      idAttr := "buy-ticket",
      href   := "https://yurplan.com/events/Scala-IO-2024/115152",
      target := "_blank"
    )

  def laptopPlusScreen = headerTag(
    className := "navbar laptop",
    logo,
    links
  )

  def tabletScreen = headerTag(
    className := "navbar tablet",
    div(logo),
    links
  )

  def mobileScreen = headerTag(
    className := "navbar mobile",
    logo,
    div(
      button(
        Icons.burger,
        onClick.stopImmediatePropagation.mapTo(!burgerClicked.now()) --> burgerClicked,
        className  := "burger",
        aria.label := "menu"
      ),
      div(
        child <-- burgerClicked.signal.map {
          case true  => mobileLinks
          case false => emptyNode
        }
      )
    )
  )
}
