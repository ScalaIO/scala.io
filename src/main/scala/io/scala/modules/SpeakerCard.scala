package io.scala
package modules

import com.raquo.laminar.api.L.*

import io.scala.models.Session
import io.scala.models.Session.Speaker
import io.scala.models.Social
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Buttons.*

object SpeakerCard {
  def apply(speaker: Speaker, talksInfo: List[Session.BasicInfo], conference: String) =
    div(
      className := "speaker-card",
      img(
        src       := speaker.photoRelPath,
        className := "photo",
        alt       := s"${speaker.name}'s profile picture"
      ),
      div(
        div(
          talksInfo.map(info =>
            span(
              info.kind.toString,
              className := info.kind.toStyle
            )
          ),
          div(
            Social.renderIcons(speaker.socials, speaker.name),
            className := "socials"
          ),
          className := "subtitle"
        ),
        h2(speaker.name, className := "card-title"),
        p(speaker.job, className   := "job"),
        className := "body"
      ),
      linksToTalks(talksInfo, conference)
    )

  def linksToTalks(infos: List[Session.BasicInfo], conference: String) =
    def linkButton(info: Session.BasicInfo) =
      val text = if info.kind == Session.Kind.Workshop then "Workshop" else "Talk"
      Buttons
        .classyNew(className := "link", text, Page.navigateTo(SessionPage(conference, info.slug)))
        .important

    div(
      display.flex,
      flexDirection.row,
      infos match
        case Nil         => emptyNode
        case head :: Nil => linkButton(head)
        case _           => infos.map(linkButton)
    )
}
