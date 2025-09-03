package io.scala.views

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.data.SponsorsHistory
import io.scala.modules.elements.Image
import io.scala.modules.elements.Titles
import org.scalajs.dom.HTMLHeadingElement

import scala.util.Random

object PreviousSponsors {

  private lazy val previousSponsorsElements = (for {
    rank    <- SponsorsHistory.allRanks
    sponsor <- SponsorsHistory.values(rank)
  } yield {
    a(
      Image.lazyLoaded(
        src   := sponsor.photoPath,
        alt   := s"${sponsor.name} logo",
        width := "100%"
      ),
      className := "sponsor-card-flat"
    )
  }) ++
    Array(
      "axa.png",
      "criteo.png",
      "ebiz.png",
      "ebiznext.png",
      "fyber.png",
      "lightbend.png",
      "lunatech.png",
      "mfglabs.png",
      "scalac.png",
      "tabmo.png",
      "teads.png",
      "valraiso.png",
      "xebia.png",
      "zengularity.png"
    ).map(fileName =>
      div(
        Image.lazyLoaded(
          src       := s"logos/old-sponsors/$fileName",
          alt       := s"Scala.IO 2022 sponsor: $fileName",
          className := "sponsor-logo-id"
        ),
        className := "sponsor-card-flat"
      )
    )

  private def baseDiv(title: ReactiveHtmlElement[HTMLHeadingElement]) = div(
    className := "container",
    title,
    div(
      idAttr := "previous-sponsors",
      div(
        className := "card-container",
        previousSponsorsElements.sortBy(_ => Random.nextInt(3) - 1)
      )
    )
  )

  private val title = "Previous Sponsors"

  def h1Div: Div = baseDiv(Titles(title))

  def h2Div: Div = baseDiv(Titles.medium(title))

}
