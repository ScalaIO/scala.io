package io.scala.app.sessions

import com.raquo.laminar.api.L.*

import io.scala.SessionPage
import io.scala.data.SessionsHistory
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles
import io.scala.svgs.Icons
import io.scala.views.ReactiveView

object SessionView extends ReactiveView[SessionPage]:
  def body(signal: Signal[SessionPage]): HtmlElement =
    val sessionSignal = signal.map: args =>
      SessionsHistory
        .sessionsForConf(Some(args.conference))
        .find(_.info.slug == args.slug)
        .get // FIXME: urgh... should display an error instead
    sectionTag(
      className := "container talk",
      children <-- sessionSignal.map: session =>
        Seq(
          Titles(session.info.title),
          div(
            className := "talk-description",
            Paragraphs.description(session.renderDescription),
            div(
              className := "links",
              session.info.slides.fold(emptyNode): slides =>
                a(Icons.pdf, href := slides, "Slides", target := "_blank"),
              session.info.replay.fold(emptyNode): replayUrl =>
                a(Icons.youtube, href := replayUrl, "Replay", target := "_blank")
            )
          ),
          Titles(if session.speakers.size > 1 then "Speakers" else "Speaker"),
          div(
            className := "card-container",
            session.speakers.map(SpeakerView(_).body)
          )
        )
    )
