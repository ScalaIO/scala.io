package io.scala.modules.layout

import io.scala.{Lexicon, Page}
import io.scala.{VenuePage, IndexPage, SponsorsPage, SchedulePage, SpeakersListPage}
import io.scala.svgs.Burger
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.UIEvent
import org.scalajs.dom.html
import io.scala.utils.Screen
import io.scala.modules.elements.ShinyButton

object Header {
  inline def width            = io.scala.utils.Screen.fromWidth(dom.window.innerWidth)
  val screenKind: Var[Screen] = Var(width)
  var burgerClicked           = Var(false)
  private var previousScreen  = screenKind.now()

  dom.window.onresize = { _ =>
    val newScreen = width
    if newScreen != previousScreen then
      screenKind.set(newScreen)
      previousScreen = newScreen
  }

  lazy val render =
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
    Lexicon.Header.speakers -> SpeakersListPage(),
    Lexicon.Header.sponsors -> SponsorsPage,
    Lexicon.Header.venue    -> VenuePage,
    Lexicon.Header.schedule -> SchedulePage()
  )

  def links = ul(
    linksPage.map(Navlink _),
    className := "navbar-links"
  )
  def mobileLinks = ul(
    linksPage.map(Navlink _),
    className := "header__sidenav"
  )

  val logo = div(
    className := "logo",
    Logo(), Page.navigateTo(IndexPage)
  )

  val buyTicket= a(
      idAttr := "buy-ticket",
      ShinyButton(Lexicon.Header.buyTicket),
      href := "https://yurplan.com/events/Scala-IO-2024/115152",
      target := "_blank",
    )

  def computerPlusScreen = headerTag(
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
