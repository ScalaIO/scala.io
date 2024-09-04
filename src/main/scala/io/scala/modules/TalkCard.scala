package io.scala
package modules

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import elements.Line
import org.scalajs.dom
import org.scalajs.dom.HTMLDivElement
import org.scalajs.dom.HTMLSpanElement

import io.scala.models.Talk
import io.scala.svgs.Icons
import io.scala.extensions.*

object TalkKindTag:
  def apply(kind: Talk.Kind): ReactiveHtmlElement[HTMLSpanElement] =
    span(kind.toString, className := kind.toStyle, marginLeft := "4px")

object TalkCard:
  def shortName(name: String) =
    name.split(" ", 2) match
      case Array(first, last) => s"${first.charAt(0)}. $last"
      case _                  => name

  def apply(talk: Talk, conference: String): ReactiveHtmlElement[HTMLDivElement] =
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
        },
        button(
          className  := "link classy-button highlight",
          aria.label := s"Go to ${talk.info.title}",
          Icons.goTo,
          Page.navigateTo(TalkPage(conference, talk.info.slug))
        )
      )
    )
