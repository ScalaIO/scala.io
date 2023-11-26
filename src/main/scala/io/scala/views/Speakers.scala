package io.scala.views

import io.scala.Lexicon
import io.scala.Page.*
import io.scala.domaines.{Kind, Speaker, Talk}
import io.scala.modules.{ClassyButton, Line, SpeakerCard, SpeakerModal, Title}
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}

case object Speakers extends View {
  private val selectedSpeaker: Var[Option[Speaker]] = Var(None)

  override def body: HtmlElement = div(
    Title("Speakers"),
    p(
      Lexicon.Speakers.catchPhrase,
      className := "catch-phrase"
    ),
    ClassyButton(Lexicon.Speakers.callToAction),
    Line(margin = 55),
    div(
      Lexicon.Speakers.speakers.map(SpeakerCard(_, selectedSpeaker)),
      className := "speakers__cards"
    ),
    child <-- selectedSpeaker.signal.map {
      case None          => emptyNode
      case Some(speaker) => SpeakerModal(speaker, selectedSpeaker)
    },
    className := "container"
  )
}
