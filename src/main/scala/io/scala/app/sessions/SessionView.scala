package io.scala.app.sessions

import com.raquo.laminar.api.L.*

import io.scala.extensions.grayOutIf
import io.scala.SessionPage
import io.scala.models.Session
import io.scala.modules.elements.Containers
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles
import io.scala.svgs.Icons
import io.scala.views.ReactiveView

object SessionView extends ReactiveView[SessionPage]:

  def body(signal: Signal[SessionPage]): HtmlElement =
    val sessionSignal = signal.map: sessionPage =>
      sessionPage.conference.sessions
        .find(_.info.slug == sessionPage.slug)
        .getOrElse(notFoundTalk)
    sectionTag(
      className := "container talk",
      children <-- sessionSignal.map: session =>
        Seq(
          session.cancelledReason match
            case None => Titles(session.info.title)
            case Some(value) =>
              Titles.withSub(
                session.info.title,
                p(s"Cancelled: $value")
              ),
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
          Titles(
            if session.speakers.size > 1 then "Speakers" else "Speaker",
            idAttr("speaker")
          ),
          Containers.gridCards(session.speakers.map(SpeakerView(_).body))
        )
    ).grayOutIf(sessionSignal.map(_.cancelledReason.isDefined))

  private val notFoundTalk = Session(
    Session.BasicInfo(
      title = "404 talk not found",
      slug = "404-talk-not-found",
      Session.Kind.Talk,
      category = "non-existent",
      confirmed = false,
      `#` = null
    ),
    description = "This session or conference does not exist.",
    speakers = List.empty
  )

end SessionView
