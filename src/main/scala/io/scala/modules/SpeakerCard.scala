package io.scala.modules

import io.scala.domaines.Speaker

import com.raquo.laminar.api.L.{*, given}

object SpeakerCard {
  def apply(speaker: Speaker, variable: Var[Option[Speaker]]) =
    div(
      img(
        src       := speaker.photo.fold("/images/profile.jpg")(path => s"/images/profiles/$path"),
        className := "speaker-photo"
      ),
      div(
        div(
          div(
            speaker.talk.kind.toString,
            className := s"${speaker.talk.kind.toStyle}"
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
