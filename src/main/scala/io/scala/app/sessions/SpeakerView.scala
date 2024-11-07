package io.scala.app.sessions

import com.raquo.laminar.api.L.*

import io.scala.models.Social
import io.scala.models.Session.Speaker
import io.scala.modules.elements.Titles
import io.scala.svgs.Icons
import io.scala.modules.elements.Image

class SpeakerView(speaker: Speaker):
  def body: HtmlElement =
    div(
      className := "speaker-view",
      Titles.medium(speaker.name),
      div(
        className := "data",
        Image.photo(
          src       := speaker.photoRelPath,
          className := "photo",
          alt       := s"${speaker.name}'s profile"
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
