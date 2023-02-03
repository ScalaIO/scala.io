package io.scala.modules

import io.scala.domaines.Speaker

import com.raquo.laminar.api.L.{*, given}

object SpeakerCard {
  def inner(speaker: Speaker) =
    div(
      h2(
        speaker.talk.name,
        className := "speaker-card__talk__name"
      ),
      p(
        speaker.talk.description,
        className := "speaker-card__talk__description"
      ),
      className := "speaker-card__back"
    )

  def outer(speaker: Speaker) =
    div(
      div(
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
      className := "speaker-card__front"
    )

  def apply(speaker: Speaker) =
    div(
      div(
        outer(speaker),
        inner(speaker),
        className := "speaker-card__inner"
      ),
      className := "speaker-card"
    )
}
