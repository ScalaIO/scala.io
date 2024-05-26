package io.scala.views

import com.raquo.laminar.api.L._
import io.scala.data.TalksInfo
import io.scala.domaines.{Talk => Talk, _}
import io.scala.modules.TalkCard
import io.scala.modules.elements._

import org.scalajs.dom.console

case object TalkList extends SimpleViewWithDraft {

  private def sortedCategories(talks: List[Talk]): List[(String, List[Talk])] =
    talks
      .groupBy(_.info.category)
      .toList
      .sortWith:
        case ((cat1, talks1), (cat2, talks2)) =>
          val cat1HasKeynote = talks1.exists(_.isKeynote)
          val cat2HasKeynote = talks2.exists(_.isKeynote)
          (cat1HasKeynote, cat2HasKeynote, talks1.size == talks2.size) match
            // 1st criterion: categories with keynotes
            case (false, true, _) => false
            case (true, false, _) => true
            // 2nd criterion: categories with more talks
            case (_, _, false) => talks1.size > talks2.size
            // 3rd criterion: lexicographic order
            case (_, _, true) => cat1.compareTo(cat2) >= 0

  private def bodyContent(allTalks: List[Talk]) =
    console.log("allTalks", allTalks)
    val categories = sortedCategories(allTalks)
    sectionTag(
      className := "container talks-list",
      Titles("Talks"),
      Line(margin = 55),
      div(
        className := "with-toc r-toc",
        stickScroll(categories.map(_._1)),
        div(
          className := "content",
          categories.flatMap: (category, talks) =>
            List(
              h2(idAttr     := category, className := "content-title", category),
              div(className := "card-container", talks.sorted.map(TalkCard(_)))
            )
        )
      )
    )

  override def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(TalksInfo.allTalks)
    else bodyContent(TalksInfo.allTalks.filter(_.speakers.forall(_.confirmed)))

  private def stickScroll(sortedCategories: List[String]) =
    div(
      className := "table-of-contents",
      idAttr    := "talks-cat-toc",
      div(
        className := "toc-content",
        h3("Navigation"),
        sortedCategories.map: cat =>
          a(
            href := s"#${cat}",
            span(cat)
          )
      )
    )

}
