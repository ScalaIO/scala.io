package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.elements.Title
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase
import io.scala.domaines.Person
import io.scala.profilePlaceholder

class PersonView(person: Person):
  def body: HtmlElement =
    div(
      className := "person",
      Title.small(person.name),
      div(
        className := "person-data",
        img(
          src       := person.photo.fold(profilePlaceholder)(path => s"/images/profiles/$path"),
          className := "person-photo"
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
            person.socialNetworksWithAccount
          )
        )
      )
    )
