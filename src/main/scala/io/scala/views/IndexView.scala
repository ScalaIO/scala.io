package io.scala.views

import io.scala.Lexicon.Header.talks
import io.scala.Page.*
import io.scala.data.SpeakersInfo
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.*
import io.scala.modules.layout.*
import io.scala.svgs.Check
import io.scala.svgs.Ticket

import com.raquo.laminar.api.L.{*, given}

case object IndexView extends GenericView {

  def render(withDraft: Boolean): HtmlElement =
    div(
      div(
        className := "fullscreen",
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
      p("""The conference is organized by Scala supporters from the community,
          | and provides a great opportunity to meet with other enthusiasts and practitioners.""".stripMargin,
        "You can find the videos of the past editions (200+) on our ",
        a(className := "basic-link", href := "https://www.youtube.com/@scalaio/videos", "YouTube channel"),
        "."
      ),
      br(),
      p("This edition brings us together in Nantes for two days, with a single-session structure and a large community space. With a great venue, wonderful speakers, and a lot of surprises, we are looking forward to meeting you there!"),
    ),
    div(
      className := "container",
      Title("Tickets"),
      div(
        className := "tickets",
        YurPlan()
        // div(
        //   className := "group-ticket",
        //   Ticket(),
        //   p("Group tickets"),
        //   ticketPerks
        //     .map(e =>
        //       List(
        //         e,
        //         Line(margin = 10)
        //       )
        //     )
        //     .flatten
        //     .dropRight(1),
        //   ShinyButton.linkLight("Email us!").amend(href := s"mailto:contact@scala.io"),
        //   p(
        //     className := "small",
        //     "Style stolen from ",
        //     u(a(href := "https://www.scalar-conf.com/tickets", "scalar-conf.com/tickets"))
        //   )
        // )
      )
    ),
    Separator(),
    speakerGallery(withDraft)
  )

  val ticketPerks = List(
    h3("Ask for a group discount!"),
    div(Check(), "access to 2 conference days"),
    div(Check(), "access to the community party"),
    div(Check(), "opportunity to meet Scala and FP experts"),
    div(Check(), "(dev|tech)-friendly networking"),
    div(Check(), "special offer for your team")
  )

  def speakerGallery(withDraft: Boolean) =
    val speakers =
      if withDraft then SpeakersInfo.allSpeakers
      else SpeakersInfo.allSpeakers.filter(_.confirmed)
    div(
      className := "container speaker-gallery",
      span(
        className := "page-title",
        "Speaker Gallery"
      ),
      div(
        className := "card-container",
        speakers.map(SpeakerCard(_))
      )
    )

  lazy val hero: Div = div(
    className       := "hero",
    backgroundImage := "url(../images/index-hero.webp)",
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
