package io.scala
package modules.layout

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.window

import io.scala.svgs.Icons
import io.scala.utils.Screen
import io.scala.utils.Screen.screenVar

object Header {
  var burgerClicked = Var(false)
  window.onclick = _ =>
    burgerClicked.update {
      case true  => false
      case false => false
    }

  lazy val render =
    screenVar.signal.map {
      case Screen.Laptop | Screen.Desktop => laptopPlusScreen
      case _                              => mobileScreen
    }

  private def navLink(name: String, page: Page): Anchor =
    a(
      name,
      className := "link",
      Page.navigateTo(page)
    )

  private val linksPage: Seq[(String, Page)] = Seq(
    Lexicon.Header.sessions -> SessionsPage(),
    Lexicon.Header.sponsors -> SponsorsPage(),
    Lexicon.Header.venue    -> VenuePage,
    // Lexicon.Header.schedule -> SchedulePage(),
    "Scala-FR events" -> EventsPage,
    "FAQ"             -> FAQPage
  )

  def links = div(
    className := "links",
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

  def laptopPlusScreen = headerTag(
    className := "navbar laptop",
    logo,
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
