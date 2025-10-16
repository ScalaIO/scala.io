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
      ),
    )
  )
}
