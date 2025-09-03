package io.scala.views

import com.raquo.laminar.api.L.*
import io.scala.modules.elements.Titles

object VenueView extends SimpleView {

  def body(): HtmlElement = sectionTag(
    className := "container venue",
    Titles("Our Venue"),
    div(
      a(
        "La Grande Crypte - 69 bis Rue Boissière, Paris",
        href := "https://lagrandecrypte.com/"
      )
    ),
    Titles("Accommodations"),
    div(
      display.flex,
      flexDirection.row,
      flexWrap.wrap
      // div(
      //   Titles.small("Economical"),
      //   Lists.innerDiscs()
      // ),
      // div(
      //   Titles.small("Less economical :)"),
      //   Lists.innerDiscs(
      //   )
      // )
    )
  )
}
