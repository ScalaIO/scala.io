package io.scala
package modules

import com.raquo.laminar.api.L.*
import elements.Line

import io.scala.extensions.*
import io.scala.models.Session
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Buttons.important
import io.scala.svgs.Icons

object SessionKindTag:
  def apply(kind: Session.Kind): Span =
    span(kind.toString, className := kind.toStyle, marginLeft := "4px")

object SessionCard:
  private def shortName(name: String) =
    name.split(" ", 2) match
      case Array(first, last) => s"${first.charAt(0)}. $last"
      case _                  => name

  // Remove the dependency on `conference` by having it injected in Session.Basic info for better reusability
  def apply(session: Session, conference: String): Div =
    div(
      className := s"talk-card ${session.info.kind.toStyle}", // TODO: rename CSS also
      div(
        h3(
          className := "card-title",
          session.info.title,
          SessionKindTag(session.info.kind)
        ),
        div(
          className := "subtitle",
          // session.info.room.nullFold(span())(room => span(className := "room", room.show)),
          // hide the room for now, can be see in the draft schedule
          span(),
          span(s"${session.info.kind.duration}min")
        )
      ),
      Line(margin = 0.25),
      div(
        className := "body paragraph",
        session.renderDescription
      ),
      Line(margin = 0.25),
      div(
        className := "card-footer",
        div(
          className := "speakers",
          session.speakers.map { speaker =>
            div(
              className := "speaker",
              img(
                src       := speaker.photoRelPath,
                className := "photo",
                alt       := s"${speaker.name}'s profile picture"
              ),
              div(
                p(
                  shortName(speaker.name),
                  className := "name"
                ),
                p(
                  speaker.jobTitle,
                  if speaker.company.nonEmpty then s" @ ${speaker.company}" else "",
                  className := "job"
                )
              )
            )
          }
        ),
        Buttons
          .classyNew(
            Icons.goTo,
            className  := "link",
            aria.label := s"Go to ${session.info.title}",
            Page.navigateTo(SessionPage(conference, session.info.slug))
          )
          .important
          .amend(display.block, width.percent := 100)
      )
    )

end SessionCard
