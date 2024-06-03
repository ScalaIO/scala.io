package io.scala.views

import com.raquo.laminar.api.L._
import io.scala.IndexPage
import io.scala.data.TalksHistory
import io.scala.modules.SpeakerCard
import io.scala.modules.elements._
import io.scala.modules.layout._

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
            "This edition brings us together in Nantes for two days, with a single-session structure and a large community space. With a great venue, wonderful speakers, and a lot of surprises, we are looking forward to meeting you there!"
          )
        ),
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
      span(
        className := "page-title",
        "Speaker Gallery"
      ),
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
        "November 2024 - ",
        span(
          className := "event-town",
          "Paris"
        ),
        " @ ",
        a(
          className := "event-location",
          href      := "/venue",
          "Epita"
        )
      )
    )
  )
}
