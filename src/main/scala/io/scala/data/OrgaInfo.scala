package io.scala.data

import io.scala.models.Organizer
import io.scala.models.Social

object OrgaInfo:
  val jonathanWinandy = Organizer(
    name = "Jonathan Winandy",
    photo = Some("jWinandy.webp"),
    job = "Staff Software Engineer",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/ahoy_jon"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/jwinandy"),
      Social(Social.Kind.Github, "https://github.com/ahoy-jon"),
      Social(Social.Kind.Other, "https://univalence.io")
    ),
    representative = true
  )

  val marcKarassev = Organizer(
    name = "Marc Karassev",
    photo = Some("mKarassev.webp"),
    job = "Senior Software Engineer @ Freelance",
    socials = List(
      Social(Social.Kind.Github, "https://github.com/markarasev"),
      Social(Social.Kind.Mastodon, "https://mamot.fr/@mark"),
      Social(Social.Kind.Other, "https://markarasev.me")
    ),
    representative = true
  )

  val francoisSarradin = Organizer(
    name = "François Sarradin",
    photo = Some("fSarradin.webp"),
    job = "Data tech lead @ Univalence",
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
    job = "CEO @ Clever Cloud",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/waxzce"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/waxzce/"),
      Social(Social.Kind.Github, "https://github.com/waxzce"),
      Social(Social.Kind.Other, "https://www.waxzce.org")
    ),
    representative = true
  )

  val lucasNouguier = Organizer(
    name = "Lucas Nouguier",
    photo = Some("lNouguier.webp"),
    job = "Software Engineer @ Teads",
    misc = Some("Main website maintainer"),
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/LucasNouguier"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/lucas-nouguier"),
      Social(Social.Kind.Github, "https://github.com/iusildra")
    )
  )

  val warisRadji = Organizer(
    name = "Waris Radji",
    photo = Some("wRadji.webp"),
    job = "Deep Reinforcement Learning Research Engineer @ INRIA",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/warisr"),
      Social(Social.Kind.Github, "https://github.com/riiswa")
    )
  )

  val jbKaiser = Organizer(
    name = "Jean-Baptiste Kaiser",
    photo = Some("jbKaiser.webp"),
    job = "Scala Developer @ Clever Cloud",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/ArendSyl"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/arendsyl"),
      Social(Social.Kind.Github, "https://github.com/Arendsyl")
    )
  )

  val vKasas = Organizer(
    name = "Valentin Kasas",
    photo = Some("vKasas.jpeg"),
    job = "Freelance",
    socials = List(
      Social(Social.Kind.Twitter, "https://x.com/valentinkasas"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/valentin-kasas-937a5837/")
    )
  )

  val allOrga = List(jonathanWinandy, marcKarassev, quentinAdam, lucasNouguier, vKasas)
