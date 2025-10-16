package io.scala.views

import com.raquo.laminar.api.L.*

import io.scala.modules.elements.{Links, Titles}

object VenueView extends SimpleView {

  def body(): HtmlElement = sectionTag(
    className := "container venue",
    Titles("Our Venue"),
    div(
      Links.highlighted(
        "La Grande Crypte - 69 bis Rue Boissière, Paris",
        href           := "https://maps.app.goo.gl/GuLkQLZeDLGeVzCz9",
        textDecoration := "underline"
      ),
      p("Closest subway stations: Victor Hugo (line 2), Boissière (line 6)")
    ),
    Titles("Community Party"),
    div(
      Links.highlighted(
        "Le Cabanon Pigalle - 14 rue Jean-Baptiste Pigalle",
        href           := "https://maps.app.goo.gl/KtCXf3zHJLmuhRYr8",
        textDecoration := "underline"
      ),
      p("25 minutes from La Grande Crypte via the subway line 2 between Victor Hugo and Blanche stations.")
    )
  )
}
