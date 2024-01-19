package io.scala.data

import io.scala.Lexicon.Footer.Newsletter.description
import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Social
import io.scala.domaines.Speaker

object SpeakersInfo:
  val jimNewton = Speaker(
    name = "Jim Newton",
    photo = Some("jim.webp"),
    job = "",
    company = "EPITA Rennes",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/jim-newton-463600a8"),
      Social(Social.Kind.Other, "https://www.lrde.epita.fr/wiki/User:Jnewton")
    ),
    confirmed = true
  )
  val uliFahrenberg = Speaker(
    name = "Uli Fahrenberg",
    photo = Some("uli.webp"),
    description = """|Ulrich (Uli) Fahrenberg is professor at EPITA Rennes and head of the automata research group at LRE, EPITA Paris. He holds a PhD in mathematics from Aalborg University and has worked at Aalborg University, IRISA Rennes, and at École polytechnique.
         |
         |Fahrenberg works in algebraic topology, concurrency theory, real-time verification, categorical foundations, and general quantitative verification. He has published more than 100 papers in computer science and mathematics. He has been a member of numerous program committees and is steering committee co-chair of the RAMiCS conferences and member of the steering committee of the GETCO conferences.
         |""".stripMargin,
    job = "",
    company = "EPITA Rennes",
    socials = List(
      Social(Social.Kind.Github, "https://github.com/ulifahrenberg"),
      Social(Social.Kind.Other, "https://ulifahrenberg.github.io")
    ),
    confirmed = true
  )

  val raphaelClaude = Speaker(
    name = "Raphael Claude",
    photo = Some("rClaude.webp"),
    description =
      "Staff Dev Lead at Criteo Developer & Site Reliability Engineer that mainly worked with the JVM and Hadoop ecosystem over the last decade.",
    job = "Staff Dev Lead",
    company = "Criteo",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/heapoverflow")
    ),
    confirmed = true
  )

  val anatoliiKmetiuk = Speaker(
    name = "Anatolii Kmetiuk",
    photo = Some("toli.webp"),
    job = "Compiler engineer and community manager",
    description =
      "Lawyer turned Scala Engineer for the past 10 years. Started as a Scala and Machine Learning freelancer for 5 years followed by another 4 years at EPFL at the Scala 3 core team in the roles of compiler engineer and community manager.",
    company = "Scala Center, EPFL",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/akmetiuk"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/akmetiuk"),
      Social(Social.Kind.Github, "https://github.com/anatoliykmetyuk"),
      Social(Social.Kind.Other, "https://akmetiuk.com")
    ),
    confirmed = true
  )

  val briceJaglin = Speaker(
    name = "Brice Jaglin",
    photo = Some("bJaglin.webp"),
    description =
      "I am the main maintainer of Scalafix since 2020, after I discovered the potential of using custom rules in a modular monolith at work and started contributing to the project to make that easier. Currently a Staff Engineer at Swile, I no longer use Scala on a day-to-day basis but I continue to maintain Scalafix on my free time as I have yet to find such a powerful tool in another ecosystem!",
    job = "Staff Engineer",
    company = "Swile",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/bjaglin"),
      Social(Social.Kind.Github, "https://github.com/bjaglin")
    ),
    confirmed = true
  )

  val johannaVauchel = Speaker(
    name = "Johanna Vauchel",
    photo = Some("jVauchel.webp"),
    description =
      "Diplomée de l'INSA de Rouen spécialité Génie Mathématique, j'ai commencé à travailler pour des applications 3D (aménagement intérieur, imagerie médicale, simulateur de vêtements). En 2021 je me suis reconvertie pour devenir Ingénieure Data, dans l'entreprise Lectra à Bordeaux, où je développe des pipelines de données en Scala via Kafka et Snowflake. J'aime former, vulgariser des concepts et partager avec mes collègues ou lors de conférences, des connaissances ou des retours d'expérience. Je suis adepte du sketchnoting et de la facilitation graphique.",
    job = "Data Engineer",
    company = "Lectra",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/johanna-vauchel-05576a78"),
      Social(Social.Kind.Github, "https://github.com/jvauchel"),
      Social(Social.Kind.Other, "https://jvauchel.github.io")
    ),
    confirmed = true
  )

  val mehdiRebiai = Speaker(
    name = "Mehdi Rebiai",
    photo = Some("mRebiai.webp"),
    description =
      """|Développeur depuis bientôt 20 ans, d'abord du Java puis un peu de front, puis beaucoup d'intégration continue et d'automatisation. 
      |Désormais je suis Tech Lead Data chez Lectra : beaucoup de Scala et de Kafka. 
      |Je participe également au projet Open Source Kapoeira : https://github.com/lectra-tech/kapoeira
      |""".stripMargin,
    job = "Data Engineer, Tech Lead",
    company = "Lectra",
    socials = List(
      Social(Social.Kind.Github, "https://github.com/mrebiai"),
      Social(Social.Kind.Other, "https://mrebiai.github.io")
    ),
    confirmed = true
  )

  val valentinBergeron = Speaker(
    name = "Valentin Bergeron",
    photo = None,
    description =
      "Currently Engineering team lead @Ledger, I like Scala, making programming languages, and joking about python",
    job = "Engineering team lead",
    company = "Ledger",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/__vberg"),
      Social(Social.Kind.Github, "https://github.com/vbergeron")
    ),
    confirmed = true
  )

  val sophieCollard = Speaker(
    name = "Sophie Collard",
    photo = None,
    description =
      "Sophie is a software engineer and ex-data scientist with a fondness for strongly typed functional languages. She has worked with Scala for the past 8 years and more recently delved into frontend development using Elm. She is a Lead Software Engineer at JPMorgan where she works on the Model-driven Engineering Platform. Her academic background is in Environmental Engineering (BEng) and Renewable Energy (MSc).",
    job = "Lead Software Engineer",
    company = "JPMorgan",
    socials = List(
      Social(Social.Kind.Github, "https://github.com/sophiecollard")
    ),
    confirmed = true
  )

  val paulMatthew = Speaker(
    name = "Paul Matthews",
    photo = Some("pMatthews.webp"),
    description = """|I'm currently a backend Scala developer at ClearScore with a focus on functional programming.
         |
         |I completed a BSc in Computer Science at the Uni of Hertfordshire in 2020 with first class honours and prior to this have a background in the music industry as a signed recording artist, record producer and audio engineer with credits including number 1 chart positions and platinum and gold sales.
         |""".stripMargin,
    job = "Backend Scala Developer",
    company = "ClearScore",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/matthews-paul"),
      Social(Social.Kind.Other, "https://en.wikipedia.org/wiki/Paul_Matthews_(musician)")
    ),
    confirmed = true
  )

  val lukaszBialy = Speaker(
    name = "Łukasz Biały",
    photo = Some("lBialy.webp"),
    description =
      "Polyglot full-stack developer and functional programming enthusiast. PSE & Scala Developer Advocate @ VirtusLab. Values quality over quantity. Permanent learner with a severe information dependency problem. Enjoys conversations about philosophy and all things related to mind's inner workings. Loves mountains, biking and hiking.",
    job = "Software Engineer",
    company = "VirtusLab",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/lukasz_bialy")
    ),
    confirmed = true
  )

  val olivierMelois = Speaker(
    name = "Olivier Mélois",
    photo = Some("oMelois.webp"),
    description =
      """|My name is Olivier Mélois. I have been using Scala as my main language for since 2013. I maintain weaver-test and smithy4s, and have contribute to many libraries and tools, from ammonite to cats-effect.
         |
         |After leaving in Manchester (UK) for a while, I moved back to the countryside in the south of France, and now work remotely full-time in the countryside. I try to grow vegetables.
         |""".stripMargin,
    job = "Principal Engineer",
    company = "Disney Streaming Services",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/olivier-mélois-99234bbb"),
      Social(Social.Kind.Other, "https://github.com/Baccata")
    ),
    confirmed = true
  )

  val enzoCrance = Speaker(
    name = "Enzo Crance",
    photo = Some("eCrance.webp"),
    description =
      "PhD in Computer Science @ Inria 🇫🇷 Meta-programming for Coq, formal proof automation Functional programming enjoyer",
    job = "PhD Student",
    company = "INRIA",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/cranceltik"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/enzo-crance-099628b9/?locale=en_US"),
      Social(Social.Kind.Other, "https://ecrance.net")
    ),
    confirmed = true
  )

  val anthonyCros = Speaker(
    name = "Anthony Cros",
    photo = Some("aCros.webp"),
    description = """|I am an independent software engineer/architect with 20 years of professional coding experience (see LinkedIn). My focus is on data transformations (especially big data), domain modeling, software architecture in general, and bioinformatics.
         |
         |My past experiences primarily include work in the biomedical field, with positions held at the Ontario Institute for Cancer Research, the Hospital for Sick Children in Toronto, the Children's Hospital of Philadelphia, the BF2I lab (INSA Lyon), and the bacteriology lab at UCBL (Lyon). I also worked for a short period of time in the telecom industry, although a less exciting venture for my tastes.
         |
         |The above experiences included a great many situations in which data had to be modeled with the most extreme care, and processed with just the right trade-offs of practicality and performance. In the biomedical field in particular, errors in judgment on these aspects can have real consequences for patient care, whether indirectly via portals used by researchers (e.g. International Cancer Genome Consortium data portal), or directly in the case of internal hospital systems (typically kept private).
         |
         |All these experiences led me to develop my own tool, Gallia, with the aim of streamlining the process of data transformation, and which draws on all the issues I've encountered using existing tools (as well as their strengths!). I'm also developing a tool intended to help with the modeling aspect - with an emphasis on semantics - and hope to publish it as well at some point in the future.
         |""".stripMargin,
    job = "Software Architect",
    company = "Self-employed",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/anthony_cros"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/anthony-cros-3587b063"),
      Social(Social.Kind.Other, "http://anthonycros.com")
    ),
    confirmed = true
  )

  val didierPlaindoux = Speaker(
    name = "Didier Plaindoux",
    photo = Some("dPlaindoux.webp"),
    description = "Talks about Java, Python, Swift, Kotlin and FP",
    job = "Senior Software Engineer",
    company = "Akawan (Freelance)",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/didier-plaindoux-912b3517"),
      Social(Social.Kind.Github, "https://github.com/d-plaindoux"),
      Social(Social.Kind.Other, "http://d.plaindoux.free.fr")
    ),
    confirmed = true
  )

  val francoisLaroche = Speaker(
    name = "François Laroche",
    photo = Some("fLaroche.webp"),
    description = """|Développeur scala depuis plusieurs années, je me passionne pour la performance des applications.
         |
         |Je suis actuellement l'architecte de Make.org où je mets en place des applications haute disponibilité.
         |""".stripMargin,
    job = "Tech Lead",
    company = "NuMind",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/françois-laroche-28406132"),
      Social(Social.Kind.Other, "https://github.com/larochef")
    ),
    confirmed = true
  )

  val raphaelLemaitre = Speaker(
    name = "Raphaël Lemaitre",
    photo = Some("rLemaitre.webp"),
    description =
      """|Bonjour! I'm Raphaël Lemaitre, a seasoned programmer passionate about crafting efficient and sustainable technology. Since embarking on my career journey in 2000, I've been deeply involved in the realm of software development, with a focus on JVM languages like Java and Scala. My technical proficiency includes functional programming, database technologies such as PostgreSQL and Cassandra, along with system design.
         |
         |Currently, in my role as a Back-end Senior Staff Engineer at Ledger, I concentrate on designing and writing maintainable systems. My goal is to develop software that is not only functional but also robust and adaptable for future advancements. I strive for clean, effective, and scalable solutions that emphasize long-term sustainability in software design.
         |
         |Beyond my professional pursuits, I'm an enthusiastic golfer, an escape game enthusiast, and a proud father. I firmly believe in the power of continuous learning and sharing knowledge, which led me to create a blog for exchanging ideas, particularly focusing on ADHD strategies for developers.
         |
         |My interests span from software development to history and technology, where I eagerly engage in conversations that ignite curiosity and foster innovation. Join me in exploring the dynamic landscape of technology, as we seek to build more efficient, maintainable, and impactful technological solutions.
         |""".stripMargin,
    job = "Senior Staff Engineer",
    company = "Ledger",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/rlemaitre"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/rlemaitre"),
      Social(Social.Kind.Github, "https://github.com/rlemaitre"),
      Social(Social.Kind.Other, "https://rlemaitre.com")
    ),
    confirmed = true
  )

  val olyaMazhara = Speaker(
    name = "Olya Mazhara",
    photo = Some("oMazhara.webp"),
    description =
      """|Scala has been my coding playground for about 5 wonderful years. While I've never taken the stage at conferences, I'm an avid attendee, soaking up the vibrant energy of the Scala community. My journey includes sharing the love for Scala by teaching courses at the university and organising cozy Scala meet-ups with friends. I even celebrated a birthday at one of our Scala gatherings, making it a memorable affair.
         |
         |Working with phenomenal individuals has shaped my perspective, making the Scala world, for me, a realm of camaraderie and shared passion. I believe friendship is the key factor in community creation, and I'm excited to delve into my experiences and insights on building strong, connected Scala communities amidst challenges.
         |""".stripMargin,
    job = "Software Engineer",
    company = "Intellias",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/Olga80572775"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/olya-mazhara-28092681")
    ),
    confirmed = true
  )

  val adamWarski = Speaker(
    name = "Adam Warski",
    photo = Some("aWarski.webp"),
    description =
      """|I am one of the co-founders of SoftwareMill, where I code mainly using Scala and other interesting technologies. I am involved in open-source projects, such as sttp, MacWire, Quicklens, ElasticMQ and others. I have been a speaker at major conferences, such as JavaOne, LambdaConf, Devoxx and ScalaDays.
         |
         |Apart from writing closed- and open-source software, in my free time I try to read the Internet on various (functional) programming-related subjects. Any ideas or insights usually end up with a blog (https://softwaremill.com/blog)
         |""".stripMargin,
    job = "Co-founder",
    company = "SoftwareMill",
    socials = List(
      Social(Social.Kind.Twitter, "http://twitter.com/adamwarski"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/adamwarski"),
      Social(Social.Kind.Other, "http://softwaremill.com")
    ),
    confirmed = true
  )

  val xavierVdW = Speaker(
    name = "Xavier Van de Woestyne",
    photo = Some("xVdW.webp"),
    description =
      "I love programming languages (like OCaml, Elm, Elixir/Erlang, IO, Haskell and Scala), Types systems and Web programming.",
    job = "Software Engineer",
    company = "", // TODO
    socials = List(
      Social(Social.Kind.Twitter, "http://twitter.com/vdwxv"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/xavdw"),
      Social(Social.Kind.Other, "https://xvw.lol")
    ),
    confirmed = true
  )

  val monicaMcGuigan = Speaker(
    name = "Monica McGuigan",
    photo = Some("mMcGuigan.webp"),
    description = """|Monica is a junior Scala developer at JP Morgan, working in a team who aims to improve understanding of the banks’ data and data models.
         |
         |She began her programming journey using C#, then onto a mixture C++, JavaScript and TypeScript throughout her undergraduate Physics degree. She now writes Scala full time in her current position. Monica is also passionate about encouraging those who do not have a Computer Science background into software development roles, as she has been able to apply her transferable problem-solving skills from a scientific discipline to programming and is really enjoying it!
         |""".stripMargin,
    job = "Software Engineer",
    company = "JP Morgan",
    socials = List(
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/monica-mcguigan")
    ),
    confirmed = true
  )

  val matthieuBaechler = Speaker(
    name = "Matthieu Baechler",
    photo = Some("mBaechler.webp"),
    description = "I’m a veteran backend developer, living in Ardèche and writing software as a contractor for a living. I wrote Java for years and I’m now mainly writing Scala and Elm on a daily basis. My domains of interest are FP, reactive programming, distributed architecture, CQRS/ES and performance analysis.",
    socials = List(
      Social(Social.Kind.Twitter, "https://twitter.com/m_baechler"),
      Social(Social.Kind.Linkedin, "https://www.linkedin.com/in/matthieu-baechler-12336011")
    ),
    job = "Developer",
    company = "Conduktor",
    confirmed = false
  )

  val allSpeakers = allTalks.flatMap(_.speakers).distinct
