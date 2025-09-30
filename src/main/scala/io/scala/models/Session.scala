package io.scala.models

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import io.scala.data.Event
import io.scala.data.parsers.Parsers
import io.scala.modules.SessionCard
import io.scala.modules.elements.Paragraphs
import io.scala.svgs.Icons
import org.scalajs.dom.HTMLDivElement
import org.scalajs.dom.HTMLParagraphElement
import org.scalajs.dom.SVGSVGElement

sealed trait Act:
  def isKeynote: Boolean
  def isBreak: Boolean

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

  def duration: Int                          = info.kind.duration
  def render(room: Session.Room | Null): Div = SessionCard(this, Event.Current.toString, room)
  def isKeynote: Boolean                     = info.kind == Session.Kind.Keynote
  def isWorkshop: Boolean                    = info.kind == Session.Kind.Workshop
  def isBreak: Boolean                       = false

object Session:
  def empty: Session = Session(BasicInfo.empty, "To be announced", List.empty)

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
    case Short     extends Kind("presentation-short", 30)
    case Talk      extends Kind("presentation-talk", 45)
    case Keynote   extends Kind("presentation-keynote", 50)
    case Workshop  extends Kind("workshop", 150)

    def toPlural: String = s"${this}s"
  end Kind

  case class BasicInfo(
      title: String,
      slug: String,
      kind: Kind,
      category: String,
      confirmed: Boolean,
      `#`: Int | Null,
      slides: BasicInfo.Slides = None,
      replay: BasicInfo.Replay = None
  )
  object BasicInfo:
    def empty: BasicInfo =
      BasicInfo("To be announced", "", Kind.Talk, "", false, null)

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
    kind: Break.Kind,
    overrideDuration: Option[Int] = None
) extends Act
    with Durable:
  def duration: Int = overrideDuration.getOrElse(kind.duration)
  def render: Div =
    div(className := s"blank-card break ${kind.style}", kind.icon, span(span(duration), span("min")), kind.icon)
  def isKeynote: Boolean = false
  def isBreak: Boolean   = true

object Break:

  enum Kind(val style: String, val duration: Int):
    case Short extends Kind("break-large", 5)
    case Long  extends Kind("break-large", 15)
    case Lunch1 extends Kind("break-lunch", 75)
    case Lunch2 extends Kind("break-lunch", 90)

    def icon: ReactiveSvgElement[SVGSVGElement] = this match
      case Short => Icons.chat
      case Long  => Icons.chat
      case Lunch1 => Icons.food
      case Lunch2 => Icons.food

  object Kind:
    val max: Int = Kind.values.map(_.duration).max

case class Special(
    kind: Special.Kind
) extends Act {
  def render: ReactiveHtmlElement[HTMLDivElement] = kind match
    case Special.Kind.End => div(className := "blank-card with-border end-day", Icons.logo("#222222"))
  def isKeynote: Boolean = false
  def isBreak: Boolean   = true
}
object Special:
  enum Kind:
    case End
