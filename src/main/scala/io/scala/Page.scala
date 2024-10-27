package io.scala

import app.faq.*
import app.schedule.ScheduleView
import app.sessions.SessionList
import app.sessions.SessionView
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
case class SessionsPage(withDraft: Option[Boolean] = None, conference: Option[String] = None)
    extends Page
    with Draftable
    with Routeable:
  def title: String = "Sessions"
case class SessionPage(conference: String, slug: String) extends Page with Slugify:
  def title: String = s"Session - $slug"
case class SponsorsPage(conference: Option[String] = None) extends Page with Routeable:
  def title: String = "Sponsors"
case object VenuePage extends Page:
  def title: String = "Venue"
case class SchedulePage(withDraft: Option[Boolean] = None) extends Page with Draftable:
  def title: String = "Schedule"
case object EventsPage extends Page:
  def title: String = "Other events"
case object FAQPage extends Page:
  def title: String = "FAQ"
case object CoCPage extends Page:
  def title: String = "Code of Conduct"

object Page {

  given ReadWriter[IndexPage]       = macroRW
  given ReadWriter[SessionsPage]    = macroRW
  given ReadWriter[SessionPage]     = macroRW
  given ReadWriter[SponsorsPage]    = macroRW
  given ReadWriter[VenuePage.type]  = macroRW
  given ReadWriter[SchedulePage]    = macroRW
  given ReadWriter[EventsPage.type] = macroRW
  given ReadWriter[FAQPage.type]    = macroRW

  given pageArgBasicCodec: ReadWriter[Page] = macroRW

  val draftParam      = param[Boolean]("withDraft").?
  val conferenceParam = param[String]("conference").?

  given FromString[Page, DummyError] = {
    case "sessions" => Right(SessionsPage())
    case "sponsors" => Right(SponsorsPage())
    case "venue"    => Right(VenuePage)
    case "schedule" => Right(SchedulePage())
    case "events"   => Right(EventsPage)
    case "faq"      => Right(FAQPage)
    case "coc"      => Right(CoCPage)
    case _          => Left(DummyError.dummyError)
  }

  given Printer[Page] = {
    case _: SessionsPage => "sessions"
    case s: SessionPage  => s"sessions/${s.slug}"
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
  val sessionsRoute = Route.onlyQuery[SessionsPage, (Option[Boolean], Option[String])](
    encode = x => (x.withDraft, x.conference),
    decode = SessionsPage(_, _),
    (root / "sessions" / endOfSegments) ? draftParam & conferenceParam
  )
  // maintain old /talks?conference=<conference> links
  private val legacyTalksRoute = Route.onlyQuery(
    encode = (x: SessionsPage) => (x.withDraft, x.conference),
    decode = SessionsPage(_, _),
    (root / "talks" / endOfSegments) ? draftParam & conferenceParam
  )
  val sessionRoute = Route[SessionPage, (String, String)](
    encode = x => (x.conference, x.slug),
    decode = SessionPage(_, _),
    (root / "sessions" / segment[String] / segment[String] / endOfSegments)
  )
  // maintain old /talks/<conference>/<slug> links
  private val legacyTalkRoute = Route(
    encode = (x: SessionPage) => (x.conference, x.slug),
    decode = SessionPage(_, _),
    root / "talks" / segment[String] / segment[String] / endOfSegments
  )
  // maintain links of Nantes 2024 edition: scala.io/talks/<slug>
  private val legacyNantesTalkRoute = Route[SessionPage, String](
    encode = x => x.slug,
    decode = SessionPage("nantes-2024", _),
    root / "talks" / segment[String] / endOfSegments
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
      sessionsRoute,
      legacyTalksRoute,
      sessionRoute,
      legacyTalkRoute,
      legacyNantesTalkRoute,
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

  val splitter: SplitRender[Page, HtmlElement] =
    SplitRender(router.currentPageSignal)
      .collectSignal[IndexPage](IndexView.render)
      .collectSignal[SessionsPage](SessionList.render)
      .collectSignal[SessionPage](SessionView.render)
      .collectSignal[SponsorsPage](SponsorsList.render)
      .collectStatic(VenuePage)(VenueView.render())
      .collectSignal[SchedulePage](ScheduleView.render)
      .collectStatic(EventsPage)(EventsView.render())
      .collectStatic(FAQPage)(FAQView.render())
      .collectStatic(CoCPage)(CoCView.render())

  def navigateTo(page: => Page, selector: Option[String] = None): Binder[HtmlElement] = Binder { el =>
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
        selector match
          case None =>
            document.body.scrollTop = 0;            // For Safari
            document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
          case Some(value) =>
            document.getElementById(value).scrollIntoView()
      }).bind(el)
  }
}
