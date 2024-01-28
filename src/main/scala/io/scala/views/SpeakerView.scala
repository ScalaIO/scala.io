package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.elements.Title
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase
import io.scala.domaines.{Speaker, Social}
import io.scala.profilePlaceholder

class SpeakerView(speaker: Speaker):
  def body: HtmlElement =
    div(
      className := "person",
      Title.small(speaker.name),
      div(
        className := "speaker-data",
        img(
          src       := speaker.photoPath,
          className := "speaker-photo",
          alt       := s"${speaker.name}'s profile picture"
        ),
        div(
          className := "paragraph",
          div(
            div(
              Suitcase(),
              speaker.job
            ),
            div(
              AtSign(),
              speaker.company
            )
          ),
          h2("Who am I"),
          speaker.renderDescription,
          div(
            className := "socials",
            Social.renderWithAccount(speaker.socials)
          )
        )
      )
    )
