package io.scala
package modules

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.extensions.grayOutIf
import io.scala.models.Session
import io.scala.models.Session.Speaker
import io.scala.models.Social
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Buttons.*
import org.scalajs.dom.HTMLDivElement

object SpeakerCard {
  def apply(speaker: Speaker, talks: List[Session], conference: String): ReactiveHtmlElement[HTMLDivElement] =
    div(
      className := "speaker-card",
      img(
        src       := speaker.photoRelPath,
        className := "photo",
        alt       := s"${speaker.name}'s profile picture"
      ),
      div(
        div(
          talks.map(t =>
            span(
              t.info.kind.toString,
              className := t.info.kind.toStyle
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
      linksToTalks(talks, conference)
    ).grayOutIf(talks.forall(_.cancelledReason.isDefined))

  def linksToTalks(talks: List[Session], conference: String): ReactiveHtmlElement[HTMLDivElement] =
    def linkButton(info: Session.BasicInfo) =
      val text = if info.kind == Session.Kind.Workshop then "Workshop" else "Talk"
      Buttons
        .classyNew(className := "link", text, Page.navigateTo(SessionPage(conference, info.slug)))
        .important

    div(
      display.flex,
      flexDirection.row,
      talks match
        case Nil         => emptyNode
        case head :: Nil => linkButton(head.info)
        case _           => talks.map(t => linkButton(t.info))
    )
}
