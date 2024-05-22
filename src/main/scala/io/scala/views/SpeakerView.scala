package io.scala.views

import com.raquo.laminar.api.L._
import io.scala.domaines.Social
import io.scala.domaines.Speaker
import io.scala.modules.elements.Title
import io.scala.svgs.Icons

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
              Icons.suitecase,
              speaker.job
            ),
            div(
              Icons.atSign,
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
