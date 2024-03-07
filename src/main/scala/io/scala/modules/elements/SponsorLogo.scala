package io.scala.modules.elements

import io.scala.domaines.Speaker
import io.scala.domaines.Sponsor

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.console
object SponsorLogo {
  def apply(sponsor: Sponsor) =
    a(
      className := "sponsor-card",
      styleAttr := s"grid-column: span ${sponsor.colSpan}; grid-row: span ${sponsor.rowSpan}",
      img(
        className := s"sponsor-logo-${sponsor.rank.css}",
        src       := sponsor.photoPath,
        alt       := s"${sponsor.name} logo"
      ),
      href   := sponsor.website,
      target := "_blank"
    )
}
