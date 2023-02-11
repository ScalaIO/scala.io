package io.scala.modules

import io.scala.domaines.Speaker

import com.raquo.laminar.api.L.{*, given}

object SpeakerCard {
  def apply(speaker: Speaker, variable: Var[Option[Speaker]]) =
    div(
      img(
        src       := speaker.photo.fold("/images/profile.jpg")(path => s"/images/profiles/$path"),
        className := "speaker-card__photo"
      ),
      div(
        div(
          div(
            speaker.presentation.toString,
            className := s"speaker-card__presentation ${speaker.presentation.toStyle}"
          ),
          div(
            className := "speaker-card__socials"
          ),
          className := "speaker-card__headband"
        ),
        h2(
          speaker.name,
          className := "speaker-card__name"
        ),
        span(
          speaker.job,
          className := "speaker-card__job"
        ),
        span(
          speaker.company,
          className := "speaker-card__company"
        ),
        className := "speaker-card__information"
      ),
      onClick.mapTo(Some(speaker)) --> variable,
      className := "speaker-card"
    )
}
