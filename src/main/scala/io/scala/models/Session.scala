package io.scala.models

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import org.scalajs.dom
import org.scalajs.dom.HTMLDivElement
import org.scalajs.dom.HTMLParagraphElement
import org.scalajs.dom.SVGSVGElement

import io.scala.data.Event
import io.scala.data.parsers.Parsers
import io.scala.extensions.*
import io.scala.modules.SessionCard
import io.scala.modules.elements.Paragraphs
import io.scala.svgs.Icons

sealed trait Act:
  def dateTime: LocalDateTime | Null
  def render: Div
  def isKeynote: Boolean
  def isBreak: Boolean

  lazy val startingTime = dateTime.nullMap(_.toLocalTime)
  lazy val startingDay  = dateTime.nullMap(_.getDayOfWeek)
object Act:
  given Ordering[Act] = Ordering.by(_.startingTime)

sealed trait Durable:
  def duration: Int

case class Session(
    info: Session.BasicInfo,
    description: String,
    speakers: List[Session.Speaker],
    cancelledReason: Option[String] = None,
    htmlId: Option[String] = None
) extends Act
    with Durable:
  lazy val renderDescription: List[ReactiveHtmlElement[HTMLParagraphElement]] =
    Parsers.Description.parseTalk(description).map(Paragraphs.description(_))

  export info.dateTime
  def duration: Int       = info.kind.duration
  def render: Div         = SessionCard(this, Event.Current.toString)
  def isKeynote: Boolean  = info.kind == Session.Kind.Keynote
  def isWorkshop: Boolean = info.kind == Session.Kind.Workshop
  def isBreak: Boolean    = false

object Session:
  def empty: Session = Session(BasicInfo.empty, "To be announced", List.empty)
  object IsScheduled:
    def unapply(s: Session): Boolean =
      if s.info.room.isDefined && s.dateTime.isDefined then true
      else false

  opaque type Room = String
  extension (room: Room) def show: String = s"Room $room"
  object Room:
    def apply(room: String): Room = room
    given Ordering[Room]          = Ordering.String.on(_.show)

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

    def toPlural: String = s"${this.toString()}s"
  end Kind

  case class BasicInfo(
      title: String,
      slug: String,
      kind: Kind,
      category: String,
      confirmed: Boolean,
      dateTime: LocalDateTime | Null,
      room: Room | Null,
      `#`: Int | Null,
      slides: BasicInfo.Slides = None,
      replay: BasicInfo.Replay = None
  )
  object BasicInfo:
    def empty: BasicInfo = BasicInfo("Malformed talk info", "", Kind.Talk, "", false, LocalDateTime.MIN, Room("none"), null)

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
      job.split("@") match
        case Array(j)    => (j.trim, "")
        case Array(j, c) => (j.trim, c.trim)

    def renderBio: Array[String] = bio.split("\n")

  object Speaker:
    def empty: Speaker      = Speaker("Malformed speaker", "", "")
    given Ordering[Speaker] = Ordering.by(_.name)

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
  def isKeynote: Boolean = false
  def isBreak: Boolean   = true

object Break:
  def from(kind: Break.Kind, day: Int, hour: Int, minute: Int): Break =
    val dateTime = LocalDateTime.of(2024, 11, 7 + day, hour, minute)
    Break(dateTime, kind)

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
    case Special.Kind.End => div(className := "blank-card with-border end-day", Icons.logo("#222222"))
  def isKeynote: Boolean = false
  def isBreak: Boolean   = true
}
object Special:
  def from(kind: Special.Kind, day: Int, hour: Int, minute: Int): Special =
    val dateTime = LocalDateTime.of(2024, 11, 7 + day, hour, minute)
    Special(dateTime, kind)

  enum Kind:
    case End
