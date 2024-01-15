package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}
import io.scala.domaines.Speaker
import io.scala.domaines.Sponsor

object SponsorLogo {
  def apply(sponsor: Sponsor) =
    a(
      img(
        src       := s"/logos/${sponsor.photo}",
        alt       := "logo",
        className := s"sponsor-logo-${sponsor.rank.css}"
      ),
      href   := sponsor.website,
      target := "_blank",
      className := "sponsor-card"
    )
}
