package io.scala.views

import io.scala.Lexicon
import io.scala.Page.*
import io.scala.domaines.{Kind, Speaker, Talk}
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.Line
import io.scala.modules.elements.ClassyButton
import io.scala.modules.elements.Title
import io.scala.views.View

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.Element
import org.scalajs.dom.console

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
    if withDraft then bodyContent(Lexicon.Speakers.speakers)
    else bodyContent(Lexicon.Speakers.speakers.filter(_.confirmed))
}
