package io.scala.views

import io.scala.Lexicon.Header.talks
import io.scala.Page.*
import io.scala.data.SpeakersInfo
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.Separator
import io.scala.modules.elements.Title
import io.scala.modules.elements.YurPlan
import io.scala.modules.layout.{Footer, Headband, Header}
import org.scalajs.dom.console

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

  val description =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam convallis ligula sem, eu tincidunt augue dictum in. Aliquam euismod sit amet nibh sit amet gravida. Ut fringilla vitae ligula sed dapibus. Nunc sed arcu sed leo molestie auctor non id orci. Fusce nulla ipsum, egestas vel pharetra nec, maximus eu velit. Aenean egestas, ipsum ac porta scelerisque, sapien dolor elementum dolor, posuere dapibus nisi dolor cursus augue. Integer pellentesque urna et neque faucibus aliquet. Sed posuere nunc sed erat dictum suscipit. In hac habitasse platea dictumst."

  def body(withDraft: Boolean): HtmlElement = sectionTag(
    className := "index",
    div(className := "container description", Title("Who are we ?"), p(description)),
    Separator(
      span(
        className := "page-title",
        "Content"
      ),
      div(
        className := "container stats",
        stat("2", "Days"),
        stat("19", "Talks"),
        stat("150", "Attendees"),
        stat("1", "Track")
      )
    ),
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
    console.log(s"withDraft: $withDraft")
    val speakers =
      if withDraft then SpeakersInfo.allSpeakers
      else SpeakersInfo.allSpeakers.filter(_.confirmed)
    console.log(s"speakers: ${speakers.size}")
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
    className       := "hero",
    backgroundImage := "url(../images/index-hero.webp)",
    div(
      className := "overlay",
      div(
        h2(className := "title", "Your Scala Event in France !"),
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
            href      := "https://maps.app.goo.gl/o22S4SjA2v11R6ef8",
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
