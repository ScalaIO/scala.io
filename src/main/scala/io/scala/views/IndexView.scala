package io.scala.views

import io.scala.Lexicon.Header.talks
import io.scala.Page.*
import io.scala.data.SpeakersInfo
import io.scala.data.SponsorsInfo
import io.scala.domaines.Sponsor
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.*
import io.scala.modules.layout.*

import com.raquo.laminar.api.L.{*, given}
import io.scala.utils.ButtonKind

case object IndexView extends GenericView {

  def render(withDraft: Boolean): HtmlElement =
    div(
      div(
        className := "fullscreen index",
        Headband.render,
        child <-- Header.render,
        IndexView.hero
      ),
      IndexView.body(withDraft),
      Footer.render
    )

  def body(withDraft: Boolean): HtmlElement = sectionTag(
    className := "index",
    div(
      className := "container description",
      Title("Exchanging Ideas"),
      p("""
          Scala.IO is a conference for people having
           interest in the Scala ecosystem or simply
           being curious about the language, usages.

          """),
      p(
        """The conference is organized by Scala supporters from the community,
          | and provides a great opportunity to meet with other enthusiasts and practitioners.""".stripMargin,
        "You can find the videos of the past editions (200+) on our ",
        a(className := "basic-link", href := "https://www.youtube.com/@scalaio/videos", "YouTube channel"),
        "."
      ),
      br(),
      p(
        "This edition brings us together in Nantes for two days, with a single-session structure and a large community space. With a great venue, wonderful speakers, and a lot of surprises, we are looking forward to meeting you there!"
      )
    ),
    Separator(),
    yearlySponsors,
    Separator(),
    speakerGallery(withDraft)
  )

  def speakerGallery(withDraft: Boolean) =
    val speakers =
      if withDraft then SpeakersInfo.allSpeakers
      else SpeakersInfo.allSpeakers.filter(_.confirmed)
    div(
      className := "container speaker-gallery",
      Title("Speaker Gallery"),
      div(
        className := "card-container",
        speakers.map(SpeakerCard(_))
      )
    )

  lazy val yearlySponsors =
    div(
      className := "container yearly-sponsors",
      Title("Yearly sponsors"),
      p("Find a nice sentence"),
      div(
        className := "card-container",
        SponsorsInfo.allSponsors.filter(_.rank == Sponsor.Rank.Yearly).map(SponsorLogo(_))
      ),
      ClassyButton(
        "Interested? Email us!",
        ButtonKind.Href("mailto:contact@scala.io"),
        true
      )
    )

  lazy val hero: Div = div(
    className := "hero",
    div(
      className := "overlay",
      h2(className := "title", "Connecting Scala Enthusiasts!"),
      h3(
        className := "event-date-location",
        "15th-16th February 2024 - ",
        span(
          className := "event-town",
          "Nantes"
        ),
        " @ ",
        a(
          className := "event-location",
          href      := "/venue",
          "Le Palace"
        )
      )
    )
  )
}
