package io.scala.data

import io.scala.domaines.Sponsor

object SponsorSInfo {
  val allSponsors = List(
    Sponsor(
      name = "Clever Cloud",
      website = "https://www.clever-cloud.com",
      photo = "clever.svg",
      rank = Sponsor.Rank.Platinum
    ),
    Sponsor(
      name = "Clever Cloud",
      website = "https://www.clever-cloud.com",
      photo = "clever.svg",
      rank = Sponsor.Rank.Silver
    ),
    Sponsor(
      name = "Univalence",
      website = "https://univalence.io",
      photo = "univalence-black.webp",
      rank = Sponsor.Rank.Love
    ),
    Sponsor(
      name = "_icilundi",
      website = "https://icilundi.fr",
      photo = "iciLundi.svg",
      rank = Sponsor.Rank.Community
    ),
    Sponsor(
      name = "Numind",
      website = "https://www.numind.ai",
      photo = "numind.svg",
      rank = Sponsor.Rank.Gold
    )
  )
}
