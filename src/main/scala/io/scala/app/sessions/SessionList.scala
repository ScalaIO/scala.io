package io.scala.app.sessions

import com.raquo.laminar.api.L.*
import scala.collection.immutable.Queue

import io.scala.SessionsPage
import io.scala.data.SessionsHistory
import io.scala.data.SessionsHistory.getConfName
import io.scala.models.Session
import io.scala.modules.SessionCard
import io.scala.modules.elements.*
import io.scala.modules.layout.Tabs
import io.scala.views.ReactiveView

case object SessionList extends ReactiveView[SessionsPage] {

  given Ordering[(String, List[Session])] =
    case ((cat1, talks1), (cat2, talks2)) =>
      val (cat1HasKeynote, cat2HasKeynote) = (talks1.exists(_.isKeynote), talks2.exists(_.isKeynote))
      val sizeComparison                   = talks1.size.compareTo(talks2.size)

      if !cat1HasKeynote && cat2HasKeynote then 1
      else if cat1HasKeynote && !cat2HasKeynote then -1
      else if sizeComparison != 0 then -sizeComparison
      else cat1.compareTo(cat2)

  private def sortedCategories(sessions: List[Session]): List[(String, List[Session])] =
    sessions.groupBy(_.info.category).toList.sorted

  def tabWithTOC(categories: List[(String, List[Session])], sessionArg: SessionsPage) =
    div(
      className := "with-toc r-toc",
      stickScroll(categories.map(_._1)),
      div(
        className := "toc-content",
        categories
          .foldLeft(Queue.empty[HtmlElement]) { case (acc, (category, talks)) =>
            acc
              .enqueue(Titles.medium(category, idAttr := category))
              .enqueue(
                Containers.gridCards(talks.sorted.map(SessionCard(_, getConfName(sessionArg.conference))))
              )
          }
          .toSeq
      )
    )
  override def body(args: Signal[SessionsPage]): HtmlElement =
    sectionTag(
      className := "container talks-list", // TODO: rename CSS also
      Titles("Sessions"),
      Line(margin = 4, sizeUnit = "rem"),
      child <-- args.map { arg =>
        val (workshopsByCategory, talksByCategory) =
          SessionsHistory.sessionsForConf(arg).partition(_.isWorkshop)
        val tabs = List(
          "Keynotes" ->
            Containers.gridCards(talksByCategory.filter(_.isKeynote).map(SessionCard(_, getConfName(arg.conference)))),
          "Talks" -> tabWithTOC(sortedCategories(talksByCategory.filter(!_.isKeynote)), arg),
          "Workshops" ->
            Containers.gridCards(workshopsByCategory.map(SessionCard(_, getConfName(arg.conference))))
        )
        Tabs(tabs).render
      }
    )

  private def stickScroll(sortedCategories: List[String]) =
    div(
      className := "table-of-contents",
      idAttr    := "talks-cat-toc",
      div(
        className := "toc-navigation",
        h3("Navigation"),
        sortedCategories.map: cat =>
          a(
            href := s"#${cat}",
            span(cat)
          )
      )
    )

}
