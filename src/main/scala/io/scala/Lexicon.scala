package io.scala

import io.scala.domaines.{Sponsor}
import io.scala.domaines.Social

object Lexicon {
  object Headband {
    val left  = "February 15th-16th 2024 @ Nantes"
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
    val catchPhrase  = "Become a ScalaIO 2024 speakers sharing what you love with the community."
    val callToAction = "Become a speaker"
  }

  object Sponsors {
    val catchPhrase =
      "Become a ScalaIO 2024 sponsor and promote your services, products or simply increase your brand awareness with more than 350 passionate Scala, Spark, and FP languages developers!"
    val callToAction   = "Become a sponsor"
    val callToBrochure = "Download the brochure"

    val brochureUrl =
      "https://docs.google.com/presentation/d/1nZ_6r4r0M1VZNBUE41DtnKr6FVdjFyoWF-zbJ1BtdSw/edit?usp=sharing"

    val sponsors = Seq(
      Seq.fill(3)(
        Sponsor(
          photo = "scalaio.png",
          website = "https://scala.io/",
          rank = Sponsor.SponsorRank.Platinum
        )
      ),
      Seq.fill(6)(
        Sponsor(
          photo = "scalaio.png",
          website = "https://scala.io/",
          rank = Sponsor.SponsorRank.Gold
        )
      ),
      Seq.fill(12)(
        Sponsor(
          photo = "scalaio.png",
          website = "https://scala.io/",
          rank = Sponsor.SponsorRank.Silver
        )
      ),
      Seq.fill(2)(
        Sponsor(
          photo = "scalaio.png",
          website = "https://scala.io/",
          rank = Sponsor.SponsorRank.Community
        )
      ),
      Seq.fill(12)(
        Sponsor(
          photo = "scalaio.png",
          website = "https://scala.io/",
          rank = Sponsor.SponsorRank.Love
        )
      )
    ).flatten
  }

  object Venue {
    val catchPhrase = "A cosy place in the heart of Nantes"
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
