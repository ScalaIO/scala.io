package io.scala
package views

import io.scala.data.TalksInfo
import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.Title
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase

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
