package io.scala.views

import com.raquo.laminar.api.L.*

import io.scala.data.EventFilesName
import io.scala.domaines.Meetup
import io.scala.modules.elements.Containers
import io.scala.modules.elements.Titles

object EventsView extends SimpleView {

  lazy val events =
    EventFilesName.events
      .map: event =>
        Meetup(event)
      .sorted

  override def body(): HtmlElement = {
    sectionTag(
      className := "container events",
      Titles("Other events"),
      Containers.flexCards(events.zipWithIndex.map { (ev, idx) =>
        if idx % 2 == 0 then ev.render.amend(className := "normal")
        else ev.render.amend(className := "reverse")
      })
    )
  }
}
