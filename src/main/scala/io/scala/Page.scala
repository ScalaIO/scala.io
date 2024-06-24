package io.scala

import app.faq.*
import app.schedule.ScheduleView
import app.talks.TalkList
import app.talks.TalkView
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*
import org.scalajs.dom.document
import org.scalajs.dom.html
import upickle.default.*
import urldsl.errors.DummyError
import urldsl.vocabulary.FromString
import urldsl.vocabulary.Printer

import io.scala.views.*
import io.scala.views.IndexView

sealed trait Draftable:
  def withDraft: Option[Boolean]
sealed trait Routeable:
  def conference: Option[String]
object Routeable:
  def fallback: String = "paris-2024"

sealed trait Slugify:
  def slug: String
sealed trait Page:
  def title: String

case class IndexPage(withDraft: Option[Boolean] = None, conference: Option[String] = None)
    extends Page
    with Draftable
    with Routeable:
  def title: String = "Home"
case class TalksPage(withDraft: Option[Boolean] = None, conference: Option[String] = None)
    extends Page
    with Draftable
    with Routeable:
  def title: String = "Talks"
case class TalkPage(conference: String, slug: String) extends Page with Slugify:
  def title: String = s"Talk - $slug"
case class SponsorsPage(conference: Option[String] = None) extends Page with Routeable:
  def title: String = "Sponsors"
case object VenuePage extends Page:
  def title: String = "Venue"
case class SchedulePage(withDraft: Option[Boolean] = None, conference: Option[String] = None)
    extends Page
    with Draftable
    with Routeable:
  def title: String = "Schedule"
case object EventsPage extends Page:
  def title: String = "Other events"
case object FAQPage extends Page:
  def title: String = "FAQ"
case object CoCPage extends Page:
  def title: String = "Code of Conduct"

object Page {

  given indexCodec: ReadWriter[IndexPage]        = macroRW
  given talksCodec: ReadWriter[TalksPage]        = macroRW
  given talkCodec: ReadWriter[TalkPage]          = macroRW
  given sponsorsCodec: ReadWriter[SponsorsPage]  = macroRW
  given venueCodec: ReadWriter[VenuePage.type]   = macroRW
  given scheduleCodec: ReadWriter[SchedulePage]  = macroRW
  given eventsCodec: ReadWriter[EventsPage.type] = macroRW
  given faqCodec: ReadWriter[FAQPage.type]       = macroRW

  given pageArgBasicCodec: ReadWriter[Page] = macroRW

  val draftParam      = param[Boolean]("withDraft").?
  val conferenceParam = param[String]("conference").?

  given FromString[Page, DummyError] = {
    case "talks"    => Right(TalksPage())
    case "sponsors" => Right(SponsorsPage())
    case "venue"    => Right(VenuePage)
    case "schedule" => Right(SchedulePage())
    case "events"   => Right(EventsPage)
    case "faq"      => Right(FAQPage)
    case "coc"      => Right(CoCPage)
    case _          => Left(DummyError.dummyError)
  }

  given Printer[Page] = {
    case _: TalksPage    => "talks"
    case t: TalkPage     => s"talks/${t.slug}"
    case _: SponsorsPage => "sponsors"
    case VenuePage       => "venue"
    case _: SchedulePage => "schedule"
    case FAQPage         => "faq"
    case EventsPage      => "events"
    case CoCPage         => "coc"
    case _: IndexPage    => ""
  }

  val indexRoute = Route.onlyQuery[IndexPage, (Option[Boolean], Option[String])](
    encode = x => (x.withDraft, x.conference),
    decode = IndexPage(_, _),
    (root / endOfSegments) ? draftParam & conferenceParam
  )
  val talksRoute = Route.onlyQuery[TalksPage, (Option[Boolean], Option[String])](
    encode = x => (x.withDraft, x.conference),
    decode = TalksPage(_, _),
    (root / "talks" / endOfSegments) ? draftParam & conferenceParam
  )
  val talkRoute = Route[TalkPage, (String, String)](
    encode = x => (x.conference, x.slug),
    decode = TalkPage(_, _),
    (root / "talks" / segment[String] / segment[String] / endOfSegments)
  )
  val legacyTalkRoute = Route[TalkPage, String](
    encode = x => x.slug,
    decode = TalkPage("nantes-2024", _),
    (root / "talks" / segment[String] / endOfSegments)
  )
  val sponsorsRoute = Route.onlyQuery[SponsorsPage, Option[String]](
    encode = x => x.conference,
    decode = args => SponsorsPage(args),
    (root / "sponsors" / endOfSegments) ? param[String]("conference").?
  )
  val venueRoute = Route.static(
    VenuePage,
    root / "venue" / endOfSegments
  )
  val scheduleRoute = Route.onlyQuery[SchedulePage, Option[Boolean]](
    encode = x => x.withDraft,
    decode = args => SchedulePage(args),
    (root / "schedule" / endOfSegments) ? draftParam
  )
  val eventsRoute = Route.static(
    EventsPage,
    root / "events" / endOfSegments
  )
  val faqRoute = Route.static(
    FAQPage,
    root / "faq" / endOfSegments
  )
  val cocRoute = Route.static(
    CoCPage,
    root / "code-of-conduct" / endOfSegments
  )

  val router = new Router[Page](
    routes = List(
      indexRoute,
      talksRoute,
      talkRoute,
      legacyTalkRoute,
      sponsorsRoute,
      venueRoute,
      scheduleRoute,
      eventsRoute,
      faqRoute,
      cocRoute
    ),
    getPageTitle = page => page.title + " - ScalaIO",
    serializePage = page => write(page)(using pageArgBasicCodec),
    deserializePage = pageStr => read(pageStr)(using pageArgBasicCodec)
  )(
    popStateEvents = windowEvents(_.onPopState),
    owner = unsafeWindowOwner
  ) {
    override def relativeUrlForPage(page: Page): String = super.relativeUrlForPage(page).replaceAll("\\?$", "")
  }

  def current = router.currentPageSignal.now()

  // TODO: use collectSignal as much as possible to avoid recreating the whole components
  val splitter: SplitRender[Page, HtmlElement] =
    SplitRender(router.currentPageSignal)
      .collectSignal[IndexPage](args => IndexView.render(args))
      .collectSignal[TalksPage](args => TalkList.render(args))
      .collectSignal[TalkPage](args => TalkView.render(args))
      .collectSignal[SponsorsPage](args => SponsorsList.render(args))
      .collectStatic(VenuePage)(VenueView.render())
      .collect[SchedulePage](arg => ScheduleView.render(arg.withDraft.getOrElse(false), arg.conference))
      .collectStatic(EventsPage)(EventsView.render())
      .collectStatic(FAQPage)(FAQView.render())
      .collectStatic(CoCPage)(CoCView.render())

  def navigateTo(page: Page): Binder[HtmlElement] = Binder { el =>
    val isLinkElement = el.ref.isInstanceOf[html.Anchor]
    if (isLinkElement) {
      el.amend(href(router.absoluteUrlForPage(page)))
    }

    // If element is a link and user is holding a modifier while clicking:
    //  - Do nothing, browser will open the URL in new tab / window / etc. depending on the modifier key
    // Otherwise:
    //  - Perform regular pushState transition
    (onClick
      .filter(ev => !(isLinkElement && (ev.ctrlKey || ev.metaKey || ev.shiftKey || ev.altKey)))
      .preventDefault
      --> { _ =>
        router.pushState(page)
        document.body.scrollTop = 0;            // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
      }).bind(el)
  }
}
