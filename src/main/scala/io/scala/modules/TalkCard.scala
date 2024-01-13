package io.scala.modules

import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.svgs.GoTo
import io.scala.views.ScheduleState

import com.raquo.laminar.api.L.{*, given}
import elements.Line
import org.scalajs.dom

object TalkKindTag:
  def apply(kind: Talk.Kind) =
    span(kind.toString, className := kind.toStyle)
object TalkDescription:
  def apply(description: String) =
    val desc = if (description.length() <= 200) description else description.substring(0, 200) + "..."
    p(
      desc,
      className := "talk-description"
    )

object TalkCard:
  def apply(talk: Talk) =
    div(
      className := s"talk-card ${talk.kind.toStyle}",
      onClick --> { _ =>
        ScheduleState.selectedTalk.set(Some(talk))
      },
      div(
        h3(
          talk.title,
          className := "card-title"
        ),
        TalkKindTag(talk.kind)
      ),
      Line(margin = 10),
      div(
        className := "card-body",
        TalkDescription(talk.description)
      ),
      Line(margin = 10),
      div(
        className := "card-footer",
        div(
          className := "talk-speakers",
          talk.speakers.map { speaker =>
            div(
              className := "talk-speaker",
              img(
                src       := speaker.photo.fold(profilePlaceholder)(path => s"/images/profiles/$path"),
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
          }
        ),
        a(
          className := "card-link classy-button classy-button-highlight",
          s"Open talk's page ",
          GoTo(),
          href := s"/talks/${talk.slug}"
        ),
        p(
          className := "room",
          talk.room.map(_.render).getOrElse("")
        )
      )
    )
