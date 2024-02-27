package io.scala.views

import io.scala.TalkPage
import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Talk
import io.scala.modules.TalkKindTag
import io.scala.modules.elements.Title

import com.raquo.laminar.api.L.{*, given}

object TalkView extends ReactiveView[TalkPage]:
  def body(signal: Signal[TalkPage]): HtmlElement =
    val talk = signal.map: args =>
      allTalks.find(_.slug == args.slug).get
    sectionTag(
      className := "container talk",
      h1( // TODO: reuse Title(name: String)
        child.text <-- talk.map(_.title),
        className := "page-title"
      ),
      div(
        className := "talk-description",
        div(
          className := "paragraph",
          children <-- talk.map(_.renderDescription)
        ),
        div(
          className := "links",
          div(
            span(
              className := "slides-link",
              "Slides"
            ),
            "👉",
            a(
              href <-- talk.map(_.slides.getOrElse("")),
              child.text <-- talk.map(_.slides.getOrElse(""))
            )
          ),
          div(
            span(
              className := "replay-link",
              "Replay"
            ),
            "👉",
            a(
              href <-- talk.map(_.replay.getOrElse("")),
              child.text <-- talk.map(_.replay.getOrElse(""))
            )
          )
        )
      ),
      h1(
        child.text <-- talk.map(t => if t.speakers.size > 1 then "Speakers" else "Speaker"),
        className := "page-title"
      ),
      div(
        className := "card-container",
        children <-- talk.map(_.speakers.map(SpeakerView(_).body))
      )
    )
