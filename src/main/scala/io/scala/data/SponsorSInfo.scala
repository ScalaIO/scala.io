package io.scala.data

import io.scala.domaines.Sponsor

object SponsorSInfo:
  val allSponsors = List(
    Sponsor(
      name = "Clever Cloud",
      website = "https://www.clever-cloud.com",
      photo = "clever.svg",
      rank = Sponsor.Rank.Platinum
    ),
    Sponsor(
      name = "Univalence",
      website = "https://univalence.io",
      photo = "univalence.svg",
      rank = Sponsor.Rank.Love
    ),
    Sponsor(
      name = "_icilundi",
      website = "https://icilundi.fr",
      photo = "iciLundi.svg",
      rank = Sponsor.Rank.Partner
    ),
    Sponsor(
      name = "Numind",
      website = "https://www.numind.ai",
      photo = "numind.svg",
      rank = Sponsor.Rank.Gold
    ),
    Sponsor(
      name = "PSUG",
      website = "https://www.meetup.com/fr-FR/paris-scala-user-group-psug",
      photo = "psug.svg",
      rank = Sponsor.Rank.Partner,
      gridRow = 3
    ),
    Sponsor(
      name = "LambdaNantes",
      website = "https://mobilizon.fr/@lambdanantes",
      photo = "lambdaNantes.webp",
      rank = Sponsor.Rank.Partner,
      gridRow = 2
    )
  )
