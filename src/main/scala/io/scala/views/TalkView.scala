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
        child.text <-- signal.map(_.title),
        className := "page-title"
      ),
      div(
        marginBottom := "2rem",
        className := "paragraph talk-description",
        children <-- talk.map(_.renderDescription)
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
