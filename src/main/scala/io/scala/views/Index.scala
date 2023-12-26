package io.scala.views

import io.scala.Page._
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.YurPlan

case object Index extends View {
  override def body: HtmlElement = sectionTag(
    div(
      className := "index-hero",
      backgroundImage := "url(../images/index-hero.jpeg)",
      div(
        className := "overlay",
        div(
          h2(className := "index__subtitle", "Your Scala Event in France !"),
          h3(
            className := "index__event-date-location",
            "15th-16th February 2024 - ",
            span(
              className := "index__event-town",
              "Nantes"
            ),
            " @ ",
            a(
              className := "index__event-location",
              href      := "https://maps.app.goo.gl/o22S4SjA2v11R6ef8",
              "Le Palace"
            )
          )
        )
      )
    ),
    YurPlan()
  )
}
