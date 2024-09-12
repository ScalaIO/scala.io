package io.scala.models

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.{ReactiveHtmlElement, ReactiveSvgElement}
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import org.scalajs.dom
import org.scalajs.dom.{HTMLDivElement, HTMLParagraphElement, SVGSVGElement}

import io.scala.data.current
import io.scala.data.parsers.Parsers
import io.scala.extensions.*
import io.scala.modules.TalkCard
import io.scala.modules.elements.Paragraphs
import io.scala.svgs.Icons

sealed trait Act:
  def day: DayOfWeek
  def time: LocalTime
  def render: Div

sealed trait Durable:
  def duration: Int

case class Session(
    info: Session.BasicInfo,
    description: String,
    speakers: List[Session.Speaker]
) extends Act
    with Durable:
  lazy val renderDescription: List[ReactiveHtmlElement[HTMLParagraphElement]] =
    Parsers.Description.parseTalk(description).map(Paragraphs.description(_))

  val day: DayOfWeek     = info.dateTime.nullMap(_.getDayOfWeek)
  val time: LocalTime    = info.dateTime.nullMap(_.toLocalTime)
  def duration: Int      = info.kind.duration
  def render: Div        = TalkCard(this, current)
  def isKeynote: Boolean = info.kind == Session.Kind.Keynote

object Session:
  opaque type Room = String
  extension (room: Room) def show: String = s"Room $room"
  object Room:
    def empty                     = "TBD"
    def apply(room: String): Room = room
    given Ordering[Room]          = Ordering.String.on(_.show)

  def empty: Session      = Session(BasicInfo.empty, "To be announced", List.empty)
  given Ordering[Session] = Ordering.by(talk => (talk.info.kind, talk.info.title))
  given Ordering[Kind] = Ordering.by:
    case Kind.Lightning => 5
    case Kind.Short     => 4
    case Kind.Workshop  => 3
    case Kind.Talk      => 2
    case Kind.Keynote   => 1

  enum Kind(val toStyle: String, val duration: Int):
    case Lightning extends Kind("presentation-lightning", 15)
    case Short     extends Kind("presentation-short", 25)
    case Talk      extends Kind("presentation-talk", 45)
    case Keynote   extends Kind("presentation-keynote", 60)
    case Workshop  extends Kind("workshop", 150)
  end Kind

  case class BasicInfo(
      title: String,
      slug: String,
      kind: Kind,
      category: String,
      confirmed: Boolean,
      dateTime: LocalDateTime | Null,
      room: Room | Null, // TODO: reuse String | Null
      slides: BasicInfo.Slides = None,
      replay: BasicInfo.Replay = None
  )
  object BasicInfo:
    def empty: BasicInfo = BasicInfo("Malformed talk info", "", Kind.Talk, "", false, null, null)

    opaque type Slides = Option[String]
    object Slides:
      extension (slides: Slides) inline def fold[A](ifEmpty: => A)(f: String => A): A = slides.fold(ifEmpty)(f)
      inline def apply(opt: Option[String]): Slides                                   = opt.filter(_.nonEmpty)

    opaque type Replay = Option[String]
    object Replay:
      extension (replay: Replay) inline def fold[A](ifEmpty: => A)(f: String => A): A = replay.fold(ifEmpty)(f)
      inline def apply(opt: Option[String]): Replay                                   = opt.filter(_.nonEmpty)

  case class Speaker(
      name: String,
      photoRelPath: String,
      job: String,
      socials: List[Social] = List.empty,
      bio: String = ""
  ):
    lazy val (jobTitle, company) =
      val parsed = job.splitAt(job.indexOf("@"))
      (parsed._1.trim, parsed._2.drop(1).trim)

    def renderBio: Array[String] = bio.split("\n")

  object Speaker:
    def empty: Speaker = Speaker("Malformed speaker", "", "")

case class Break(
    dateTime: LocalDateTime,
    kind: Break.Kind,
    overrideDuration: Option[Int] = None
) extends Act
    with Durable:
  def duration: Int   = overrideDuration.getOrElse(kind.duration)
  val day: DayOfWeek  = dateTime.nullMap(_.getDayOfWeek)
  val time: LocalTime = dateTime.nullMap(_.toLocalTime)
  def render: Div =
    div(className := s"blank-card break ${kind.style}", kind.icon, span(span(duration), span("min")), kind.icon)

object Break:
  enum Kind(val style: String, val duration: Int):
    case Large extends Kind("break-large", 15)
    case Lunch extends Kind("break-lunch", 60)

    def icon: ReactiveSvgElement[SVGSVGElement] = this match
      case Large => Icons.chat
      case Lunch => Icons.food

  object Kind:
    val max: Int = Kind.values.map(_.duration).max

case class Special(
    dateTime: LocalDateTime,
    kind: Special.Kind
) extends Act {
  val day: DayOfWeek  = dateTime.nullMap(_.getDayOfWeek)
  val time: LocalTime = dateTime.nullMap(_.toLocalTime)
  def render: ReactiveHtmlElement[HTMLDivElement] = kind match
    case Special.Kind.End => div(className := "blank-card end-day", Icons.logo("#222222"))
}
object Special:
  enum Kind:
    case End
