package io.scala.views

import com.raquo.laminar.api.L.*
import scala.collection.SortedMap

import io.scala.IndexPage
import io.scala.Page
import io.scala.VenuePage
import io.scala.data.SessionsHistory
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
            "This edition brings us together in Paris for two days, with a multi-session structure and a large community space. With a great venue, wonderful speakers, and a lot of surprises, we are looking forward to meeting you there!"
          )
        ),
        Separator(),
        tickets,
        Separator(),
        speakerGallery(args)
      )
    )

  def speakerGallery(args: Signal[IndexPage]) =
    def speakers(conf: Option[String]) =
      SortedMap
        .from(
          SessionsHistory
            .sessionsForConf(conf)
            .filter(_.info.confirmed)
            .flatMap: talk =>
              talk.speakers.map((_, talk.info))
            .groupMap(_._1)(_._2)
        )
        .toSeq

    div(
      className := "container speaker-gallery",
      Titles("Speaker Gallery"),
      div(
        className := "card-container",
        children <-- args.map: page =>
          speakers(page.conference).map(SpeakerCard(_, _, SessionsHistory.getConfName(page.conference)))
      )
    )

  lazy val hero: Div = div(
    className := "hero",
    div(
      className := "overlay",
      h2(className := "title", "Connecting Scala Enthusiasts!"),
      h3(
        className := "event-date-location",
        "07 & 08 November 2024 - ",
        span(className := "event-town", "Paris"),
        " @ ",
        a(className := "event-location", Page.navigateTo(VenuePage), "Epita")
      ),
      Links.button("Get your ticket", "#tickets")(padding := "16px 24px")
    )
  )

  lazy val tickets: Div = div(
    idAttr("tickets"),
    className := "container",
    Titles("Tickets"),
    a(
      title                      := "Online ticketing",
      href                       := "https://www.billetweb.fr/shop.php?event=scalaio-paris-2024-epita",
      className                  := "shop_frame",
      target                     := "_blank",
      dataAttr("src")            := "https://www.billetweb.fr/shop.php?event=scalaio-paris-2024-epita",
      dataAttr("max-width")      := "100%",
      dataAttr("initial-height") := "1000",
      dataAttr("scrolling")      := "no",
      dataAttr("id")             := "scalaio-paris-2024-epita",
      dataAttr("resize")         := "1",
      "Online ticketing"
    ),
    scriptTag(
      `type` := "text/javascript",
      src    := "https://www.billetweb.fr/js/export.js"
    )
  )
}
