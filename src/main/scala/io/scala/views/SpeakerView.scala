package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.elements.Title
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase
import io.scala.domaines.{Speaker, Social}
import io.scala.profilePlaceholder

class SpeakerView(person: Speaker):
  def body: HtmlElement =
    div(
      className := "person",
      Title.small(person.name),
      div(
        className := "speaker-data",
        img(
          src       := person.photoPath,
          className := "speaker-photo"
        ),
        div(
          className := "paragraph",
          div(
            div(
              Suitcase(),
              person.job
            ),
            div(
              AtSign(),
              person.company
            )
          ),
          h2("Who am I"),
          person.renderDescription,
          div(
            className := "socials",
            Social.renderWithAccount(person.socials)
          )
        )
      )
    )
