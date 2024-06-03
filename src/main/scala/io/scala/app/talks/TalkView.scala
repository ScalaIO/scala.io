package io.scala.app.talks

import io.scala.TalkPage
import io.scala.svgs.Icons

import com.raquo.laminar.api.L._
import io.scala.data.TalksHistory
import io.scala.views.ReactiveView

object TalkView extends ReactiveView[TalkPage]:
  def body(signal: Signal[TalkPage]): HtmlElement =
    val talk = signal.map: args =>
      TalksHistory.talksForConf(Some(args.conference)).find(_.info.slug == args.slug).get
    sectionTag(
      className := "container talk",
      h1( // TODO: reuse Title(name: String)
        child.text <-- talk.map(_.info.title),
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
          child <-- talk.map:
            _.info.slides
              .map: slides =>
                a(Icons.pdf, href := slides, slides, target := "_blank")
              .getOrElse(emptyNode)
          ,
          child <-- talk.map:
            _.info.replay
              .map: replayUrl =>
                a(Icons.youtube, href := replayUrl, replayUrl, target := "_blank")
              .getOrElse(emptyNode)
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
