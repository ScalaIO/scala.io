package io.scala.views

import io.scala.domaines.{Social, Speaker}
import io.scala.modules.elements.Title
import io.scala.profilePlaceholder
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase

import com.raquo.laminar.api.L.{*, given}

class SpeakerView(speaker: Speaker):
  def body: HtmlElement =
    div(
      className := "speaker-view",
      Title.small(speaker.name),
      div(
        className := "data",
        img(
          src       := speaker.photoPath,
          className := "photo",
          alt       := s"${speaker.name}'s profile picture"
        ),
        div(
          div(
            className := "svg-list",
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
