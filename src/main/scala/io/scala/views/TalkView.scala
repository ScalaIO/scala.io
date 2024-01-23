package io.scala.views

import io.scala.domaines.Talk
import io.scala.modules.elements.Title

import com.raquo.laminar.api.L.{*, given}

class TalkView(talk: Talk) extends SimpleView:
  override def title: String = talk.title

  def body(withDraft: Boolean): HtmlElement =
    sectionTag(
      className := "container talk",
      Title(talk.title),
      div(
        marginBottom := "2rem",
        className := "paragraph",
        talk.renderDescription
      ),
      Title(if (talk.speakers.size > 1) "Speakers" else "Speaker"),
      div(
        className := "card-container",
        talk.speakers.map(SpeakerView(_).body)
      )
    )
