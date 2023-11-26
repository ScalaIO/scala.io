package io.scala.views

import io.scala.Lexicon
import io.scala.Page.*
import io.scala.domaines.{Kind, Speaker, Talk}
import io.scala.modules.{ClassyButton, Line, SpeakerCard, SponsorLogo, Title}
import io.scala.utils.ButtonKind
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

case object Sponsors extends View {

  override def body: HtmlElement = div(
    Title("Sponsors"),
    p(
      Lexicon.Sponsors.catchPhrase,
      className := "catch-phrase"
    ),
    div(
      ClassyButton(Lexicon.Sponsors.callToAction),
      ClassyButton(
        Lexicon.Sponsors.callToBrochure,
        kind = ButtonKind.Href(Lexicon.Sponsors.brochureUrl),
        // isImportant = false
      ),
      className := "sponsors__buttons"
    ),
    Line(margin = 55),
    div(
      Lexicon.Sponsors.sponsors.groupBy(_.rank).toSeq.sortBy(_._1).map { case (rank, sponsors) =>
        div(
          h2(
            s"${rank.title} sponsors",
            className := "sponsor-kind__title"
          ),
          div(
            sponsors.map(SponsorLogo.apply),
            className := "sponsor-kind__logos"
          ),
          className := "sponsor-kind"
        )
      },
      className := "all-sponsors"
    ),
    className := "container"
  )
}
