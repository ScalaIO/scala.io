package io.scala.data

import io.scala.Lexicon.Footer.Newsletter.description
import io.scala.data.TalksNantes2024.*
import io.scala.data.SpeakersNantes2024.*
import io.scala.domaines.ConfDay
import io.scala.domaines.Room
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.domaines.Time

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.defs.tags.HtmlTags
import com.raquo.laminar.nodes.ReactiveHtmlElement
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import knockoff.Chunk
import knockoff.ChunkParser
import knockoff.HeaderChunk
import knockoff.LinkDefinitionChunk
import org.scalajs.dom.console
import scala.collection.mutable.{HashMap, Queue}
import io.scala.modules.elements.SelfHosted
import io.scala.modules.elements.WebHosted

object TalksInfo:
  lazy val talksBySpeaker =
    allTalks
      .foldLeft(HashMap.empty[Speaker, Set[Talk]].withDefaultValue(Set.empty)): (acc, next) =>
        next.speakers.foreach: speaker =>
          acc(speaker) += next
        acc
      .toMap
      .mapValues(_.toList.sortBy(_.title))

  lazy val allTalks = List(
    Talk(
      title = "Chasing Arrows, in Categories containing Functors and Monads",
      slug = "chasing-arrows-functors-monads",
      description = chasingArrowsFunctorsMonads,
      speakers = ???,//List(jimNewton, uliFahrenberg),
      category = Talk.Category.Algebra,
      day = ConfDay.Friday,
      start = Time(15, 40),
      room = Room.One,
      slides = Some(SelfHosted("chasing-arrows.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=KFhhR6tSy_0&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=12&pp=iAQB")
    ),
    Talk(
      title = "Data pipelines engineering made simple with Scala",
      slug = "data-pipelines-simple",
      description = dataPipeline,
      speakers = ???,//List(raphaelClaude),
      category = Talk.Category.DataEng,
      day = ConfDay.Friday,
      start = Time(9, 50),
      room = Room.One,
      slides = Some(SelfHosted("data-pipeline.pptx", 2024)),
      replay = Some("https://www.youtube.com/watch?v=gS6zOfqUHVs&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=8&pp=iAQB")
    ),
    Talk(
      title = "Scala 3 Compiler Academy Journey",
      slug = "scala-3-compiler-academy-journey",
      description = compilerAcademy,
      speakers = ???,//List(SpeakersInfo.anatoliiKmetiuk),
      category = Talk.Category.Community,
      day = ConfDay.Thursday,
      start = Time(15, 40),
      room = Room.One,
      slides = Some(WebHosted("https://docs.google.com/presentation/d/1DPX8w7I07CIm7rU_z5TNJ_R_A7mf6_lZCMfIVBvrRpk/edit?usp=sharing")),
      replay = Some("https://www.youtube.com/watch?v=wmuVcCSsk-4&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=9&pp=iAQB")
    ),
    Talk(
      title = "Unleashing Scalafix potential with custom rules",
      slug = "unleashing-scalafix-potential",
      description = unleashingScalafix,
      speakers = ???,//List(SpeakersInfo.briceJaglin),
      category = Talk.Category.ToolsEcosystem,
      day = ConfDay.Thursday,
      start = Time(10, 50),
      room = Room.One,
      slides = Some(SelfHosted("scalafix.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=qexJYvo2EwY&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=15&pp=iAQB")
    ),
    Talk(
      title = "🌴 Youpi dansons la Kapoeira en testant nos Kafka streams 🕺 💃",
      slug = "kapoeira-with-kafka-streams",
      description = kafkaStreams,
      speakers = ???,//List(SpeakersInfo.johannaVauchel, SpeakersInfo.mehdiRebiai),
      category = Talk.Category.DataEng,
      day = ConfDay.Thursday,
      start = Time(16, 40),
      room = Room.One,
      slides = Some(WebHosted("https://jvauchel.github.io/kapoeira-dance/index-scalaio.html")),
      replay = Some("https://www.youtube.com/watch?v=BUQFj2jrGj8&list=PLjkHSzY9VuL8we5GaQgqWrZSwiKtLM7Cb&index=5&pp=iAQB")
    ),
    Talk(
      title = "Armored type safety with Iron",
      slug = "armored-type-safety-with-iron",
      description = armoredTypeSafetyIron,
      speakers = ???,//List(SpeakersInfo.raphaelLemaitre, SpeakersInfo.valentinBergeron),
      category = Talk.Category.Modeling,
      day = ConfDay.Thursday,
      start = Time(11, 40),
      room = Room.One,
      slides = Some(WebHosted("https://scalaio-2024.rlemaitre.com")),
      replay = Some("https://www.youtube.com/watch?v=I3BvpzFVBto&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=6&pp=iAQB")
    ),
    Talk(
      title = "Contravariance: intuition building from first principles",
      slug = "contravariance-intuition-building",
      description = contravariance,
      speakers = ???,//List(SpeakersInfo.sophieCollard),
      category = Talk.Category.Modeling,
      day = ConfDay.Friday,
      start = Time(14, 50),
      room = Room.One,
      slides = Some(SelfHosted("Contravariance.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=A7t3b0kymFM&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=13&pp=iAQB")
    ),
    Talk(
      title = "Songwriting in Scala - Creating a DSL for writing Music with ADT's",
      slug = "songwriting-in-scala-dsl-and-adt",
      description = songwritingMusicAdt,
      kind = Talk.Kind.Speech,
      speakers = ???,//List(SpeakersInfo.paulMatthew),
      category = Talk.Category.Modeling,
      day = ConfDay.Thursday,
      start = Time(14, 0),
      room = Room.One,
      replay = Some("https://www.youtube.com/watch?v=Mr6TtLRU5LM&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=4&pp=iAQB")
    ),
    Talk(
      title = "Hands-on Besom: Infrastructure-as-Code with Scala",
      slug = "hands-on-besom-iac-with-scala",
      description = handsOnBesom,
      speakers = ???,//List(SpeakersInfo.lukaszBialy),
      category = Talk.Category.Cloud,
      day = ConfDay.Friday,
      start = Time(10, 50),
      room = Room.One,
      slides = Some(SelfHosted("besom.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=J0vXLHN8YpA&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=7&pp=iAQB")
    ),
    Talk(
      title = "Introduction to Smithy/Smithy4s",
      slug = "introduction-to-smithy-smithy4s",
      description = introSmithy4s,
      speakers = ???,//List(SpeakersInfo.olivierMelois),
      kind = Talk.Kind.Speech,
      category = Talk.Category.Cloud,
      day = ConfDay.Thursday,
      start = Time(14, 50),
      room = Room.One,
      replay = Some("https://www.youtube.com/watch?v=UorOxZTJDxg&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=10&pp=iAQB")
    ),
    Talk(
      title = "Logic Meta-Programming for Functional Languages",
      slug = "logic-meta-programming-for-functional-languages",
      description = logicMetaProg,
      speakers = ???,//List(SpeakersInfo.enzoCrance),
      category = Talk.Category.Algebra,
      day = ConfDay.Friday,
      start = Time(9, 0),
      room = Room.One,
      replay = Some("https://www.youtube.com/watch?v=K3babGnXfEI&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=2&pp=iAQB")
    ),
    Talk(
      title = "Migrating Gallia to Scala 3: the good, the bad, and the very good.",
      slug = "migrating-gallia-to-scala-3",
      description = migratingGalliaToScala3,
      kind = Talk.Kind.Short,
      speakers = ???,//List(SpeakersInfo.anthonyCros),
      category = Talk.Category.ToolsEcosystem,
      day = ConfDay.Friday,
      start = Time(13, 30),
      room = Room.One,
      slides = Some(SelfHosted("gallia-migration.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=DzjvFx5YYik&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=14&pp=iAQB")
    ),
    Talk(
      title = "Types dépendants: de la théorie à la pratique",
      slug = "dependent-types-from-theory-to-practice",
      description = dependantTypes,
      speakers = ???,//List(SpeakersInfo.didierPlaindoux),
      category = Talk.Category.Algebra,
      day = ConfDay.Friday,
      start = Time(14, 0),
      room = Room.One,
      slides = Some(WebHosted("http://d.plaindoux.free.fr/talks/dependent-type/main.html")),
      replay = Some("https://www.youtube.com/watch?v=bYcdxB3Iukc&list=PLjkHSzY9VuL8we5GaQgqWrZSwiKtLM7Cb&index=4&pp=iAQB")
    ),
    Talk(
      title = "Utilisez l'intelligence artificielle dans vos programmes !",
      slug = "use-ai-in-your-programs",
      description = useAiInPrograms,
      speakers = ???,//List(SpeakersInfo.francoisLaroche, SpeakersInfo.samuelBernard),
      category = Talk.Category.AI,
      day = ConfDay.Friday,
      start = Time(11, 40),
      room = Room.One,
      replay = Some("https://www.youtube.com/watch?v=B3Mz-6e9B3s&list=PLjkHSzY9VuL8we5GaQgqWrZSwiKtLM7Cb")
    ),
    Talk(
      title = "My first year in Scala",
      slug = "my-first-year-in-scala",
      description = firstYearScala,
      speakers = ???,//List(SpeakersInfo.monicaMcGuigan),
      category = Talk.Category.Community,
      day = ConfDay.Thursday,
      start = Time(17, 30),
      room = Room.One,
      slides = Some(SelfHosted("first-year-scala.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=Jyn2l1nhwZE&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=3&pp=iAQB")
    ),
    Talk(
      title = "Une autre introduction aux GADTs",
      slug = "intro-to-gadts",
      description = otherIntroGadts,
      kind = Talk.Kind.Keynote,
      speakers = ???,//List(SpeakersInfo.xavierVdW),
      category = Talk.Category.Algebra,
      day = ConfDay.Thursday,
      start = Time(9, 30),
      room = Room.One,
      slides = Some(SelfHosted("GADTs-xavier-vw-woestyne.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=r4c7xuVB9xA&ab_channel=ScalaIOFR")
    ),
    Talk(
      title = "Ukraine's Scala community building lessons",
      slug = "ukraine-scala-community",
      description = ukraineScalaBuildingLessons,
      kind = Talk.Kind.Lightning,
      speakers = ???,//List(SpeakersInfo.olyaMazhara),
      category = Talk.Category.Community,
      day = ConfDay.Friday,
      start = Time(16, 40),
      room = Room.One,
      slides = Some(SelfHosted("scala-community-building-lessons.pptx", 2024)),
      replay = Some("https://www.youtube.com/watch?v=6skPn0evEE8&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=11&pp=iAQB")
    ),
    Talk(
      title = "Unwrapping IO: is it a path that you want to follow?",
      slug = "unwrapping-io",
      description = unwrappingIo,
      kind = Talk.Kind.Keynote,
      speakers = ???,//List(SpeakersInfo.adamWarski),
      category = Talk.Category.Effects,
      day = ConfDay.Friday,
      start = Time(17, 0),
      room = Room.One,
      slides = Some(WebHosted("https://adamw.github.io/unwrapping-io")),
      replay = Some("https://www.youtube.com/watch?v=qR_Od7qbacs&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=1&pp=iAQB")
    ),
    Talk(
      title = "When all the nails are trees, the hammer you need probably looks like a chainsaw",
      slug = "nails-are-tree-need-chainsaw",
      description = nailsTreesChainsaw,
      speakers = ???,//List(SpeakersInfo.matthieuBaechler),
      category = Talk.Category.Algebra,
      kind = Talk.Kind.Short,
      day = ConfDay.Thursday,
      start = Time(13, 30),
      room = Room.One,
      slides = Some(SelfHosted("Chainsaw.pdf", 2024)),
      replay = Some("https://www.youtube.com/watch?v=0unlHZmNYkw&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=5&pp=iAQB")
    ),
    Talk(
      title = "Rex: Migration de Scala 2 à Scala 3",
      slug = "rex-migration-scala-2-to-3",
      description = rexMigrationScala2To3,
      speakers = ???,//List(SpeakersInfo.antoineBlondeau, SpeakersInfo.jeanBaptiseKaiser),
      kind = Talk.Kind.Lightning,
      category = Talk.Category.ToolsEcosystem,
      day = ConfDay.Thursday,
      start = Time(18, 20),
      room = Room.One,
      replay = Some("https://www.youtube.com/watch?v=jGGTqCmwArI&list=PLjkHSzY9VuL8we5GaQgqWrZSwiKtLM7Cb&index=3&pp=iAQB")
    )
  )
