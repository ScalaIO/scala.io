package io.scala.modules

import io.scala.domaines.{Speaker, Sponsor}

import com.raquo.laminar.api.L.{*, given}

object SponsorLogo {
  def apply(sponsor: Sponsor) =
    a(
      img(
        src       := s"/logos/${sponsor.photo}",
        alt       := "logo",
        className := s"sponsor-logo sponsor-logo--${sponsor.rank.css}"
      ),
      href   := sponsor.website,
      target := "_blank"
    )
}
