package io.scala.app.sessions

import com.raquo.laminar.api.L.*
import scala.collection.immutable.Queue

import io.scala.SessionsPage
import io.scala.data.SessionsHistory
import io.scala.data.SessionsHistory.getConfName
import io.scala.models.Session
import io.scala.modules.SessionCard
import io.scala.modules.elements.*
import io.scala.views.ReactiveView

case object SessionList extends ReactiveView[SessionsPage] {

  private def sortedCategories(sessions: List[Session]): List[(String, List[Session])] =
    sessions
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

  override def body(args: Signal[SessionsPage]): HtmlElement =
    def talksForConf(conference: Option[String], draft: Boolean) =
      if draft then SessionsHistory.sessionsForConf(conference)
      else SessionsHistory.sessionsForConf(conference).filter(_.info.confirmed)

    sectionTag(
      className := "container talks-list", // TODO: rename CSS also
      Titles("Sessions"),
      Line(margin = 55),
      div(
        className := "with-toc r-toc",
        children <-- args.map { arg =>
          val categories =
            sortedCategories(talksForConf(arg.conference, arg.withDraft.getOrElse(false)))
          List(
            stickScroll(categories.map(_._1)),
            div(
              className := "content",
              categories
                .foldLeft(Queue.empty[HtmlElement]) { case (acc, (category, talks)) =>
                  acc
                    .enqueue(h2(idAttr := category, className := "content-title", category))
                    .enqueue(
                      div(className := "card-container", talks.sorted.map(SessionCard(_, getConfName(arg.conference))))
                    )
                }
                .toSeq
            )
          )
        }
      )
    )

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