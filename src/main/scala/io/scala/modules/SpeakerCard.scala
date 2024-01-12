package io.scala.modules

import io.scala.domaines.Speaker
import io.scala.data.TalksInfo.talksTag

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import org.scalajs.dom.console
import io.scala.data.TalksInfo.allTalks
import io.scala.svgs.GoTo
import io.scala.modules.elements.ClassyButton
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom.svg.{Path, SVG}
import org.scalajs.dom.html.Anchor

object SpeakerCard {
  def apply(speaker: Speaker, variable: Var[Option[Speaker]]) =
    div(
      className := "speaker-card",
      onClick.filter(clickFilter).mapTo(Some(speaker)) --> variable,
      img(
        src       := speaker.photo.fold(profilePlaceholder)(path => s"/images/profiles/$path"),
        className := "speaker-photo"
      ),
      div(
        div(
          talksTag(speaker).toList.map: kind =>
            span( 
              kind.toString,
              className := kind.toStyle
            ),
          div(
            speaker.socialNetworks,
            className := "speaker-socials"
          ),
          className := "speaker-headband"
        ),
        h2(
          speaker.name,
          className := "card-title"
        ),
        p(speaker.job),
        p(speaker.company),
        className := "speaker-information"
      ),
      a(
        className := "card-link classy-button classy-button-highlight",
        s"${speaker.name.split(" ")(0)}'s page ",
        GoTo(),
        target := "_blank",
        href := s"/speakers/${speaker.slug}"
      ),
    )
}
