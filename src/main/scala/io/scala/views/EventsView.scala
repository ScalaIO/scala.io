package io.scala.views

import io.scala.domaines.Social
import io.scala.data.OrgaInfo.allOrga
import io.scala.domaines.Organizer
import io.scala.domaines.Social
import io.scala.modules.elements.Title

import com.raquo.laminar.api.L.*
import io.scala.domaines.Event
import io.scala.domaines.TimeRange
import java.time.LocalDateTime
import java.time.LocalDate
import io.scala.data.EventsList

object EventsView extends SimpleView {
  override def body(): HtmlElement = {
    sectionTag(
      className := "container events",
      Title("Other events"),
      div(
        className := "list",
        EventsList.eventsList.zipWithIndex.map: (event, index) =>
          if index % 2 == 0 then
            event.render().amend(className := "normal")
          else
            event.render().amend(className := "reverse")
      )
    )
  }
}
