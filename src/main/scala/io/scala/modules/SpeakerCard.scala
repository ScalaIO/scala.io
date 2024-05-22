package io.scala
package modules

import com.raquo.laminar.api.L._
import io.scala.data.TalksInfo.talksBySpeaker
import io.scala.domaines.Social
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.svgs.Icons

object SpeakerCard {
  def apply(speaker: Speaker) =
    div(
      className := "speaker-card",
      img(
        src       := speaker.photoPath,
        className := "photo",
        alt       := s"${speaker.name}'s profile picture"
      ),
      div(
        div(
          talksBySpeaker(speaker).flatMap: talk =>
            Array(
              span(
                talk.kind.toString,
                className := talk.kind.toStyle
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
        p(speaker.company),
        className := "body"
      ),
      linkToTalks(talksBySpeaker(speaker))
    )

  def linkToTalks(talks: Seq[Talk]) =
    talks.map { talk =>
      button(
        className := "link classy-button classy-button-highlight",
        "See talk ", // ! Problem if >= 2 talks
        Icons.goTo,
        Page.navigateTo(TalkPage(talk.slug))
      )
    }
}
