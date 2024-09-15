package io.scala
package modules

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import elements.Line
import org.scalajs.dom
import org.scalajs.dom.HTMLDivElement
import org.scalajs.dom.HTMLSpanElement

import io.scala.extensions.*
import io.scala.models.Session
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Buttons.important
import io.scala.svgs.Icons

object SessionKindTag:
  def apply(kind: Session.Kind): ReactiveHtmlElement[HTMLSpanElement] =
    span(kind.toString, className := kind.toStyle, marginLeft := "4px")

object SessionCard:

  private def shortName(name: String) =
    name.split(" ", 2) match
      case Array(first, last) => s"${first.charAt(0)}. $last"
      case _                  => name

  // Remove the dependency on `conference` by having it injected in Session.Basic info for better reusability
  def apply(session: Session, conference: String): ReactiveHtmlElement[HTMLDivElement] =
    div(
      className := s"talk-card ${session.info.kind.toStyle}", // TODO: rename CSS also
      div(
        h3(
          className := "title",
          session.info.title,
          SessionKindTag(session.info.kind)
        ),
        div(
          className := "subtitle",
          session.info.room.nullFold(emptyNode)(room => span(className := "room", room.show))
        )
      ),
      Line(margin = 4),
      div(
        className := "body paragraph",
        session.renderDescription
      ),
      Line(margin = 4),
      div(
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
      )
    )

end SessionCard
