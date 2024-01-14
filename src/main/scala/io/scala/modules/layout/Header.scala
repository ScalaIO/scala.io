package io.scala.modules.layout

import io.scala.{BasicPage, Lexicon, Page, PageArg}
import io.scala.svgs.Burger
import io.scala.svgs.Logo
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.UIEvent
import org.scalajs.dom.html
import io.scala.utils.Screen
import io.scala.modules.elements.ShinyButton
import io.scala.utils.Screen.screenVar

object Header {
  var burgerClicked           = Var(false)

  lazy val render =
    screenVar.signal.map {
      case Screen.Mobile   => mobileScreen
      case Screen.Tablet   => tabletScreen
      case _ => laptopPlusScreen
    }

  private def Navlink(name: String, page: PageArg): Li =
    li(
      name,
      className := "header__navbar__link",
      Page.navigateTo(page)
    )

  private val linksPage = Seq(
    Lexicon.Header.talks -> BasicPage.Talks.toPageArg,
    Lexicon.Header.sponsors -> BasicPage.Sponsors.toPageArg,
    Lexicon.Header.venue    -> BasicPage.Venue.toPageArg,
    Lexicon.Header.schedule -> BasicPage.Schedule.toPageArg,
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
    Logo.apply(), Page.navigateTo(BasicPage.Index.toPageArg)
  )

  val buyTicket = a(
    idAttr := "buy-ticket",
    ShinyButton(Lexicon.Header.buyTicket),
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
