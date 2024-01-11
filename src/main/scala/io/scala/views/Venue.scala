package io.scala.views

import io.scala.Lexicon
import io.scala.modules.Line
import io.scala.modules.Title

import com.raquo.laminar.api.L.{*, given}

object Venue extends View {

  def body: HtmlElement = sectionTag(
    className := "container",
    Title("Venue"),
    p(
      Lexicon.Venue.catchPhrase,
      className := "catch-phrase"
    ),
    Line(margin = 55),
    div(
      className       := "hero",
      backgroundImage := "url(https://icilundi.fr/wp-content/uploads/2021/05/06/le-palace-atrium-scaled.jpg)",
      div(
        className := "overlay",
        div(
          h2(className := "index__subtitle", "Le Palace"),
          h3(
            className := "index__event-date-location",
            "4 Rue Voltaire, 44000 Nantes"
          )
        )
      )
    ),
    Line.separator(vMargin = 32, width = 80, height = 8),
    div(
      className := "venue-map-container",
      iframe(
        className := "venue-map",
        src := "https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d1355.095279080701!2d-1.5657394!3d47.2128536!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4805ed0c3af02b81%3A0xfe8747f744fd5b97!2sLe%20Palace%20_icilundi!5e0!3m2!1sfr!2sfr!4v1701301592089!5m2!1sfr!2sfr",
        loadingAttr := "lazy"
      )
    )
  )

}
