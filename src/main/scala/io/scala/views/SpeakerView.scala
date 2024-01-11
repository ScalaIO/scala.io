package io.scala.views

import io.scala.domaines.Speaker
import com.raquo.laminar.api.L.{*, given}
import io.scala.modules.elements.Title

final case class SpeakerView(speaker: Speaker) extends View:
  def body: HtmlElement = sectionTag(
    className := "container",
    Title(speaker.name)
  )
