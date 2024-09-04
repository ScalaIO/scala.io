package io.scala.app.talks

import com.raquo.laminar.api.L.*

import io.scala.TalkPage
import io.scala.data.TalksHistory
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles
import io.scala.svgs.Icons
import io.scala.views.ReactiveView

object TalkView extends ReactiveView[TalkPage]:
  def body(signal: Signal[TalkPage]): HtmlElement =
    val talkSignal = signal.map: args =>
      TalksHistory.talksForConf(Some(args.conference)).find(_.info.slug == args.slug).get
    sectionTag(
      className := "container talk",
      children <-- talkSignal.map: talk =>
        Seq(
          Titles(talk.info.title),
          div(
            className := "talk-description",
            Paragraphs.description(talk.renderDescription),
            div(
              className := "links",
              talk.info.slides.fold(emptyNode): slides =>
                a(Icons.pdf, href := slides, "Slides", target := "_blank"),
              talk.info.replay.fold(emptyNode): replayUrl =>
                a(Icons.youtube, href := replayUrl, "Replay", target := "_blank")
            )
          ),
          Titles(if talk.speakers.size > 1 then "Speakers" else "Speaker"),
          div(
            className := "card-container",
            talk.speakers.map(SpeakerView(_).body)
          )
        )
    )
