package io.scala.views

import io.scala.data.TalksInfo
import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.modules.SpeakerCard
import io.scala.modules.elements.Title
import io.scala.modules.profilePlaceholder
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase

import com.raquo.laminar.api.L.{*, given}

class TalkView(talk: Talk) extends SimpleView:
  override def title: String = talk.title

  def body(withDraft: Boolean): HtmlElement =
    sectionTag(
      className := "container talk",
      Title("Talk"),
      div(
        div(
          className := "paragraph",
          h2(talk.title),
          talk.renderDescription
        )
      ),
      Title(if (talk.speakers.size > 1) "Speakers" else "Speaker"),
      div(
        className := "card-container",
        talk.speakers.map(SpeakerView(_).body)
      )
    )
