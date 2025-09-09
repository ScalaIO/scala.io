package io.scala.views

import com.raquo.laminar.api.L.*
import io.scala.Lexicon
import io.scala.SponsorsPage
import io.scala.data.SponsorsHistory
import io.scala.modules.elements.*

case object SponsorsList extends ReactiveView[SponsorsPage] {
  def body(args: Signal[SponsorsPage]): HtmlElement = {
    sectionTag(
      sponsors(args),
      div(
        Titles.medium("Become a sponsor"),
        className := "container",
        p(Lexicon.Sponsors.catchPhrase),
        div(
          Buttons.classy("Become a sponsor", kind = Buttons.ButtonKind.Href("mailto:contact@scala.io")),
          Buttons.classy(
            Lexicon.Sponsors.callToBrochure,
            kind = Buttons.ButtonKind.Href("/assets/sponsorship/2025.pdf")
          ),
          className := "sponsors__buttons"
        )
      ),
      div(
        width := "100%",
        display.flex,
        flexDirection.row,
        justifyContent.center,
        iframe(
          src := "https://docs.google.com/presentation/d/e/2PACX-1vQnOK-iGp4EJ9Etp_z5yZv8nxOH61cxg_j2xHrOvebNoD0x5mO9OL-2Fv8O5yKBNgWMC74eMgKXc3En/pubembed?start=false&loop=false",
          width  := "960px",
          height := "569px"
        )
      ),
      div(className := "container"), // spacer
      PreviousSponsors.h2Div
    )
  }

  def sponsors(args: Signal[SponsorsPage]) = div(
    Titles("Sponsors"),
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
              Titles.small(s"${rank.title}"),
              div(
                className := s"card-container ${rank.css}",
                sponsors.map: sponsor =>
                  SponsorLogo(sponsor).amend(
                    styleAttr := s"grid-column: span ${sponsor.gridCol}; grid-row: span ${sponsor.gridRow}"
                  )
              )
            ),
            Line.separator(width = 6, height = 2)
          )
        }
        .dropRight(1)
    )
  )
}
