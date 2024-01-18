package io.scala.modules

import io.scala.{Page, PageArg}
import io.scala.data.TalksInfo.{allTalks, talksBySpeaker}
import io.scala.domaines.{Speaker, Talk}
import io.scala.domaines.Person
import io.scala.modules.elements.ClassyButton
import io.scala.svgs.GoTo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom
import org.scalajs.dom.console
import org.scalajs.dom.html.Anchor
import org.scalajs.dom.svg.{Path, SVG}

object PersonCard {
  def apply(person: Person) =
    div(
      className := "person-card",
      img(
        src       := person.photo.fold(io.scala.profilePlaceholder)(path => s"/images/profiles/$path"),
        className := "person-photo"
      ),
      div(
        div(
          person match {
            case speaker: Speaker =>
              talksBySpeaker(speaker).map: talk =>
                span(
                  talk.kind.toString,
                  className := talk.kind.toStyle
                )
            case _ => emptyNode
          },
          div(
            person.socialNetworks,
            className := "person-socials"
          ),
          className := "card-subtitle"
        ),
        h2(
          person.name,
          className := "card-title"
        ),
        p(person.job),
        p(person.company),
        className := "person-information"
      ),
      person match {
        case speaker: Speaker => linkToTalks(talksBySpeaker(speaker))
        case _                => emptyNode
      }
    )

  def linkToTalks(talks: Seq[Talk]) =
    talks.map { talk =>
      button(
        className := "card-link classy-button classy-button-highlight",
        "See talk ", // ! Problem if >= 2 talks
        GoTo(),
        Page.navigateTo(PageArg.Talk(talk.slug, false))
      )
    }
}
