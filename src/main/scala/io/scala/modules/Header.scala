package io.scala.modules

import io.scala.{Lexicon, Page}
import io.scala.svgs.Burger
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.UIEvent
import org.scalajs.dom.html
object Header {
  var burgerClicked = Var(false)

  def apply(width: Double) =
    dom.console.log(s"Width: $width")
    if width >= 750 then computerPlusScreen
    else if width >= 450 then tabletScreen
    else mobileScreen

  private def Navlink(name: String, page: Page): Li =
    li(
      name,
      className := "header__navbar__link",
      Page.navigateTo(page)
    )

  private val linksPage = Seq(
    Lexicon.Header.speakers -> Page.SpeakersPage,
    Lexicon.Header.sponsors -> Page.SponsorsPage,
    Lexicon.Header.venue    -> Page.IndexPage,
    Lexicon.Header.schedule -> Page.SchedulePage
  )

  def links = ul(
    linksPage.map(Navlink _),
    className := "header__navbar"
  )
  def mobileLinks = ul(
    linksPage.map(Navlink _),
    className := "header__sidenav"
  )

  def computerPlusScreen = headerTag(
    div(Logo(), Page.navigateTo(Page.IndexPage)),
    links,
    ShinyButton(Lexicon.Header.buyTicket),
    className := "header__navbar"
  )

  def tabletScreen = headerTag(
    className := "tablet",
    div(
      div(Logo(), Page.navigateTo(Page.IndexPage)),
      ShinyButton(Lexicon.Header.buyTicket),
      className := "header__navbar-tablet"
    ),
    links
  )

  def mobileScreen = headerTag(
    div(Logo(), Page.navigateTo(Page.IndexPage)),
    div(
      ShinyButton(Lexicon.Header.buyTicket),
      button(
        Burger(),
        onClick.mapTo(!burgerClicked.now()) --> burgerClicked,
        className := "header__burger"
      ),
      div(
        child <-- burgerClicked.signal.map {
          case true  => mobileLinks.amend(onClick.mapTo(false) --> burgerClicked)
          case false => emptyNode
        }
      ),
      className := "header__navbar-mobile-buttons"
    ),
    className := "header__navbar-mobile"
  )
}
