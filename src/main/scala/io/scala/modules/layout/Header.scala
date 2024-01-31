package io.scala
package modules.layout

import io.scala.modules.elements.ShinyButton
import io.scala.svgs.Burger
import io.scala.svgs.Logo
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.UIEvent
import org.scalajs.dom.html
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
      className := "header__navbar__link",
      Page.navigateTo(page)
    )

  private val linksPage: Seq[(String, Page)] = Seq(
    Lexicon.Header.talks    -> TalksPage(),
    Lexicon.Header.sponsors -> SponsorsPage,
    Lexicon.Header.venue    -> VenuePage,
    Lexicon.Header.schedule -> SchedulePage(),
    "FAQ"                   -> FAQPage
  )

  def links = div(
    linksPage.map(navLink _),
    className := "navbar-links"
  )
  def mobileLinks = div(
    linksPage.map(navLink _),
    className := "header__sidenav"
  )

  val logo = button(
    className  := "logo",
    aria.label := "home page",
    Logo.apply(),
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
    logo,
    links,
    buyTicket
  )

  def tabletScreen = headerTag(
    className := "navbar-tablet-view",
    div(
      logo,
      buyTicket,
      className := "navbar-links-tablet"
    ),
    links
  )

  def mobileScreen = headerTag(
    className := "navbar-links-mobile",
    logo,
    div(
      buyTicket,
      button(
        Burger(),
        onClick.stopImmediatePropagation.mapTo(!burgerClicked.now()) --> burgerClicked,
        className  := "header__burger",
        aria.label := "menu"
      ),
      div(
        child <-- burgerClicked.signal.map {
          case true  => mobileLinks
          case false => emptyNode
        }
      ),
      className := "navbar-links-mobile-buttons"
    )
  )
}
