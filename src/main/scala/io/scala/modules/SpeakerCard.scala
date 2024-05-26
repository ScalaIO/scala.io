package io.scala
package modules

import com.raquo.laminar.api.L._
import io.scala.data.TalksInfo.talksBySpeaker
import io.scala.domaines.Social
import io.scala.domaines.Talk.Speaker
import io.scala.svgs.Icons
import io.scala.domaines.Talk

object SpeakerCard {
  def apply(speaker: Speaker) =
    div(
      className := "speaker-card",
      img(
        src       := speaker.photoRelPath,
        className := "photo",
        alt       := s"${speaker.name}'s profile picture"
      ),
      div(
        div(
          talksBySpeaker(speaker).flatMap: talk =>
            Array(
              span(
                talk.info.kind.toString,
                className := talk.info.kind.toStyle
              ),
              div(
                Social.render(speaker.socials, speaker.name),
                className := "socials"
              )
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
      linkToTalks(talksBySpeaker(speaker))
    )

  def linkToTalks(talks: Seq[Talk]) =
    talks.map { talk =>
      button(
        className := "link classy-button highlight",
        "See talk ", // ! Problem if >= 2 talks
        Icons.goTo,
        Page.navigateTo(TalkPage(talk.info.slug))
      )
    }
}
