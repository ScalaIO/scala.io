package io.scala.views

import io.scala.Lexicon
import io.scala.SponsorsPage
import io.scala.data.SponsorsHistory
import io.scala.modules.elements._

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

case object SponsorsList extends ReactiveView[SponsorsPage] {
  def body(args: Signal[SponsorsPage]): HtmlElement = {
    sectionTag(
      className := "container",
      Titles("Sponsors"),
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
      div(
        className := "container",
        children <-- args.map(arg =>
          SponsorsHistory
            .sponsorsForConf(arg.conference)
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
                    sponsors.map: sponsor =>
                      SponsorLogo(sponsor).amend(
                        styleAttr := s"grid-column: span ${sponsor.gridCol}; grid-row: span ${sponsor.gridRow}"
                      )
                  )
                ),
                Line.separator(width = 100, height = 2)
              )
            }
            .dropRight(1)
        )
      )
    )
  }
}
