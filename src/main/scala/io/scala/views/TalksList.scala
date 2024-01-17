package io.scala.views

import io.scala.Lexicon
import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.modules.TalkCard
import io.scala.modules.elements.*

import com.raquo.laminar.api.L.{*, given}

case object TalksList extends SimpleView {
  def bodyContent(talks: List[Talk]) = sectionTag(
    className := "container talks-list",
    Title("Talks"),
    Line(margin = 55),
    Talk.Category.values.map: category =>
      div(
        h2(className := "content-title", category.toName),
        div(
          talks.filter(_.category == category).map(TalkCard(_)),
          className := "card-container"
        )
      )
  )

  def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(TalksInfo.allTalks)
    else bodyContent(TalksInfo.allTalks.filter(_.speakers.forall(_.confirmed)))

  def title = "Talks"
}
