package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.Lexicon
import io.scala.data.SpeakersInfo
import io.scala.domaines.*
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.*
import io.scala.views.View

case object SpeakersList extends View {
  def bodyContent(speakers: List[Speaker])= sectionTag(
    className := "container",
    Title("Speakers"),
    p(
      Lexicon.Speakers.catchPhrase,
      className := "catch-phrase"
    ),
    Line(margin = 55),
    div(
      speakers.map(SpeakerCard(_)),
      className := "card-container"
    ),
  )

  def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(SpeakersInfo.allSpeakers)
    else bodyContent(SpeakersInfo.allSpeakers.filter(_.confirmed))
}
