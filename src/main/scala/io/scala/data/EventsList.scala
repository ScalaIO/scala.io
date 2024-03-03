package io.scala.data

import io.scala.domaines.DayOfYear
import io.scala.domaines.Event
import io.scala.domaines.Time
import io.scala.domaines.TimeRange

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object EventsList:
  // Random events
  val eventsList = List(
    Event(
      "Paris",
      "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_640.jpg",
      "ScalaIO Paris",
      "TBD",
      TimeRange(DayOfYear(2024, 3, 1), Time(19, 0), DayOfYear(2024, 3, 1), Time(22, 0)),
      """|ScalaIO Paris is the first ScalaIO event in France. It is a conference for developers who want to learn and share their knowledge about the Scala programming language and its ecosystem.
         |
         |The conference will take place in Paris, at the Espace Charenton, on March 1st, 2024.
         |
         |The event will be held in French and English, and will feature a mix of talks, workshops, and networking opportunities.
         |
         |We hope to see you there!""".stripMargin
    ),
    Event(
      "Montpellier",
      "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_640.jpg",
      "LambdaPlage",
      "TBD",
      TimeRange(DayOfYear(2024, 4, 2), Time(16, 0), DayOfYear(2024, 4, 2), Time(22, 0)),
      """|LambdaPlage is a conference for developers who want to learn and share their knowledge about functional programming and its ecosystem.
         |
         |The conference will take place in Montpellier, at the Corum, on April 2nd, 2024.
         |
         |The event will be held in French and English, and will feature a mix of talks, workshops, and networking opportunities.
         |
         |We hope to see you there!""".stripMargin
    )
  )
