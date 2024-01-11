package io.scala.modules

import io.scala.domaines.Speaker
import io.scala.svgs.Cross
import io.scala.views.Speakers.selectedSpeaker

import com.raquo.laminar.api.L.{*, given}

object SpeakerModal {
  def apply(speaker: Var[Option[Speaker]]) =
    div(
      role      := "dialog",
      className := "card-overlay",
      display <-- speaker.signal.map(_.isDefined).map {
        case true  => "flex"
        case false => "none"
      },
      button(
        Cross(),
        onClick.mapTo(None) --> speaker,
        className := "speaker-modal__close"
      ),
      div(
        className := "speaker-modal",
        div(
          className := "header",
          img(
            src <-- speaker.signal.map {
              case Some(s) => s.photo.fold(profilePlaceholder)(path => s"/images/profiles/$path")
              case None    => profilePlaceholder
            },
            className := "speaker-photo"
          ),
          div(
            className := "speaker-title",
            h1(
              className := "speaker-name",
              child.text <-- speaker.signal.map(_.map(_.name).getOrElse("ø"))
            ),
            span(
              className := "title-2",
              child.text <-- speaker.signal.map(_.map(_.job).getOrElse("ø"))
            ),
            span(
              className := "title-2",
              child.text <-- speaker.signal.map(_.map(_.company).getOrElse("ø"))
            )
          ),
          button(
            className := "close",
            onClick --> { _ => speaker.set(None) },
            Cross()
          )
        ),
        div(
          p(
            child.text <-- speaker.signal.map(_.map(_.description).getOrElse("ø"))
          )
        )
      )
    )
}
