package io.scala.views

import io.scala.Page.*
import io.scala.domaines.{Presentation, Speaker, Talk}
import io.scala.modules.{ClassyButton, SpeakerCard, Title}
import io.scala.views.View
import com.raquo.laminar.api.L.{*, given}
import io.scala.Lexicon

case object Speakers extends View {
  val speaker = Speaker(
    name = "John Doe",
    presentation = Presentation.Keynote,
    job = "Data Engineer",
    company = "Scala.IO",
    socials = List.empty,
    talk = Talk(
      name = "Scala is a good language",
      description = """Scala is considered an incredible language
          |because it is a highly expressive and concise
          |programming language that combines functional
          |and object-oriented programming paradigms. It
          |has built-in support for concurrency, making it
          |easier to write parallel and asynchronous code,
          |and it is fully interoperable with Java. These
          |features, along with its powerful type system
          |and functional programming features, make Scala
          |a popular choice for building large-scale, complex
          |systems in a variety of domains.""".stripMargin.replace("\n", " ")
    )
  )

  override def body: HtmlElement = div(
    Title("Speakers"),
    p(Lexicon.Speakers.catchPhrase),
    ClassyButton(Lexicon.Speakers.callToAction),
    SpeakerCard(speaker),
    styleAttr := "margin: 70px 120px;"
  )
}
