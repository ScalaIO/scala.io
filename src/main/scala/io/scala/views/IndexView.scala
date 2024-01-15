package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.Lexicon.Header.talks
import io.scala.Page.*
import io.scala.data.SpeakersInfo
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.Separator
import io.scala.modules.elements.Title
import io.scala.modules.elements.YurPlan
import io.scala.modules.layout.Footer
import io.scala.modules.layout.Headband
import io.scala.modules.layout.Header

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

  val description =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam convallis ligula sem, eu tincidunt augue dictum in. Aliquam euismod sit amet nibh sit amet gravida. Ut fringilla vitae ligula sed dapibus. Nunc sed arcu sed leo molestie auctor non id orci. Fusce nulla ipsum, egestas vel pharetra nec, maximus eu velit. Aenean egestas, ipsum ac porta scelerisque, sapien dolor elementum dolor, posuere dapibus nisi dolor cursus augue. Integer pellentesque urna et neque faucibus aliquet. Sed posuere nunc sed erat dictum suscipit. In hac habitasse platea dictumst."

  def body(withDraft: Boolean): HtmlElement = sectionTag(
    className := "index",
    div(className := "container description",

      h2("Exchanging Ideas"),
      p(
        """
          Scala.IO is a conference for people having
           interest in the Scala ecosystem or simply
           being curious about the language, usages.

          """),
      p(
        """The conference is organized by Scala supporters from the community,
          | and provides a great opportunity to meet with other enthusiasts and practitioners.""".stripMargin),


      h2("2024 - Nantes"),
      p("For this edition, we are meeting in Nantes, for 2 days, one track, and a large community space.")
    )

,
    div(
      className := "container description",
      Title("Tickets"),
      YurPlan()
    ),
    Separator(
      h2(textAlign.center, "Small separator to make it look good")
    ),
    speakerGallery(withDraft)
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

  lazy val hero = div(
    className := "hero",
    backgroundImage := "url(../images/index-hero.webp)",
    div(
      className := "overlay",
      div(
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
            href := "/venue",
            "Le Palace"
          )
        )
      )
    )
  )

  def stat(number: String, description: String) =
    div(
      className := "item",
      div(
        className := "stat-number",
        number
      ),
      div(
        className := "stat-text",
        description
      )
    )
}
