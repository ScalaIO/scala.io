package io.scala.modules

import io.scala.domaines.Speaker
import io.scala.data.TalksInfo.talksTag

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.console
import io.scala.data.TalksInfo.allTalks

object SpeakerCard {
  def apply(speaker: Speaker, variable: Var[Option[Speaker]]) =
    

    div(
      img(
        src       := speaker.photo.fold("/images/profile.jpg")(path => s"/images/profiles/$path"),
        className := "speaker-photo"
      ),
      div(
        div(
          talksTag(speaker).toList.map: tag =>
            div( 
              tag.toString,
              className := s"${tag.toStyle}"
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
      onClick.mapTo(Some(speaker)) --> variable,
      className := "speaker-card"
    )
}
