package io.scala

import io.scala.domaines.Time

object Lexicon {
  object Headband:
    val left  = "February 15th-16th 2024 @ Nantes"
    val right = "10th years edition"

  object Header:
    val talks     = "Talks"
    val sponsors  = "Sponsors"
    val venue     = "Venue"
    val schedule  = "Schedule"
    val buyTicket = "Get your ticket"

  object Speakers:
    val catchPhrase  = "Become a ScalaIO 2024 speakers sharing what you love with the community."
    val callToAction = "Become a speaker"

  object Sponsors:
    val catchPhrase: String =
      """Become a ScalaIO 2024 sponsor and promote your services,
        |products or simply increase your brand awareness with passionate
        |Scala, Spark, and FP languages developers!""".stripMargin

    val callToAction   = "Become a sponsor"
    val callToBrochure = "Download the brochure"

    val brochureUrl =
      "https://docs.google.com/presentation/d/1nZ_6r4r0M1VZNBUE41DtnKr6FVdjFyoWF-zbJ1BtdSw/edit?usp=sharing"

  object Schedule:
    val opening        = Array(Time(7, 45), Time(7, 45))
    val firstTalk      = Array(Time(9, 30), Time(9, 0))
    val lunch          = Array(Time(12, 30), Time(12, 30))
    val endOfTalks     = Array(Time(18, 35), Time(18, 0))
    val communityParty = Array(Time(19, 30))

  object Venue {
    val catchPhrase = "A cosy place in the heart of Nantes"
  }

  object Footer {
    object Description {
      val text = "Scala is an association founded in 2013 by Scala supporters for Scala enthusiasts."
    }

    object Newsletter {
      val title       = "Newsletter"
      val description = "Be the first to know about our news !"
      val button      = "Subscribe"
    }

    val copyright = "ScalaIO conference, All Rights Reserved."
  }
}
