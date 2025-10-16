package io.scala

import java.time.LocalTime

object Lexicon {
  object Headband:
    val left  = "February 15th-16th 2024 @ Nantes"
    val right = "10th years edition"

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
      "https://docs.google.com/presentation/d/e/2PACX-1vQnOK-iGp4EJ9Etp_z5yZv8nxOH61cxg_j2xHrOvebNoD0x5mO9OL-2Fv8O5yKBNgWMC74eMgKXc3En/pub?start=false&loop=false&slide=id.p"

  object Schedule:
    val opening        = Array(LocalTime.of(7, 45))
    val firstTalk      = Array(LocalTime.of(8, 40))
    val lunch          = Array(LocalTime.of(12, 20))
    val endOfTalks     = Array(LocalTime.of(17, 55))
    val communityParty = Array(LocalTime.of(18, 30))

  object Venue {
    val catchPhrase = "A cosy place in the heart of Nantes"
  }

  object Footer {
    object Description {
      val text = "ScalaIO is an association founded in 2013 by Scala supporters for Scala enthusiasts."
    }

    object Newsletter {
      val description = "Be the first to know about our news !"
      val button      = "Subscribe"
    }

    val copyright = "ScalaIO conference, All Rights Reserved."
  }
}
