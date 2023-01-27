package io.scala.modules

import io.scala.{Lexicon, Page}
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Header {
  def apply(): Div = div(
    div(Logo(), Page.navigateTo(Page.IndexPage)),
    ul(
      Navlink(Lexicon.Header.speakers, Page.SpeakersPage),
      Navlink(Lexicon.Header.sponsors, Page.IndexPage),
      Navlink(Lexicon.Header.venue, Page.IndexPage),
      Navlink(Lexicon.Header.schedule, Page.IndexPage),
      className := "header__navbar"
    ),
    ShinyButton(Lexicon.Header.buyTicket),
    className := "header"
  )

  private def Navlink(name: String, page: Page): Li =
    li(
      name,
      className := "header__navbar__link",
      Page.navigateTo(page)
    )
}
