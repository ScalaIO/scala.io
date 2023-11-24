package io.scala.modules

import io.scala.domaines.Kind
import io.scala.domaines.Speaker
import io.scala.domaines.Talk

import com.raquo.laminar.api.L.{*, given}

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
      className := "talk-card__description"
    )

object TalkCard:
  def apply(speaker: Speaker) =
    div(
      className := "talk-card",
      div(
        h3(
          speaker.talk.title,
          className := "talk-card__title"
        ),
        className := "talk-card__headband"
      ),
      Line(padding = 10),
      div(
        className := "talk-card__body",
        TalkDescription(speaker.talk.description)
      ),
      Line(padding = 10),
      div(
        className := "talk-card__footer",
        div(
          className := "talk-card__speaker",
          img(
            src       := speaker.photo.fold("/images/profile.jpg")(path => s"/images/profiles/$path"),
            className := "talk-card__speaker-photo"
          ),
          div(
            className := "talk-card__speaker-info",
            p(
              speaker.name,
              className := "talk-card__speaker-name"
            ),
            p(
              speaker.company,
              className := "talk-card__speaker-company"
            )
          )
        ),
        p(
          className := "talk-card__room",
          speaker.talk.room.map(_.render).getOrElse("")
        )
      )
    )
