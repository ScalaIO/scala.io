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
  inline def width            = Screen.fromWidth(dom.window.innerWidth)
  val screenKind: Var[Screen] = Var(width)
  var burgerClicked           = Var(false)
  private var previousScreen  = screenKind.now()

  dom.window.onresize = { _ =>
    val newScreen = width
    if newScreen != previousScreen then
      screenKind.set(newScreen)
      previousScreen = newScreen
  }

  def apply() =
    screenKind.signal.map {
      case Screen.Computer => computerPlusScreen
      case Screen.Tablet   => tabletScreen
      case Screen.Mobile   => mobileScreen
    }

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
    className := "navbar-links"
  )
  def mobileLinks = ul(
    linksPage.map(Navlink _),
    className := "header__sidenav"
  )

  def computerPlusScreen = headerTag(
    div(Logo(), Page.navigateTo(Page.IndexPage)),
    links,
    ShinyButton(Lexicon.Header.buyTicket)
  )

  def tabletScreen = headerTag(
    className := "navbar-tablet-view",
    div(
      div(Logo(), Page.navigateTo(Page.IndexPage)),
      ShinyButton(Lexicon.Header.buyTicket),
      className := "navbar-links-tablet"
    ),
    links
  )

  def mobileScreen = headerTag(
    className := "navbar-links-mobile",
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
      className := "navbar-links-mobile-buttons"
    )
  )
}
