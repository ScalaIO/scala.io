package io.scala.views

import io.scala.Lexicon
import io.scala.Page.*
import io.scala.domaines.{Kind, Speaker, Talk}
import io.scala.modules.{ClassyButton, Line, SpeakerCard, SpeakerModal, Title}
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom.Element

case object Speakers extends View {
  private val selectedSpeaker: Var[Option[Speaker]] = Var(None)

  override def body: HtmlElement = sectionTag(
    className := "container",
    Title("Speakers"),
    p(
      Lexicon.Speakers.catchPhrase,
      className := "catch-phrase"
    ),
    a(
      ClassyButton(Lexicon.Speakers.callToAction),
      href   := "https://www.papercall.io/scalaio-2024-nte",
      target := "_blank"
    ),
    Line(margin = 55),
    div(
      Lexicon.Speakers.speakers.map(SpeakerCard(_, selectedSpeaker)),
      className := "card-container"
    ),
    SpeakerModal(selectedSpeaker),
    onClick.compose {
      _.withCurrentValueOf(selectedSpeaker.signal)
        .collect { case (event, Some(_)) =>
          event.target match
            case e: Element if e.classList.contains("card-overlay") =>
              selectedSpeaker.set(None)
        }
    } --> ()
  )
}
