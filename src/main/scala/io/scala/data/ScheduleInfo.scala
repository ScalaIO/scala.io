package io.scala.data

import com.raquo.laminar.api.L.*
import java.time.DayOfWeek
import java.time.LocalTime

import io.scala.models.Break
import io.scala.models.Session
import io.scala.models.Special
import io.scala.modules.syntax.*

object ScheduleInfo {
  val minStart = LocalTime.of(9, 0)
  val maxEnd   = LocalTime.of(19, 0)
  val pxByHour = 600

  val room1 = Session.Room("A")
  val room2 = Session.Room("B")
  val room3 = Session.Room("C")

  def time(h: Int, m: Int) = LocalTime.of(h, m)

  extension (d: HtmlElement) def gridArea(area: String) = d.amend(styleAttr := s"grid-area: $area")

  def session(slug: String) = Event.Current.sessions.find(_.info.slug == slug).getOrElse(Session.empty)

  val day1 = List(
    time(9, 30).render(),
    session("surprise-opening-keynote").render(room1),
    time(10, 30).render(),
    Break(Break.Kind.Large).render,
    time(10, 45).render(),
    session("building-robust-applications-with-kyo-intro").render(room1),
    session("complex-web-uis-with-fastscala").render(room2),
    time(11, 15).render(),
    Break(Break.Kind.Large).render,
    time(11, 30).render(),
    session("say-goodbye-to-implicits-contextual-abstractions-in-scala3").render(room1),
    session("scala-pattern-matching-for-deeply-embedded-dsl").render(room2),
    time(12, 15).render(),
    Break(Break.Kind.Lunch2).render,
    time(13, 30).render(),
    session("tools-for-scala-startups").render(room1),
    session("beyond-basics-lsp").render(room2),
    session("building-robust-applications-with-kyo").render(room3),
    time(14, 15).render(),
    Break(Break.Kind.Large).render,
    time(14, 30).render(),
    session("joyful-and-secure-publishing-to-maven-central").render(room1),
    session("computer-algebra-scala").render(room2),
    time(15, 15).render(),
    Break(Break.Kind.Large).render,
    time(15, 30).render(),
    session("full-stack-scala-the-easy-way").render(room1),
    session("calculating-is-funnier-than-guessing").render(room2),
    session("complex-web-uis-with-fastscala-workshop").render(room3),
    time(16, 15).render(),
    Break(Break.Kind.Large).render,
    time(16, 30).render(),
    session("escaping-false-dichotomy-with-sanely-automatic-derivation").render(room1),
    session("recognizing-regular-patterns-in-heterogeneous-sequences").render(room2),
    time(17, 15).render(),
    Special(Special.Kind.End).render
  ).zipWithIndex.map { case (x, i) => x.gridArea(s"a${i + 1}") }

  val day2 = List(
    time(9, 15).render(),
    session("how-functional-is-direct-style").render(room1),
    session("mill-builds-in-scala-3-a-migration-story").render(room2),
    session("tools-for-scala-workshop").render(room3),
    time(10, 0).render(),
    Break(Break.Kind.Large).render,
    time(10, 15).render(),
    session("optimize-data-transfer-kafka-to-bq").render(room1),
    session("zk-voting-in-scala-and-rust").render(room2),
    time(11, 0).render(),
    Break(Break.Kind.Large).render,
    time(11, 15).render(),
    session("plowing-postgres-and-unearthing-hidden-gems").render(room1),
    session("scary-stuff-less-metaprogramming-in-scala3").render(room2),
    time(12, 0).render(),
    Break(Break.Kind.Lunch1).render,
    time(13, 30).render(),
    session("welcome-to-scala-2.7.7-in-2024").render(room1),
    session("profunctors-for-fun-and-profit").render(room2),
    session("tools-for-scala-workshop").render(room3),
    time(14, 15).render(),
    Break(Break.Kind.Large).render,
    time(14, 30).render(),
    session("scala-performance-when-you-should-betray-fp-principles").render(room1),
    session("decisions4s-conditionals-higher-kinded-data-scala-3").render(room2),
    time(15, 15).render(),
    Break(Break.Kind.Large).render,
    time(15, 30).render(),
    session("better-scala-builds-with-mill").render(room1),
    session("embracing-failure-web-development-with-scala-3").render(room2),
    time(16, 15).render(),
    Break(Break.Kind.Large).render,
    time(16, 30).render(),
    session("programming-a-language").render(room1),
    time(17, 15).render(),
    Special(Special.Kind.End).render
  ).zipWithIndex.map { case (x, i) => x.gridArea(s"a${i + 1}") }

  val allDays = Seq(
    (DayOfWeek.THURSDAY, div(day1, className := "day-base-layout day1")),
    (DayOfWeek.FRIDAY, div(day2, className := "day-base-layout day2"))
  )
}
