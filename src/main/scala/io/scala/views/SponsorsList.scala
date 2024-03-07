package io.scala.views

import io.scala.Lexicon
import io.scala.data.SponsorsInfo
import io.scala.modules.elements.*

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement

case object SponsorsList extends SimpleView {
  val sponsorList: Div = div(
    className := "container",
    SponsorsInfo.allSponsors
      .groupBy(_.rank)
      .toSeq
      .sortBy(_._1)
      .flatMap { case (rank, sponsors) =>
        List(
          div(
            className := "container sponsor-kind",
            h2(
              className := "title",
              s"${rank.title}"
            ),
            div(
              className := s"card-container ${rank.css}",
              sponsors.map(SponsorLogo(_))
            )
          ),
          Line.separator(width = 100, height = 2)
        )
      }
      .dropRight(1)
  )

  def body(): HtmlElement = {
    sectionTag(
      className := "container",
      Title("Sponsors"),
      p(
        Lexicon.Sponsors.catchPhrase,
        className := "catch-phrase"
      ),
      div(
        a(
          ClassyButton(Lexicon.Sponsors.callToAction),
          href   := "mailto:contact@scala.io",
          target := "_blank"
        ),
        // ClassyButton(
        //   Lexicon.Sponsors.callToBrochure,
        //   kind = ButtonKind.Href(Lexicon.Sponsors.brochureUrl)
        // ),
        className := "sponsors__buttons"
      ),
      Line(margin = 55),
      sponsorList
    )
  }
}
