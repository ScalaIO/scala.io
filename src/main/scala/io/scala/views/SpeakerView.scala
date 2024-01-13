package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.data.TalksInfo
import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Speaker
import io.scala.modules.elements.Title
import io.scala.modules.profilePlaceholder
import io.scala.svgs.AtSign
import io.scala.svgs.Suitcase

class SpeakerView(speaker: Speaker) extends SimpleView:
  override def title: String = "Speaker " + speaker.name

  def body(withDraft:Boolean): HtmlElement =
    val talk = allTalks.find(_.speakers.contains(speaker)).get
    sectionTag(
      className := "container speaker",
      Title.withSub(
        speaker.name,
        div(
          className := "speaker-job",
          div(
            Suitcase(),
            speaker.job
          ),
          div(
            AtSign(),
            speaker.company
          )
        )
      ),
      div(
        className := "speaker-data",
        img(
          src       := speaker.photo.fold(profilePlaceholder)(path => s"/images/profiles/$path"),
          className := "speaker-photo"
        ),
        div(
          className := "speaker-info",
          h2("Who am I"),
          p(speaker.description),
          h2(talk.title),
          p(talk.description)
        )
      )
    )
