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
    div(
      className := "with-toc",
      div(
        className := "content",
        Talk.Category.values.map: category =>
          div(
            h2(idAttr := category.slug, className := "content-title", category.name),
            div(
              talks.filter(_.category == category).map(TalkCard(_)),
              className := "card-container"
            )
          ),
      ),
      stickScroll
    )
  )

  def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(TalksInfo.allTalks)
    else bodyContent(TalksInfo.allTalks.filter(_.speakers.forall(_.confirmed)))

  def title = "Talks"

  def stickScroll =
    div(
      className := "table-of-contents",
      idAttr    := "talks-cat-toc",
      div(
        className := "toc-content",
        h3("Navigation"),
        Talk.Category.values.map: cat =>
          a(
            href := s"#${cat.slug}",
            span(s"- ${cat.name}")
          )
      )
    )
}
