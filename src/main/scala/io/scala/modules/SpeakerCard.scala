package io.scala
package modules

import com.raquo.laminar.api.L.*

import io.scala.domaines.Social
import io.scala.domaines.Talk
import io.scala.domaines.Talk.Speaker
import io.scala.svgs.Icons

object SpeakerCard {
  def apply(speaker: Speaker, talkInfo: Talk.BasicInfo, conference: String) =
    div(
      className := "speaker-card",
      img(
        src       := speaker.photoRelPath,
        className := "photo",
        alt       := s"${speaker.name}'s profile picture"
      ),
      div(
        div(
          span(
            talkInfo.kind.toString,
            className := talkInfo.kind.toStyle
          ),
          div(
            Social.renderIcons(speaker.socials, speaker.name),
            className := "socials"
          ),
          className := "subtitle"
        ),
        h2(
          speaker.name,
          className := "title"
        ),
        p(speaker.job),
        // p(speaker.company), //TODO: re-add company as a separate field
        className := "body"
      ),
      linkToTalks(talkInfo, conference)
    )

  def linkToTalks(info: Talk.BasicInfo, conference: String) =
    button(
      className := "link classy-button highlight",
      "See talk ", // ! Problem if >= 2 talks
      Icons.goTo,
      Page.navigateTo(TalkPage(conference, info.slug))
    )
}
