package io.scala
package modules

import com.raquo.laminar.api.L.*
import elements.Line

import io.scala.extensions.*
import io.scala.models.Session
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Buttons.important
import io.scala.modules.elements.Image
import io.scala.svgs.Icons
import io.scala.data.Event

object SessionKindTag:
  def apply(kind: Session.Kind): Span =
    span(kind.toString, className := kind.toStyle, marginLeft := "4px")

object SessionCard:
  private def shortName(name: String) =
    name.split(" ", 2) match
      case Array(first, last) => s"${first.charAt(0)}. $last"
      case _                  => name

  // Remove the dependency on `conference` by having it injected in Session.Basic info for better reusability
  def apply(session: Session, conference: Event, room: Session.Room | Null = null): Div =
    def realDuration = session.info.title match
      case s if session.duration == 150 && (s.startsWith("Building") || s.startsWith("Quick")) => 90
      case _ => session.info.kind.duration

    div(
      className := s"talk-card ${session.info.kind.toStyle}", // TODO: rename CSS also
      div(
        h3(
          className := "card-title",
          if (session.info.`#` != null) then s"${session.info.title} #${session.info.`#`}"
          else session.info.title,
          SessionKindTag(session.info.kind)
        ),
        div(
          className := "subtitle",
          room.nullFold(span())(room => span(className := "room", room.show)),
          // hide the room for now, can be see in the draft schedule
          span(),
          span(s"${realDuration}min")
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
              Image.photo(
                src       := speaker.photoRelPath,
                className := "photo",
                alt       := s"${speaker.name}'s profile"
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
    ).grayOutIf(session.cancelledReason.isDefined)

end SessionCard
