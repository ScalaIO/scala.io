package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}
import io.scala.domaines.Speaker
import io.scala.domaines.Sponsor
import org.scalajs.dom.console
object SponsorLogo {
  def apply(sponsor: Sponsor) =
    a(
      img(
        src       := sponsor.photoPath,
        alt       := sponsor.name,
        className := s"sponsor-logo-${sponsor.rank.css}"
      ),
      href   := sponsor.website,
      target := "_blank",
      className := "sponsor-card"
    )
}
