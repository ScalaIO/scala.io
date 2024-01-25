package io.scala.views

import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.modules.TalkCard
import io.scala.modules.elements.*

import com.raquo.laminar.api.L.{*, given}

case object TalkList extends SimpleView {
  private def bodyContent(talks: List[Talk]) = sectionTag(
    className := "container talks-list",
    Title("Talks"),
    Line(margin = 55),
    div(
      className := "with-toc r-toc",
      stickScroll,
      div(
        className := "content",
        Talk.Category.values
          .flatMap: category =>
            Array(
              h2(idAttr := category.slug, className := "content-title", category.name),
              div(
                talks.filter(_.category == category).sortBy(_.title).map(TalkCard(_)),
                className := "card-container"
              )
            )
      )
    )
  )

  override def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(TalksInfo.allTalks)
    else bodyContent(TalksInfo.allTalks.filter(_.speakers.forall(_.confirmed)))

  override def title: String = "Talks"

  private def stickScroll =
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
