package io.scala.views

import io.scala.Lexicon
import io.scala.Page.*
import io.scala.domaines.{Kind, Speaker, Talk}
import io.scala.modules.{SpeakerCard, SpeakerModal}
import io.scala.modules.elements.Line
import io.scala.modules.elements.ClassyButton
import io.scala.modules.elements.Title
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom.Element
import org.scalajs.dom.console

case object SpeakersList extends View {
  private val selectedSpeaker: Var[Option[Speaker]] = Var(None)

  def bodyContent(speakers: List[Speaker])= sectionTag(
    className := "container",
    Title("Speakers"),
    p(
      Lexicon.Speakers.catchPhrase,
      className := "catch-phrase"
    ),
    Line(margin = 55),
    div(
      speakers.map(SpeakerCard(_, selectedSpeaker)),
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

  def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(Lexicon.Speakers.speakers)
    else bodyContent(Lexicon.Speakers.speakers.filter(_.confirmed))
}
