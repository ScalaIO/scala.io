package io.scala
package modules

import io.scala.data.TalksInfo.{allTalks, talksBySpeaker}
import io.scala.domaines.{Social, Speaker, Talk}
import io.scala.modules.elements.ClassyButton

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom
import org.scalajs.dom.console
import org.scalajs.dom.html.Anchor
import org.scalajs.dom.svg.{Path, SVG}
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
        className := "link classy-button button-highlight",
        "See talk ", // ! Problem if >= 2 talks
        Icons.goTo,
        Page.navigateTo(TalkPage(talk.slug))
      )
    }
}
