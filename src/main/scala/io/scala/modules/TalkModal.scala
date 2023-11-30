package io.scala.modules

import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.svgs.Cross

import com.raquo.airstream.core.Signal
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
object TalkModal:
  def apply(talk: Var[Option[Speaker]]) =
    div(
      role := "dialog",
      className := "card-overlay",
      display <-- talk.signal.map(_.isDefined).map {
        case true  => "flex"
        case false => "none"
      },
      div(
        className := "talk-modal",
        div(
          className := "header",
          h2(
            className := "title-1",
            child.text <-- talk.signal.map(_.map(_.talk.title).getOrElse("ø"))
          ),
          button(
            className := "close",
            onClick --> { _ => talk.set(None) },
            Cross()
          )
        ),
        h3(
          className := "title-2",
          child.text <-- talk.signal.map {
            _.map { s =>
              s.name + " - " + s.talk.room.map(_.render).getOrElse("")
            }.getOrElse("ø")
          }
        ),
          p(
            child.text <-- talk.signal.map(_.map(_.talk.description).getOrElse("ø"))
          )
        )
    )
