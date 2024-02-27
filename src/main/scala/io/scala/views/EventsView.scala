package io.scala.views

import io.scala.domaines.Social
import io.scala.data.OrgaInfo.allOrga
import io.scala.domaines.Organizer
import io.scala.domaines.Social
import io.scala.modules.elements.Title
import io.scala.svgs.Github

import com.raquo.laminar.api.L.*

object EventsView extends SimpleView {
  override def body(): HtmlElement = {
    sectionTag(
      className := "container faq",
      Title("Other events"),
      p(
        marginTop := "32px",
        "WIP :)"
      )
    )
  }
}
