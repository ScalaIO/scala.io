package io.scala.app.talks

import com.raquo.laminar.api.L.*

import io.scala.domaines.Social
import io.scala.domaines.Talk.Speaker
import io.scala.modules.elements.Titles
import io.scala.svgs.Icons

class SpeakerView(speaker: Speaker):
  def body: HtmlElement =
    div(
      className := "speaker-view",
      Titles.medium(speaker.name),
      div(
        className := "data",
        img(
          src       := speaker.photoRelPath,
          className := "photo",
          alt       := s"${speaker.name}'s profile picture"
        ),
        div(
          div(
            className := "svg-list",
            div(
              Icons.suitecase,
              speaker.jobTitle
            ),
            div(
              Icons.atSign,
              speaker.company
            )
          ),
          Titles.small("Who am I"),
          speaker.renderBio,
          div(
            className := "socials",
            Social.renderWithAccount(speaker.socials)
          )
        )
      )
    )
