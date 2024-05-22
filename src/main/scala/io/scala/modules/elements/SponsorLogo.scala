package io.scala.modules.elements

import com.raquo.laminar.api.L._
import io.scala.domaines.Sponsor
object SponsorLogo {
  def apply(sponsor: Sponsor) =
    a(
      img(
        src       := sponsor.photoPath,
        alt       := s"${sponsor.name} logo",
        className := s"sponsor-logo-${sponsor.rank.css}"
      ),
      href   := sponsor.website,
      target := "_blank",
      className := "sponsor-card"
    )
}
