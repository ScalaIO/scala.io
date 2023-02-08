package io.scala

import io.scala.domaines.Sponsor

object Lexicon {
  object Headband {
    val left  = "The 26th and 27th of October 2023 @ Unknown location"
    val right = "10th years edition"
  }

  object Header {
    val speakers  = "Speakers"
    val sponsors  = "Sponsors"
    val venue     = "Venue"
    val schedule  = "Schedule"
    val buyTicket = "Buy a ticket"
  }

  object Speakers {
    val catchPhrase  = "Become a ScalaIO 2023 speakers sharing what you love with the community."
    val callToAction = "Become a speaker"
  }

  object Sponsors {
    val catchPhrase =
      "Become a ScalaIO 2023 sponsor and promote your services, products or simply increase your brand awareness with more than 350 passionate Scala, Spark, and FP languages developers!"
    val callToAction   = "Become a sponsor"
    val callToBrochure = "Download the brochure"

    val brochureUrl =
      "https://docs.google.com/presentation/d/1nZ_6r4r0M1VZNBUE41DtnKr6FVdjFyoWF-zbJ1BtdSw/edit?usp=sharing"

    val sponsors = Seq(
      Sponsor(
        photo = "scalaio.png",
        website = "https://scala.io/",
        rank = Sponsor.SponsorRank.Platinum
      ),
      Sponsor(
        photo = "scalaio.png",
        website = "https://scala.io/",
        rank = Sponsor.SponsorRank.Gold
      ),
      Sponsor(
        photo = "scalaio.png",
        website = "https://scala.io/",
        rank = Sponsor.SponsorRank.Silver
      ),
      Sponsor(
        photo = "scalaio.png",
        website = "https://scala.io/",
        rank = Sponsor.SponsorRank.Community
      ),
      Sponsor(
        photo = "scalaio.png",
        website = "https://scala.io/",
        rank = Sponsor.SponsorRank.Love
      )
    )
  }

  object Footer {
    object Description {
      val text =
        "Scala is an association founded 10 years ago by Scala lovers for Scala lovers. Today, it is the first Scala conference in France."
    }

    object Newsletter {
      val title       = "Newsletter"
      val description = "Be the first to know about our news !"
      val button      = "Subscribe"
    }

    val copyright = "ScalaIO conference, All Rights Reserved."
  }
}
