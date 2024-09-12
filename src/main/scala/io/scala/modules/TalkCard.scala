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
import io.scala.svgs.Icons
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Buttons.important

object TalkKindTag:
  def apply(kind: Session.Kind): ReactiveHtmlElement[HTMLSpanElement] =
    span(kind.toString, className := kind.toStyle, marginLeft := "4px")

object TalkCard:
  def shortName(name: String) =
    name.split(" ", 2) match
      case Array(first, last) => s"${first.charAt(0)}. $last"
      case _                  => name

  def apply(talk: Session, conference: String): ReactiveHtmlElement[HTMLDivElement] =
    div(
      className := s"talk-card ${talk.info.kind.toStyle}",
      div(
        h3(
          className := "title",
          talk.info.title,
          TalkKindTag(talk.info.kind)
        ),
        div(
          className := "subtitle",
          talk.info.room.nullFold(emptyNode)(room => span(className := "room", room.show))
        )
      ),
      Line(margin = 4),
      div(
        className := "body paragraph",
        talk.renderDescription
      ),
      Line(margin = 4),
      div(
        div(
          className := "speakers",
          talk.speakers.map { speaker =>
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
                  speaker.job,
                  className := "company"
                )
              )
            )
          }
        ),
        Buttons.classyNew(
          Icons.goTo,
          className  := "link",
          aria.label := s"Go to ${talk.info.title}",
          Page.navigateTo(TalkPage(conference, talk.info.slug))
        ).important
      )
    )
