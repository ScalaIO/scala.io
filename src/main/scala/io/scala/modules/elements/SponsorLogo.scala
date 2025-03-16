package io.scala.modules.elements

import com.raquo.laminar.api.L.*

import io.scala.models.Sponsor
object SponsorLogo {
  def apply(sponsor: Sponsor, withRank: Boolean = true) =
    a(
      Image.lazyLoaded(
        src       := sponsor.photoPath,
        alt       := s"${sponsor.name} logo",
        className := (if withRank then s"sponsor-logo-${sponsor.rank.css}" else "")
      ),
      href      := sponsor.website,
      target    := "_blank",
      className := "sponsor-card"
    )
}
