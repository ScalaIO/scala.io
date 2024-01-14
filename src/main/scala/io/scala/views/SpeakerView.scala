package io.scala
package views

import io.scala.data.TalksInfo
import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Speaker
import io.scala.modules.elements.Title
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase

import com.raquo.laminar.api.L.{*, given}

class SpeakerView(speaker: Speaker):
  def body: HtmlElement =
    val talk = allTalks.find(_.speakers.contains(speaker)).get
    div(
      className := "speaker",
      Title.small(speaker.name),
      div(
        className := "speaker-data",
        img(
          src       := speaker.photo.fold(profilePlaceholder)(path => s"/images/profiles/$path"),
          className := "speaker-photo"
        ),
        div(
          className := "paragraph",
          div(
            className := "speaker-job",
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
            speaker.socialNetworksWithAccount
          )
        )
      )
    )
