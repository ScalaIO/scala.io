package io.scala.views

import com.raquo.laminar.api.L.*

import io.scala.IndexPage
import io.scala.Page
import io.scala.VenuePage
import io.scala.data.TalksHistory
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.*
import io.scala.modules.layout.*

case object IndexView extends EmptyReactiveView[IndexPage] {

  override def render(args: Signal[IndexPage]): HtmlElement =
    div(
      div(
        className := "fullscreen index",
        child <-- Header.render,
        IndexView.hero
      ),
      sectionTag(
        className := "index",
        div(
          className := "container description",
          Titles("Exchanging Ideas"),
          p("""
                Scala.IO is a conference for people having
                 interest in the Scala ecosystem or simply
                 being curious about the language, usages.

                """),
          p(
            """The conference is organized by Scala supporters from the community,
                | and provides a great opportunity to meet with other enthusiasts and practitioners.""".stripMargin,
            "You can find the videos of the past editions (200+) on our ",
            Links.highlighted(href := "https://www.youtube.com/@scalaio/videos", "YouTube channel"),
            "."
          ),
          br(),
          p(
            "This edition brings us together in Paris for two days, with a multi-session structure and a large community space. With a great venue, wonderful speakers, and a lot of surprises, we are looking forward to meeting you there!"
          )
        ),
        Separator(),
        tickets,
        Separator(),
        speakerGallery(args)
      ),
      Footer.render
    )

  def speakerGallery(args: Signal[IndexPage]) =
    def speakers(conf: Option[String]) =
      TalksHistory
        .talksForConf(conf)
        .filter(_.speakers.forall(_.confirmed))
        .flatMap: talk =>
          talk.speakers.map((_, talk.info))
        .sortBy(_._1.name)

    div(
      className := "container speaker-gallery",
      Titles("Speaker Gallery"),
      div(
        className := "card-container",
        children <-- args.map:
          case IndexPage(draft, conf) =>
            speakers(conf).map(SpeakerCard(_, _, TalksHistory.getConfName(conf)))
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
        span(
          className := "event-town",
          "Paris"
        ),
        " @ ",
        a(
          className := "event-location",
          Page.navigateTo(VenuePage),
          "Epita"
        )
      )
    )
  )

  lazy val tickets: Div = div(
    className := "container",
    Titles("Tickets"),
    a(
      title                      := "Online ticketing",
      href                       := "https://www.billetweb.fr/shop.php?event=scalaio-paris-2024-epita",
      className                  := "shop_frame",
      target                     := "_blank",
      dataAttr("src")            := "https://www.billetweb.fr/shop.php?event=scalaio-paris-2024-epita",
      dataAttr("max-width")      := "100%",
      dataAttr("initial-height") := "600",
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
