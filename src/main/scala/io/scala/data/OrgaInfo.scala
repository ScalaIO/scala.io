package io.scala.data

import io.scala.Lexicon.Footer.Newsletter.description
import io.scala.domaines.Organizer
import io.scala.domaines.Social

object OrgaInfo:
  val johnathanWinandy = Organizer(
    name = "Johnathan Winandy",
    photo = Some("jWinandy.webp"),
    job = "Data Engineering Manager",
    company = "Univalence",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/ahoy_jon"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/jwinandy"),
      Social(Social.Kind.Github, "https://github.com/ahoy-jon"),
      Social(Social.Kind.Other, "https://univalence.io")
    )
  )

  val marcKarassev = Organizer(
    name = "Marc Karassev",
    photo = Some("mKarassev.webp"),
    job = "Scala Freelance",
    company = "Self-employed",
    socials = List(
      Social(Social.Kind.Github, "https://github.com/markarasev")
    )
  )

  val francoisSarradin = Organizer(
    name = "François Sarradin",
    photo = Some("fSarradin.webp"),
    job = "Data tech lead",
    company = "Univalence",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/fsarradin"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/fsarradin"),
      Social(Social.Kind.Github, "https://github.com/fsarradin"),
      Social(Social.Kind.Other, "https://kerflyn.wordpress.com")
    )
  )

  val quentinAdam = Organizer(
    name = "Quentin Adam",
    photo = Some("qAdam.webp"),
    job = "CEO",
    company = "Clever Cloud",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/waxzce"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/waxzce/"),
      Social(Social.Kind.Github, "https://github.com/waxzce"),
      Social(Social.Kind.Other, "https://www.waxzce.org")
    )
  )

  val lucasNouguier = Organizer(
    name = "Lucas Nouguier",
    photo = Some("lNouguier.webp"),
    job = "Student",
    company = "Polytech Montpellier",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/LucasNouguier"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/lucas-nouguier"),
      Social(Social.Kind.Github, "https://github.com/iusildra")
    )
  )

  val warisRadji = Organizer(
    name = "Waris Radji",
    photo = Some("wRadji.webp"),
    job = "Deep Reinforcement Learning Research Engineer",
    company = "INRIA",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/warisr"),
      Social(Social.Kind.Github, "https://github.com/riiswa")
    )
  )

  val allOrga = List(johnathanWinandy, marcKarassev, francoisSarradin, quentinAdam, lucasNouguier, warisRadji)
