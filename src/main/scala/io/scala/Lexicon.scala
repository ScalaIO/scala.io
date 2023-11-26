package io.scala

import io.scala.domaines.{Presentation, Speaker, Sponsor, Talk}

object Lexicon {
  object Headband {
    val left  = "February 15th-16th 2023 @ Nantes"
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

    val speakers = Seq.fill(20)(
      Speaker(
        name = "John Doe",
        photo = None,
        presentation = Presentation.Keynote,
        job = "Data Engineer",
        company = "Scala.IO",
        socials = List.empty,
        talk = Talk(
          name = "Scala is a good language",
          description = """Scala is considered an incredible language
              |because it is a highly expressive and concise
              |programming language that combines functional
              |and object-oriented programming paradigms. It
              |has built-in support for concurrency, making it
              |easier to write parallel and asynchronous code,
              |and it is fully interoperable with Java. These
              |features, along with its powerful type system
              |and functional programming features, make Scala
              |a popular choice for building large-scale, complex
              |systems in a variety of domains.""".stripMargin.replace("\n", " ")
        )
      )
    )
  }

  object Sponsors {
    val catchPhrase =
      "Become a ScalaIO 2023 sponsor and promote your services, products or simply increase your brand awareness with more than 350 passionate Scala, Spark, and FP languages developers!"
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
