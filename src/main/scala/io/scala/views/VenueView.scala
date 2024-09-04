package io.scala.views

import com.raquo.laminar.api.L.*

import io.scala.modules.elements.Titles

object VenueView extends SimpleView {

  def body(): HtmlElement = sectionTag(
    className := "container venue",
    Titles("Our Venue"),
    div("For this edition, we are invited by EPITA, on their site 'Kremlin-Bicêtre'"),
    img(
      src := "images/places/epita-kb.jpeg"
    ),
    br(),
    Titles("Access"),

    div(
      "Campus Paris Kremlin-Bicêtre", br(),
      "Building 'Voltaire', ",
      a(href := "https://epimap.fr/kb/voltaire/4",
        "4th floor (epimap)"), br(),
      a(href := "https://www.google.com/maps/place//data=!4m2!3m1!1s0x47e6718002cb5611:0x581b7c8cd0a77c3e?sa=X&ved=1t:8290&ictx=111",
        "14-16 rue Voltaire", br(),
        "FR, 94270 Le Kremlin-Bicêtre"))

  )
}
