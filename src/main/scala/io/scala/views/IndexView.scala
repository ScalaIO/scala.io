package io.scala.views

import com.raquo.laminar.api.L.*
import org.scalajs.dom.document

import io.scala.IndexPage
import io.scala.Page
import io.scala.SponsorsPage
import io.scala.data.SessionsHistory
import io.scala.data.SponsorsHistory
import io.scala.extensions.withLink
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.*

case object IndexView extends ReactiveView[IndexPage] {

  override def body(args: Signal[IndexPage]): HtmlElement =
    div(
      className := "index",
      IndexView.hero,
      sectionTag(
        div(
          className := "container",
          Titles("Exchanging Ideas"),
          Paragraphs.description(
            "Scala.IO is a conference for people having interest in the Scala ecosystem or simply being curious about the language, usages. The conference is organized by Scala supporters from the community, and provides a great opportunity to meet with other enthusiasts and practitioners. You can find the videos of the past editions (200+) on our ",
            Links.highlighted(href := "https://www.youtube.com/@scalaio/videos", "YouTube channel")
          ),
          Paragraphs.description(
            "This edition brings us together in Paris for one day, with a multi-session structure and a large community space. With a great venue, wonderful speakers, and a lot of surprises, we are looking forward to meeting you there!"
          )
        ),
        Separator(),
        tickets,
        Separator(),
        speakerGallery(args),
        Separator(),
        PreviousSponsors.h1Div
      )
    )

  def speakerGallery(args: Signal[IndexPage]) =
    def speakers(indexArgs: IndexPage) =
      SessionsHistory
        .sessionsForConf(indexArgs)
        .flatMap: talk =>
          talk.speakers.map((_, talk))
        .groupMap(_._1)(_._2)
        .mapValues(_.distinctBy(_.info.kind).sortBy(_.info.kind))
        .toSeq
        .sortBy(_._1)

    div(
      className := "container speaker-gallery",
      Titles("Speaker Gallery"),
      div(
        className := "card-container with-global-template",
        children <-- args.map: page =>
          speakers(page).map(SpeakerCard(_, _, SessionsHistory.getConfName(page.conference)))
      )
    )

  lazy val hero: Div = div(
    className := "hero",
    div(
      className := "overlay",
      Titles.main("Connecting Scala Enthusiasts!"),
      h2(
        className := "event-date-location",
        "17/10/2025 - Paris @ ",
        u("La Grande Crypte").withLink("https://lagrandecrypte.com/")
      ),
      div(
        className := "hero-buttons",
        Buttons
          .shiny(
            "Get your ticket!",
            onClick --> { _ => document.getElementById("tickets").scrollIntoView() }
          ),
        Buttons.shiny(Links.innerPage("Sponsor us!", SponsorsPage(None)))
      )
    )
  )

  lazy val tickets: Div = div(
    idAttr("tickets"),
    className := "container",
    Titles("Tickets"),
    div(
      a(
        title                      := "Online ticketing",
        href                       := "https://www.billetweb.fr/shop.php?event=scalaio-2025",
        className                  := "shop_frame",
        target                     := "_blank",
        dataAttr("src")            := "https://www.billetweb.fr/shop.php?event=scalaio-2025",
        dataAttr("max-width")      := "100%",
        dataAttr("initial-height") := "600",
        dataAttr("scrolling")      := "no",
        dataAttr("id")             := "scalaio-2025",
        dataAttr("resize")         := "1",
        "Online ticketing"
      ),
      scriptTag(
        `type` := "text/javascript",
        src    := "https://www.billetweb.fr/js/export.js"
      )
    )
  )

}
