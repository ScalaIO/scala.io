package io.scala.data

import io.scala.Lexicon.Speakers.speakers
import io.scala.domaines.Social
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.data.SpeakersInfo.johannaVauchel
import io.scala.Lexicon.Footer.Newsletter.description

object TalksInfo:
  val chasingArrows = Talk(
    title = "Chasing Arrows, in Categories containing Functors and Monads",
    description =
      """|At EPITA (www.epita.fr), we recently presented a course in Category Theory for Programmers (ct4p), where we presented Categories with a mathematical foundation. To make everything make sense, we culminated the course with a history of mapping functions in Lisp and other functional programming languages. Simple lists from programming languages of the 1980s have be generalized in two different, incompatible ways: 1) powerful list manipulation libraries, and 2) functors and monads.
         |
         |We used Scala as a vehicle to develop commutative diagrams for mapping functions and showed how flatMap makes the diagrams in a Kleisli category commute.
         |
         |This presentation was judged as illuminating for the mathematicians helping them understand the programming perspective, and also illuminating for the programmers helping them understand what monad are and how they relate to Category Theory.
         |
         |This ScalaIO talk will be a overview of the EPITA CT4P course, with emphasis on commutation diagrams and simple Scala programming. The talk will be accessible to intermediate programmers of Scala, Python, Lisp and other languages.
         |""".stripMargin,
    speakers = List(SpeakersInfo.jimNewton, SpeakersInfo.uliFahrenberg)
  )

  val dataPipeline = Talk(
    title = "Data pipelines engineering made simple with Scala",
    description =
      "Most organizations have data pipelines and those are growing by the minute. But working on data pipelines can be challenging and take / waste a lot of engineering time! The talk is about a state of the art platform used to build, run, evolve and operate data pipelines at Criteo: a project were Scala particularly shine. We will cover problem framing, core ideas and dive into our experience with SQL parsing, query planning and scheduling with quick introductions to the Scala libraries used. The talk is meant to be accessible to any junior developper and has food for though for tenured data engineers too.",
    speakers = List(SpeakersInfo.raphaelClaude)
  )

  val compilerAcademy = Talk(
    title = "Scala 3 Compiler Academy Journey",
    description = """
        |Open Source is all about collaboration. In the Scala world, we have a lot of eager talent hungry to learn together. The best way to learn to program is by doing. On the other hand, the core Scala projects have an ample supply of maintenance tasks – bug fixing, minor feature implementation, and documentation. To match the demand with the supply, in the Summer of 2021, I initiated the project Scala 3 Compiler Academy. Organized by the Scala Center, the Academy aimed to provide an avenue for Scala engineers to start contributing to the Scala Compiler. The Academy was implemented online pair programming sessions and a YouTube channel where one could learn more about the compiler.

        |Now, two years later, with the format fleshed out and the lessons learnt, I'd like to share our journey with you. After attending my talk, you'll learn how the format works, and how to start a similar Spree for your own project as well!""".stripMargin,
    speakers = List(SpeakersInfo.anatoliiKmetiuk)
  )

  val scalaFix = Talk(
    title = "Unleashing Scalafix potential with custom rules",
    description = """|Scalafix is a mature tool which was started nearly 8 years ago by Ólafur Páll Geirsson at the Scala Center, originally driven by the need to provide a smooth transition from Scala 2.x to Dotty (now known as Scala 3, which provides its own rewriting features).
         |
         |Beyond built-in rules execution to organize imports or remove unused code, Scalafix is very extensible thanks to its stable and beginner-friendly API to inspect Scala sources by traversing Scala Abstract Syntax Trees, raise linter errors and/or suggest rewrites to fix them. Like Scalafmt and the Metals language server, it heavily relies on the vibrant Scalameta ecosystem, allowing support of Scala 2.x and Scala 3.
         |
         |In this talk, we will demystify AST traversal and semantic information lookup to write custom rules and run them locally and/or on CI, through the sbt-scalafix plugin or via Scala Steward.
         |""".stripMargin,
    speakers = List(SpeakersInfo.briceJaglin)
  )

  val kafkaStreams = Talk(
    title = "🌴 Youpi dansons la Kapoeira en testant nos Kafka streams 🕺 💃",
    description = """|Avec le monde de la Data en perpétuelle croissance, l'outil Open Source Apache Kafka est devenu incontournable grâce à ces capacités de collecte, de traitement temps réel, de stockage et d'analyse de données.
         |
         |Mais comment facilement tester toutes ces opérations dans un environnement d'intégration ? La solution Open Source Kapoeira tente de répondre à cette problématique.
         |
         |Sais-tu danser la Kapoeira ? 🎶 C'est du Cucumber 🥒 et du Kafka ! 🎬 🎺
         |
         |C'est à l'aide d'un langage simple et normalisé basé sur Gherkin, qu'un test automatisé Kapoeira pourra être co-écrit par un QA, un PO ou un DEV. Il pourra servir à la fois de spécification, de test d'acceptance et de test de transformation de données sur une vraie infrastructure Kafka.
         |
         |Burger Quiz 🍔 : venez découvrir Kapoeira à travers une démo live testant un Kafka Stream qui fabrique des hamburgers !
         |
         |Si vous êtes intéressés, nous serons ravis de récolter vos retours et vos contributions pour nous aider à améliorer cet outil.
         |""".stripMargin,
    speakers = List(SpeakersInfo.johannaVauchel)
  )

  val allTalks = List(
    chasingArrows,
    dataPipeline,
    compilerAcademy,
    scalaFix,
    kafkaStreams
  )

object SpeakersInfo:
  val jimNewton = Speaker(
    name = "Jim Newton",
    photo = Some("jim.jpeg"),
    job = "",
    company = "EPITA Rennes",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/jim-newton-463600a8"),
      Social(Social.Kind.Other, "https://www.lrde.epita.fr/wiki/User:Jnewton")
    )
  )
  val uliFahrenberg = Speaker(
    name = "Uli Fahrenberg",
    photo = Some("uli.jpg"),
    description =
      """|Ulrich (Uli) Fahrenberg is professor at EPITA Rennes and head of the automata research group at LRE, EPITA Paris. He holds a PhD in mathematics from Aalborg University and has worked at Aalborg University, IRISA Rennes, and at École polytechnique.
         |
         |Fahrenberg works in algebraic topology, concurrency theory, real-time verification, categorical foundations, and general quantitative verification. He has published more than 100 papers in computer science and mathematics. He has been a member of numerous program committees and is steering committee co-chair of the RAMiCS conferences and member of the steering committee of the GETCO conferences.
         |""".stripMargin,
    job = "",
    company = "EPITA Rennes",
    socials = List(
      Social(Social.Kind.Github, "https://github.com/ulifahrenberg"),
      Social(Social.Kind.Other, "https://ulifahrenberg.github.io")
    )
  )

  val raphaelClaude = Speaker(
    name = "Raphael Claude",
    photo = Some("rClaude.jpeg"),
    description = "Staff Dev Lead at Criteo Developer & Site Reliability Engineer that mainly worked with the JVM and Hadoop ecosystem over the last decade.",
    job = "Staff Dev Lead",
    company = "Criteo",
    socials = List(
      Social(Social.Kind.Twitter, "http://twitter.com/heapoverflow")
    )
  )

  val anatoliiKmetiuk = Speaker(
    name = "Anatolii Kmetiuk",
    photo = Some("toli.webp"),
    job = "Compiler engineer and community manager",
    description = "Lawyer turned Scala Engineer for the past 10 years. Started as a Scala and Machine Learning freelancer for 5 years followed by another 4 years at EPFL at the Scala 3 core team in the roles of compiler engineer and community manager.",
    company = "Scala Center, EPFL",
    socials = List(
      Social(Social.Kind.Twitter, "http://twitter.com/akmetiuk"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/akmetiuk"),
      Social(Social.Kind.Github, "https://github.com/anatoliykmetyuk"),
      Social(Social.Kind.Other, "https://akmetiuk.com")
    )
  )

  val briceJaglin = Speaker(
    name = "Brice Jaglin",
    photo = Some("bJaglin.jpeg"),
    description = "Brice is the main maintainer of Scalafix since 2020, just after he discovered the potential of using custom rules in a modular monolith at work and started contributing to the project to make that easier. Currently a Staff Engineer at Swile, he no longer uses Scala on a day-to-day basis but continues to maintain Scalafix on his free time as he has yet to find such a powerful tool in another ecosystem!",
    job = "Staff Engineer",
    company = "Swile",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/bjaglin"),
      Social(Social.Kind.Github, "https://github.com/bjaglin")
    )
  )

  val johannaVauchel = Speaker(
    name = "Johanna Vauchel",
    photo = Some("jVauchel.jpeg"),
    description = "Diplomée de l’INSA de Rouen spécialité Génie Mathématique, j’ai commencé à travailler pour des applications 3D (aménagement intérieur, imagerie médicale, simulateur de vêtements). En 2021 je me suis reconvertie pour devenir Ingénieure Data, dans l’entreprise Lectra à Bordeaux, où je développe des pipelines de données en Scala via Kafka et Snowflake. J’aime former, vulgariser des concepts et partager avec mes collègues ou lors de conférences, des connaissances ou des retours d’expérience. Je suis adepte du sketchnoting et de la facilitation graphique.",
    job = "Data Engineer",
    company = "Lectra",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/johanna-vauchel-05576a78"),
      Social(Social.Kind.Github, "https://github.com/jvauchel"),
      Social(Social.Kind.Other, "https://jvauchel.github.io")
    )
  )

  val allSpeakers = List(
    jimNewton,
    uliFahrenberg,
    raphaelClaude,
    anatoliiKmetiuk,
    briceJaglin,
    johannaVauchel
  )
