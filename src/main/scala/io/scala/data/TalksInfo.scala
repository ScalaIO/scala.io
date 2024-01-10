package io.scala.data

import io.scala.Lexicon.Footer.Newsletter.description
import io.scala.data.SpeakersInfo.allSpeakers
import io.scala.domaines.Kind
import io.scala.domaines.Speaker
import io.scala.domaines.Talk

import scala.collection.mutable.HashMap

object TalksInfo:
  lazy val talksTag =
    allTalks.foldLeft(HashMap.empty[Speaker, Set[Kind]].withDefaultValue(Set.empty)): (acc, next) =>
      next.speakers.foreach: speaker =>
        acc(speaker) += next.kind
      acc
    .toMap

  lazy val allTalks = List(
    Talk(
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
    ),
    Talk(
      title = "Data pipelines engineering made simple with Scala",
      description =
        "Most organizations have data pipelines and those are growing by the minute. But working on data pipelines can be challenging and take / waste a lot of engineering time! The talk is about a state of the art platform used to build, run, evolve and operate data pipelines at Criteo: a project were Scala particularly shine. We will cover problem framing, core ideas and dive into our experience with SQL parsing, query planning and scheduling with quick introductions to the Scala libraries used. The talk is meant to be accessible to any junior developer and has food for though for tenured data engineers too.",
      speakers = List(SpeakersInfo.raphaelClaude)
    ),
    Talk(
      title = "Scala 3 Compiler Academy Journey",
      description = """
        |Open Source is all about collaboration. In the Scala world, we have a lot of eager talent hungry to learn together. The best way to learn to program is by doing. On the other hand, the core Scala projects have an ample supply of maintenance tasks – bug fixing, minor feature implementation, and documentation. To match the demand with the supply, in the Summer of 2021, I initiated the project Scala 3 Compiler Academy. Organized by the Scala Center, the Academy aimed to provide an avenue for Scala engineers to start contributing to the Scala Compiler. The Academy was implemented online pair programming sessions and a YouTube channel where one could learn more about the compiler.

        |Now, two years later, with the format fleshed out and the lessons learnt, I'd like to share our journey with you. After attending my talk, you'll learn how the format works, and how to start a similar Spree for your own project as well!""".stripMargin,
      speakers = List(SpeakersInfo.anatoliiKmetiuk)
    ),
    Talk(
      title = "Unleashing Scalafix potential with custom rules",
      description = """|Scalafix is a mature tool which was started nearly 8 years ago by Ólafur Páll Geirsson at the Scala Center, originally driven by the need to provide a smooth transition from Scala 2.x to Dotty (now known as Scala 3, which provides its own rewriting features).
         |
         |Beyond built-in rules execution to organize imports or remove unused code, Scalafix is very extensible thanks to its stable and beginner-friendly API to inspect Scala sources by traversing Scala Abstract Syntax Trees, raise linter errors and/or suggest rewrites to fix them. Like Scalafmt and the Metals language server, it heavily relies on the vibrant Scalameta ecosystem, allowing support of Scala 2.x and Scala 3.
         |
         |In this talk, we will demystify AST traversal and semantic information lookup to write custom rules and run them locally and/or on CI, through the sbt-scalafix plugin or via Scala Steward.
         |""".stripMargin,
      speakers = List(SpeakersInfo.briceJaglin)
    ),
    Talk(
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
      speakers = List(SpeakersInfo.johannaVauchel, SpeakersInfo.mehdiRebiai)
    ),
    Talk(
      title = "Armored type safety with Iron",
      description =
        """|When designing an application, we often ends up with domain specific types, that all behold constraints that we try to enforce as much as possible : an age is positive, a delivery date can't be in the past, etc. Modeling the data right is a part of the success of scala and functional programming in general, but it also brings either boilerplate (we have to do again and again validation), or rely purely on conventions.
         |
         |But there is hope. Meet the Iron library.
         |
         |Iron is, a type constraint library that allow us to have a safe, declarative and smarter model. It enable us to have a continuous stream of valid data from our API endpoints to the database, and removed a whole class of bugs. Using advanced features like opaque types, inlines and the new macro system, it offer a true 0 cost, 0 dependency library that don't hamper compile time.
         |
         |In this talk, we'll show first the different technique we can use to apply constraints is our domains. Then, we'll present Iron, its features, extensions, and integrations. We'll finish by showcasing a fully-integrated constraint-enforcing app.
         |""".stripMargin,
      speakers = List(SpeakersInfo.valentinBergeron)
    ),
    Talk(
      title = "Contravariance: intuition building from first principles",
      description = """|Contravariance throws many developers off the first time they run into it. This talk will equip the audience with an intuitive understanding of contravariance and the tools for working out from first principles whether a type parameter should be covariant or contravariant.
         |
         |The goal of this talk is to help the audience build an intuition for contravariance from first principles, instead of relying on memorised shortcuts such as “input type parameters tend to be contravariant and output type parameters covariant”.
         |
         |This talks is designed for beginner to intermediate Scala developers. Attendees need to be familiar with subtyping and typeclasses. An understanding of covariance would be beneficial but is not required as we'll cover it in the talk.
         |
         |We'll begin by working with an Animal type hierarchy and some PetRescue and PetClinic typeclasses. By working out what can be substituted for what, we'll begin building up intuition for covariance and contravariance. We'll then test this intuition by working through a second example involving JsonDecoder and JsonEncoder typeclasses.
         |""".stripMargin,
      speakers = List(SpeakersInfo.sophieCollard)
    ),
    Talk(
      title = "Songwriting in Scala - Creating a DSL for writing Music with ADT's",
      description =
        """|Can you compose and produce music with Scala? Absolutely! And I'll show you how. As a longtime professional musician now re-purposed as a Scala developer, I have used functional programming fundamentals to model music and create some simple music web audio applications. The presentation will walk through how and why I made modelling decisions to represent musical properties and events, and I'll write and play a song from scratch in real time using a Domain Specific Language for Music. The talk is aimed at Scala and FP beginners and/or any fans of digital music creation and production and is designed as a fun and interactive introduction to ADT usage in modelling real life data. Topics covered: * How I have chosen to model musical elements and properties. * How Algebraic Data types have been used to create a simple idiomatic music DSL. * Live demonstration of arranging and playing a polyphonic arrangement with the DSL.
         |""".stripMargin,
      speakers = List(SpeakersInfo.paulMatthew)
    ),
    Talk(
      title = "Bootstrap the world for your tests",
      description =
        "During this talk we are going to see how an open source library could help us to spin-up the whole system based on microservice architecture style. The main aim is to have a way to provision, orchestrate System Under Test (SUT) in a state that could be considered as a valid state for reasoning about system itself. Gonna to use Scala, Docker and testcontainers library for Scala API.",
      speakers = List(SpeakersInfo.dawidFurman)
    ),
    Talk(
      title = "Hands-on Besom: Infrastructure-as-Code with Scala",
      description =
        "In my talk I will briefly introduce Besom, a Pulumi SDK for Scala and then swiftly proceed to show - hands on - how to use it to declare cloud infrastructure and deploy your services in a simple yet safe and robust way. Besom is the first step in the new vision of programming with Scala where full products can be built using the same reliable, functional idioms. The talk will be interesting to any Scala developer who wants to upgrade her skills and capabilities in the area of cloud resource management and complete product engineering.",
      speakers = List(SpeakersInfo.lukaszBialy)
    ),
    Talk(
      title = "Introduction to Smithy/Smithy4s",
      description = """|AWS (Amazon Web Services), one of the biggest cloud providers, provides hundreds of services, and offers SDKs in multiple languages to interact with these services. These public-facing services are backed by tens of thousands of services internal to the AWS platform. In order to streamline the development process of such a behemoth, AWS relies on code-generation.
         |
         |Smithy is the culmination of ~14 years of iterations in the field of code-generation. It is an elegant declarative language that enables defining data types, operations and services in a clear and concise manner. The unique aspect of Smithy is that protocol concerns (transport, serialisation) are abstracted away in an extensible annotation-based mechanism. This means that Smithy can be used to describe things like rest/json services, but an infinite amount of other things.
         |
         |Smithy4s is a Scala code-generator that feeds off Smithy files. It is unique in that it retains the protocol-agnostic nature of Smithy :the code-generator is not biased towards any protocol or serialisation mechanism. Users can generate Scala code from Smithy to get case classes and interfaces, that can be wired in runtime-interpreters in an opt-in fashion, to derive http services, or CLIs, or even pure-Scala AWS SDKs. Developers could provide support for specific protocols as third party libraries, without ever having to touch code-generation.
         |
         |This talk will serve as an introduction to the Smithy IDL, and a demo of what is possible with Smithy4s
         |""".stripMargin,
      speakers = List(SpeakersInfo.olivierMelois)
    ),
    Talk(
      title =
        "Logic Meta-Programming for Functional Languages (A tale of mixing two of the most arcane yet powerful programming paradigms)",
      description =
        "Since the beginning of the 21st century, the functional programming paradigm, whose root ideas are now almost 100 years old, has finally trickled down to the rest of the community, bringing more safety and clarity to the more mainstream languages: several languages adopted the idiom of lambda functions, the Java community gave birth to Scala, Clojure, and Kotlin, that all include a substantial amount of functional features; most modern languages embrace immutability by default and feature a form of algebraic data types as well as lazy and/or stateless data structures; etc. In more confidential contexts, the logic programming paradigm, although more obscure, managed to remain stable in a niche of specific use cases: inference and unification problems, rule-based reasoning, combinatorics, constraint programming, operational research, etc. In this talk, we will explore one of the areas where both of these unique yet powerful tools converge: using a logic programming language as a meta-language for a functional programming language. First, we will present its use for implementing a type-checker, then for proof transfer in the context of dependently-typed functional languages.",
      speakers = List(SpeakersInfo.enzoCrance)
    ),
    Talk(
      title = "Migrating Gallia to Scala 3: the good, the bad, and the very good.",
      description = """|Following my previous talk at Scala Days last year, two major pieces of feedback emerged for Gallia: one was the need for performance improvements (for another talk) and the other was migration to Scala 3. The strong demand for the latter surprised me as I did not expect the community to have adopted it so quickly, and so successfully. I therefore decided to tackle the migration first, albeit not without some hesitation and some serious apprehension.
           |
           |In this talk I will highlight my journey tackling this endeavor in concrete terms (where does one start?), lessons I've learned for other projects that will also need migration, and the positive/negative aspects of the overall process. As the title of the presentation suggests, I would rate my overall experience as quite positive. This was unexpected because while Scala 3 looked great on paper, this kind of migration can be an extremely frustrating experience irrespective of the context.
           |
           |I will briefly introduce how certain features worked before (e.g. the now defunct scala.reflect.{api,runtime}) prior to showing the new approach(s), as well as why I chose to handle certain problems the way I did, notably when it came to reflection and macros.
           |""".stripMargin,
      speakers = List(SpeakersInfo.anthonyCros)
    ),
    Talk(
      title = "Types dépendants: de la théorie à la pratique",
      description = """|Quand on parle de types dépendants, une approche informelle est souvent faite en tentant d'initier les gens à travers des exemples plus ou moins simples. Le plus immédiat est bien évidemment le vecteur avec sa taille …
           |
           |Malheureusement, avec une telle approche nous n'abordons pas le sujet en profondeur et l'essence même de ce qu'est la dépendance de type nous passe au dessus de la tête.
           |
           |Durant cette présentation, nous allons sortir de notre zone de confort dès le départ en partant de la théorie pour aller vers la pratique. Partir de la théorie veut dire lecture de formalise pour mieux apprehender ce qu'est effectivement la dépendance de type !
           |
           |Nous aborderons: le type fonctionnel dépendant, la paire dépendante, le type somme et si tout va bien l'égalité propositionnelle.
           |""".stripMargin,
      speakers = List(SpeakersInfo.didierPlaindoux)
    ),
    Talk(
      title = "Utilisez l'intelligence artificielle dans vos programmes !",
      description =
        """|Le but de ce talk est de donner une bonne compréhension de ce qu'il est possible de faire avec les modèles NLP, notamment ceux issus de BERT, qui peuvent être utilisés par des programmes sans avoir besoin de se connecter à des apis externes. L'idée est de présenter la chaîne de traitement, que ce soit en amont ou en aval de l'appel au modèle et l'utilisation du résultat.
           |
           |Une fois ceci fait, je souhaite montrer comment utiliser un modèle en utilisant la bibliothèque DJL, en utilisant pyTorch, et montrer l'implémentation des différentes briques.
           |""".stripMargin,
      speakers = List(SpeakersInfo.francoisLaroche)
    )
  )
