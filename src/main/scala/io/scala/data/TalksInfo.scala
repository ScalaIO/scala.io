package io.scala.data

import io.scala.Lexicon.Footer.Newsletter.description
import io.scala.data.MarkdownSource.*
import io.scala.data.SpeakersInfo.allSpeakers
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

object TalksInfo:
  val parser = new ChunkParser
  import parser.{*, given}
  val textBlock = parser.emptyLine.* ~> parser.textBlock <~ parser.emptyLine.*
  val talk      = parser.phrase((parser.header ~ parser.emptyLine.?) ~> (parser.atxHeader.? ~ textBlock).+)
  val linkRegex = """\[([^\[\]]+)\]\s*\(([^\(\)]+)\)""".r

  def parseTalk(md: String) =
    parser.parse(talk, md) match
      case parser.Success(result, next) =>
        result.map:
          case header ~ textBlock =>
            val initialQueue: Queue[HtmlElement] =
              Queue.from { header.map { case HeaderChunk(_, content) => h4(content) } }

            val text = textBlock.content
            val withLinks = linkRegex
              .findAllMatchIn(text)
              .foldLeft((initialQueue, 0)):
                case ((queue, start), next) =>
                  queue += span(text.substring(start, next.start))
                  queue += a(href := next.group(2), target := "_blank", next.group(1), aria.label := next.group(2))
                  (queue, next.end)
            withLinks._1.enqueue(span(text.substring(withLinks._2)))
      case fail: parser.NoSuccess =>
        List(Queue(p("To be announced")))

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
      description = chasing_arrows_functors_monads,
      speakers = List(SpeakersInfo.jimNewton, SpeakersInfo.uliFahrenberg),
      category = Talk.Category.Algebra,
      day = ConfDay.Friday,
      start = Time(15, 40),
      room = Room.One
    ),
    Talk(
      title = "Data pipelines engineering made simple with Scala",
      slug = "data-pipelines-simple",
      description = data_pipepline,
      speakers = List(SpeakersInfo.raphaelClaude),
      category = Talk.Category.DataEng,
      day = ConfDay.Friday,
      start = Time(9, 50),
      room = Room.One
    ),
    Talk(
      title = "Scala 3 Compiler Academy Journey",
      slug = "scala-3-compiler-academy-journey",
      description = compiler_academy,
      speakers = List(SpeakersInfo.anatoliiKmetiuk),
      category = Talk.Category.Community,
      day = ConfDay.Thursday,
      start = Time(15, 40),
      room = Room.One
    ),
    Talk(
      title = "Unleashing Scalafix potential with custom rules",
      slug = "unleashing-scalafix-potential",
      description = unleashing_scalafix,
      speakers = List(SpeakersInfo.briceJaglin),
      category = Talk.Category.ToolsEcosystem,
      day = ConfDay.Thursday,
      start = Time(10, 50),
      room = Room.One
    ),
    Talk(
      title = "🌴 Youpi dansons la Kapoeira en testant nos Kafka streams 🕺 💃",
      slug = "kapoeira-with-kafka-streams",
      description = kafka_streams,
      speakers = List(SpeakersInfo.johannaVauchel, SpeakersInfo.mehdiRebiai),
      category = Talk.Category.DataEng,
      day = ConfDay.Thursday,
      start = Time(16, 40),
      room = Room.One
    ),
    Talk(
      title = "Armored type safety with Iron",
      slug = "armored-type-safety-with-iron",
      description = armored_type_safety_iron,
      speakers = List(SpeakersInfo.raphaelLemaitre, SpeakersInfo.valentinBergeron),
      category = Talk.Category.Modeling,
      day = ConfDay.Thursday,
      start = Time(11, 40),
      room = Room.One
    ),
    Talk(
      title = "Contravariance: intuition building from first principles",
      slug = "contravariance-intuition-building",
      description = contravariance,
      speakers = List(SpeakersInfo.sophieCollard),
      category = Talk.Category.Modeling,
      day = ConfDay.Friday,
      start = Time(14, 50),
      room = Room.One
    ),
    Talk(
      title = "Songwriting in Scala - Creating a DSL for writing Music with ADT's",
      slug = "songwriting-in-scala-dsl-and-adt",
      description = songwriting_music_adt,
      kind = Talk.Kind.Speech,
      speakers = List(SpeakersInfo.paulMatthew),
      category = Talk.Category.Modeling,
      day = ConfDay.Thursday,
      start = Time(14, 0),
      room = Room.One
    ),
    Talk(
      title = "Hands-on Besom: Infrastructure-as-Code with Scala",
      slug = "hands-on-besom-iac-with-scala",
      description = hands_on_besom,
      speakers = List(SpeakersInfo.lukaszBialy),
      category = Talk.Category.Cloud,
      day = ConfDay.Friday,
      start = Time(10, 50),
      room = Room.One
    ),
    Talk(
      title = "Introduction to Smithy/Smithy4s",
      slug = "introduction-to-smithy-smithy4s",
      description = intro_smithy4s,
      speakers = List(SpeakersInfo.olivierMelois),
      kind = Talk.Kind.Speech,
      category = Talk.Category.Cloud,
      day = ConfDay.Thursday,
      start = Time(14, 50),
      room = Room.One
    ),
    Talk(
      title = "Logic Meta-Programming for Functional Languages",
      slug = "logic-meta-programming-for-functional-languages",
      description = logic_meta_prog,
      speakers = List(SpeakersInfo.enzoCrance),
      category = Talk.Category.Algebra,
      day = ConfDay.Friday,
      start = Time(9, 0),
      room = Room.One
    ),
    Talk(
      title = "Migrating Gallia to Scala 3: the good, the bad, and the very good.",
      slug = "migrating-gallia-to-scala-3",
      description = migrating_gallia_to_scala_3,
      kind = Talk.Kind.Short,
      speakers = List(SpeakersInfo.anthonyCros),
      category = Talk.Category.ToolsEcosystem,
      day = ConfDay.Friday,
      start = Time(13, 30),
      room = Room.One
    ),
    Talk(
      title = "Types dépendants: de la théorie à la pratique",
      slug = "dependent-types-from-theory-to-practice",
      description = dependant_types,
      speakers = List(SpeakersInfo.didierPlaindoux),
      category = Talk.Category.Algebra,
      day = ConfDay.Friday,
      start = Time(14, 0),
      room = Room.One
    ),
    Talk(
      title = "Utilisez l'intelligence artificielle dans vos programmes !",
      slug = "use-ai-in-your-programs",
      description = use_ai_in_programs,
      speakers = List(SpeakersInfo.francoisLaroche),
      category = Talk.Category.AI,
      day = ConfDay.Friday,
      start = Time(11, 40),
      room = Room.One
    ),
    Talk(
      title = "My first year in Scala",
      slug = "my-first-year-in-scala",
      description = first_year_scala,
      speakers = List(SpeakersInfo.monicaMcGuigan),
      category = Talk.Category.Community,
      day = ConfDay.Thursday,
      start = Time(17, 30),
      room = Room.One
    ),
    Talk(
      title = "Une autre introduction aux GADTs",
      slug = "intro-to-gadts",
      description = other_intro_gadts,
      kind = Talk.Kind.Keynote,
      speakers = List(SpeakersInfo.xavierVdW),
      category = Talk.Category.Algebra,
      day = ConfDay.Thursday,
      start = Time(9, 30),
      room = Room.One
    ),
    Talk(
      title = "To be announced",
      slug = "lightning-1",
      description = "To be announced",
      kind = Talk.Kind.Lightning,
      speakers = List(SpeakersInfo.olyaMazhara),
      category = Talk.Category.Community,
      day = ConfDay.Friday,
      start = Time(16, 40),
      room = Room.One
    ),
    Talk(
      title = "To be announced",
      slug = "keynote-1",
      description = "To be announced",
      kind = Talk.Kind.Keynote,
      speakers = List(SpeakersInfo.adamWarski),
      category = Talk.Category.Effects,
      day = ConfDay.Friday,
      start = Time(17, 0),
      room = Room.One
    ),
    Talk(
      title = "When all the nails are trees, the hammer you need probably looks like a chainsaw",
      slug = "nails-are-tree-need-chainsaw",
      description = nails_trees_chainsaw,
      speakers = List(SpeakersInfo.matthieuBaechler),
      category = Talk.Category.Algebra,
      kind = Talk.Kind.Short,
      day = ConfDay.Thursday,
      start = Time(13, 30),
      room = Room.One
    ),
    Talk(
      title = "Rex: Migration de Scala 2 à Scala 3",
      slug = "rex-migration-scala-2-to-scala-3",
      description = "To be announced",
      speakers = List(SpeakersInfo.antoineBlondeau, SpeakersInfo.jeanBaptiseKaiser),
      kind = Talk.Kind.Lightning,
      category = Talk.Category.ToolsEcosystem,
      day = ConfDay.Thursday,
      start = Time(18, 20),
      room = Room.One
    )
  )
