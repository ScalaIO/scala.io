package io.scala.views

import io.scala.domaines.Talk
import io.scala.modules.elements.Title
import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.TalkKindTag

class TalkView(talk: Talk) extends SimpleView:
  override def title: String = talk.title

  def kindInfo: String = (talk.kind match
    case Talk.Kind.Keynote => Some("Keynote")
    case _ => None).map(x => s"($x)").getOrElse("")

  def body(withDraft: Boolean): HtmlElement =
    sectionTag(
      className := "container talk",
      Title(talk.title),
      kindInfo,
      div(
        marginBottom := "2rem",
        className := "paragraph talk-description",
        talk.renderDescription
      ),
      Title(if (talk.speakers.size > 1) "Speakers" else "Speaker"),
      div(
        className := "card-container",
        talk.speakers.map(SpeakerView(_).body)
      )
    )
