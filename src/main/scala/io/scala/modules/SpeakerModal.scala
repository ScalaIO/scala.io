package io.scala.modules

import io.scala.domaines.Speaker
import com.raquo.laminar.api.L.{*, given}
import io.scala.views.Speakers.{selectedSpeaker, speaker}

object SpeakerModal {
  def apply(speaker: Speaker, variable: Var[Option[Speaker]]) =
    div(
      div(
        "×",
        onClick.mapTo(None) --> variable,
        className := "speaker-modal__close"
      ),
      div(
        div(
          img(
            src       := speaker.photo.fold("/images/profile.jpg")(path => s"/images/profiles/$path"),
            width     := "250px",
            height    := "250px",
            className := "speaker-modal__photo"
          ),
          div(
            h1(
              speaker.name,
              className := "speaker-modal__speaker__name"
            ),
            span(
              speaker.job,
              className := "speaker-modal__speaker__job"
            ),
            span(
              speaker.company,
              className := "speaker-modal__speaker__company"
            ),
            className := "speaker-modal__title"
          ),
          className := "speaker-modal__header"
        ),
        div(
          h2(
            speaker.talk.name,
            className := "speaker-modal__section__title"
          ),
          p(
            speaker.talk.description,
            className := "speaker-modal__section__content"
          ),
          className := "speaker-modal__section"
        ),
        className := "speaker-modal__container"
      ),
      className := "speaker-modal"
    )
}
