package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.Lexicon
import io.scala.domaines.Sponsor
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.ClassyButton
import io.scala.modules.elements.Line
import io.scala.modules.elements.SponsorLogo
import io.scala.modules.elements.Title
import io.scala.views.View

case object SponsorsList extends View {

  def body: HtmlElement = sectionTag(
    className := "container",
    Title("Sponsors"),
    p(
      Lexicon.Sponsors.catchPhrase,
      className := "catch-phrase"
    ),
    div(
      a(
        ClassyButton(Lexicon.Sponsors.callToAction),
        href := "mailto:contact@scala.io",
        target := "_blank"
      ),
      // ClassyButton(
      //   Lexicon.Sponsors.callToBrochure,
      //   kind = ButtonKind.Href(Lexicon.Sponsors.brochureUrl)
      // ),
      className := "sponsors__buttons"
    ),
    Line(margin = 55),
    div(
      Lexicon.Sponsors.sponsors
        .groupBy(_.rank)
        .toSeq
        .sortBy(_._1)
        .flatMap { case (rank, sponsors) =>
          List(
            div(
              h2(
                s"${rank.title}",
                className := "sponsor-kind__title"
              ),
              div(
                sponsors.map(SponsorLogo.apply),
                className := "card-container"
              ),
              className := "sponsor-kind"
            ),
            Line.separator(width = 75, height = 4)
          )
        }
        .dropRight(1),
      className := "all-sponsors"
    )
  )
}
