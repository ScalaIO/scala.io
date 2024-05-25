package io.scala.views

import io.scala.data.EventsList
import io.scala.modules.elements.Titles

import com.raquo.laminar.api.L._

object EventsView extends SimpleView {
  override def body(): HtmlElement = {
    sectionTag(
      className := "container events",
      Titles("Other events"),
      div(
        className := "list",
        EventsList.eventsList.zipWithIndex.map: (event, index) =>
          if index % 2 == 0 then event.render.amend(className := "normal")
          else event.render.amend(className := "reverse")
      )
    )
  }
}
