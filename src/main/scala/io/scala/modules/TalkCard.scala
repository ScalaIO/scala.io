package io.scala
package modules

import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.svgs.GoTo

import com.raquo.laminar.api.L.{*, given}
import elements.Line
import org.scalajs.dom

object TalkKindTag:
  def apply(kind: Talk.Kind) =
    span(kind.toString, className := kind.toStyle)
object TalkDescription:
  def apply(description: String) =
    val desc = if (description.length() <= 150) description else description.substring(0, 150) + "..."
    p(desc)

object TalkCard:
  def apply(talk: Talk) =
    div(
      className := s"talk-card ${talk.kind.toStyle}",
      div(
        h3(
          talk.title,
          className := "card-title"
        ),
        div(
          className := "card-subtitle",
          TalkKindTag(talk.kind),
          if talk.room != null then
            span(
              className := "room",
              talk.room.render
            )
          else emptyNode
        )
      ),
      Line(margin = 10),
      div(
        className := "card-body",
        TalkDescription(talk.description)
      ),
      Line(margin = 10),
      div(
        className := "card-footer",
        talk.speakers.map { speaker =>
          div(
            className := "talk-speaker",
            img(
              src       := speaker.photoPath,
              className := "speaker-photo"
            ),
            div(
              p(
                speaker.name,
                className := "speaker-name"
              ),
              p(
                speaker.company,
                className := "speaker-company"
              )
            )
          )
        },
        button(
          className := "card-link classy-button classy-button-highlight",
          s"More info ",
          GoTo(),
          Page.navigateTo(PageArg.Talk(talk.slug, false))
        )
      )
    )
