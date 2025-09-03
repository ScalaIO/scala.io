package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import io.scala.models.Sponsor
object SponsorLogo {
  def apply(sponsor: Sponsor, withRank: Boolean = true, flat: Boolean = false) =
    a(
      Image.lazyLoaded(
        src       := sponsor.photoPath,
        alt       := s"${sponsor.name} logo",
        className := (if withRank then s"sponsor-logo-${sponsor.rank.css}" else ""),
        width    := "100%",
      ),
      href      := sponsor.website,
      target    := "_blank",
      className := (if (!flat) then "sponsor-card" else "sponsor-card-flat"),
    )
}
