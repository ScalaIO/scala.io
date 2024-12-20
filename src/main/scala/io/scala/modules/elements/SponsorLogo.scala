package io.scala.modules.elements

import com.raquo.laminar.api.L.*

import io.scala.models.Sponsor
object SponsorLogo {
  def apply(sponsor: Sponsor) =
    a(
      Image.lazyLoaded(
        src       := sponsor.photoPath,
        alt       := s"${sponsor.name} logo",
        className := s"sponsor-logo-${sponsor.rank.css}"
      ),
      href      := sponsor.website,
      target    := "_blank",
      className := "sponsor-card"
    )
}
