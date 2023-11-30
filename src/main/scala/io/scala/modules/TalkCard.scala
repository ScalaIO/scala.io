package io.scala.modules

import io.scala.domaines.Kind
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.views.ScheduleState

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
object TalkKindTag:
  def apply(kind: Kind) =
    div(
      kind.toString,
      className := s"speaker-card__presentation ${kind.toStyle}"
    )
object TalkDescription:
  def apply(description: String) =
    val desc = if (description.length() <= 150) description else description.substring(0, 150) + "..."
    p(
      desc,
      className := "talk-description"
    )

object TalkCard:
  def apply(speaker: Speaker) =
    div(
      className := "talk-card",
      onClick --> { _ =>
        ScheduleState.selectedTalk.set(Some(speaker))
      },
      h3(
        speaker.talk.title,
        className := "card-title"
      ),
      Line(margin = 10),
      div(
        className := "card-body",
        TalkDescription(speaker.talk.description)
      ),
      Line(margin = 10),
      div(
        className := "card-footer",
        div(
          className := "talk-speaker",
          img(
            src       := speaker.photo.fold("/images/profile.jpg")(path => s"/images/profiles/$path"),
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
        ),
        p(
          className := "room",
          speaker.talk.room.map(_.render).getOrElse("")
        )
      )
    )
