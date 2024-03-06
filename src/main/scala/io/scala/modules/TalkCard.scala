package io.scala
package modules

import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.svgs.GoTo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import elements.Line
import org.scalajs.dom
import org.scalajs.dom.{HTMLDivElement, HTMLSpanElement}

object TalkKindTag:
  def apply(kind: Talk.Kind): ReactiveHtmlElement[HTMLSpanElement] =
    span(kind.toString, className := kind.toStyle)

object TalkCard:
  def shortName(name: String) =
    name.split(" ", 2) match
      case Array(first, last) => s"${first.charAt(0)}. $last"
      case _                  => name

  def apply(talk: Talk): ReactiveHtmlElement[HTMLDivElement] =
    div(
      className := s"talk-card ${talk.kind.toStyle}",
      div(
        h3(
          className := "title",
          talk.title
        ),
        div(
          className := "subtitle",
          TalkKindTag(talk.kind),
          if talk.room != null then
            span(
              className := "room",
              talk.room.render
            )
          else emptyNode
        )
      ),
      Line(margin = 16),
      div(
        className := "body paragraph",
        talk.renderDescription
      ),
      Line(margin = 16),
      div(
        className := "footer",
        talk.speakers.map { speaker =>
          div(
            className := "speaker",
            img(
              src       := speaker.photoPath,
              className := "photo",
              alt       := s"${speaker.name}'s profile picture"
            ),
            div(
              p(
                shortName(speaker.name),
                className := "name"
              ),
              p(
                speaker.company,
                className := "company"
              )
            )
          )
        },
        button(
          className  := "link classy-button classy-button-highlight",
          aria.label := s"Go to ${talk.title}",
          GoTo(),
          Page.navigateTo(TalkPage(talk.slug))
        )
      )
    )
