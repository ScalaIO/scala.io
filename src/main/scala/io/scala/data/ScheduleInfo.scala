package io.scala.data

import com.raquo.laminar.api.L.*
import java.time.DayOfWeek
import java.time.LocalTime

import io.scala.models.Break
import io.scala.models.Session
import io.scala.models.Special
import io.scala.modules.syntax.*

object ScheduleInfo {
  val minStart = LocalTime.of(8, 0)
  val maxEnd   = LocalTime.of(19, 0)
  val pxByHour = 600

  val room1 = Session.Room("A")
  val room2 = Session.Room("B")

  def time(h: Int, m: Int) = LocalTime.of(h, m)

  extension (d: HtmlElement) def gridArea(area: String) = d.amend(styleAttr := s"grid-area: $area")

  lazy val session: String => Session =
    val schedulables = SessionsHistory.sessionsForSchedule
    (slug) => schedulables.find(_.info.slug == slug).getOrElse(Session.empty)

  val day1 = List(
    time(8, 45).render(),
    session("what-is-true").render(room1),
    time(9, 35).render().gridArea("a4"),
    Break(Break.Kind.Short).render,
    time(9, 40).render(),
    session("higher-standard-of-standard-library").render(room1),
    session("event-sourcing-is-just-a-foldleft-on-events-and-a-decide-function-or-is-it").render(room2),
    time(10, 25).render(),
    Break(Break.Kind.Long).render,
    time(10, 40).render(),
    session("to-effect-or-not-to-effect").render(room1),
    session("can-we-have-the-standard-library-for-macros").render(room2),
    time(11, 25).render(),
    Break(Break.Kind.Short).render,
    time(11, 30).render(),
    session("effets-secondaires").render(room1),
    session("running-llms-locally-with-scala").render(room2),
    time(12, 15).render(),
    Break(Break.Kind.Short).render,
    time(12, 20).render(),
    session("hands-on-direct-style").render(room1),
    session("compile-time-contracts-fiber-safe-data-pipelines").render(room2),
    time(13, 5).render(),
    Break(Break.Kind.Lunch1).render,
    time(14, 20).render(),
    session("ai-assistance-for-scala").render(room1),
    session("balanced-sampling-for-pbt").render(room2),
    time(15, 5).render(),
    Break(Break.Kind.Short).render,
    time(15, 10).render(),
    session("full-stack-scala-ml-platform").render(room1),
    div(className := "blank-card"),
    time(15, 55).render(),
    Break(Break.Kind.Long).render,
    time(16, 10).render(),
    session("api-or-scala-first").render(room1),
    session("zio-from-data-science-perspective").render(room2),
    time(16, 55).render(),
    Break(Break.Kind.Short).render,
    session("catalytic-compilation").render(room1),
    time(17, 0).render(),
    session("best-jvm-for-best-language").render(room2),
    time(17, 45).render(),
    Special(Special.Kind.End).render
  ).zipWithIndex.map { case (x, i) => x.gridArea(s"a${i + 1}") }

  val allDays = Seq(
    (DayOfWeek.FRIDAY, div(day1, className := "day-base-layout day1"))
  )
}
