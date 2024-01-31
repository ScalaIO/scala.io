package io.scala

import io.scala.data.SpeakersInfo
import io.scala.data.TalksInfo
import io.scala.domaines.Speaker
import io.scala.domaines.TalkInfo
import io.scala.views.*
import io.scala.views.IndexView

import com.raquo.airstream.core.Signal
import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import org.scalajs.dom.document
import org.scalajs.dom.html
import upickle.default.*
import urldsl.errors.DummyError
import urldsl.language.PathSegmentWithQueryParams
import urldsl.vocabulary.{FromString, Printer, UrlMatching}
import urldsl.vocabulary.FromString
import urldsl.vocabulary.Printer
import urldsl.vocabulary.UrlMatching
import org.scalajs.dom.console

sealed trait Draftable:
  def withDraft: Option[Boolean]

sealed trait Slugify:
  def slug: String
sealed trait Page:
  def title: String

case class IndexPage(withDraft: Option[Boolean] = None) extends Page with Draftable:
  def title: String = "Home"
case class TalksPage(withDraft: Option[Boolean] = None) extends Page with Draftable:
  def title: String = "Talks"
case class TalkPage(slug: String) extends Page with Slugify:
  def title: String = s"Talk - $slug"
case object SponsorsPage extends Page:
  def title: String = "Sponsors"
case object VenuePage extends Page:
  def title: String = "Venue"
case class SchedulePage(withDraft: Option[Boolean] = None) extends Page with Draftable:
  def title: String = "Schedule"
case object FAQPage extends Page:
  def title: String = "FAQ"

object Page {

  given indexCodec: ReadWriter[IndexPage]            = macroRW
  given talksCodec: ReadWriter[TalksPage]            = macroRW
  given talkCodec: ReadWriter[TalkPage]              = macroRW
  given sponsorsCodec: ReadWriter[SponsorsPage.type] = macroRW
  given venueCodec: ReadWriter[VenuePage.type]       = macroRW
  given scheduleCodec: ReadWriter[SchedulePage]      = macroRW
  given faqCodec: ReadWriter[FAQPage.type]           = macroRW

  given pageArgBasicCodec: ReadWriter[Page] = macroRW

  val draftParam = param[Boolean]("withDraft").?

  given FromString[Page, DummyError] = {
    case "talks"    => Right(TalksPage())
    case "sponsors" => Right(SponsorsPage)
    case "venue"    => Right(VenuePage)
    case "schedule" => Right(SchedulePage())
    case "faq"      => Right(FAQPage)
    case _          => Left(DummyError.dummyError)
  }

  given Printer[Page] = {
    case _: TalksPage    => "talks"
    case t: TalkPage     => s"talks/${t.slug}"
    case SponsorsPage    => "sponsors"
    case VenuePage       => "venue"
    case _: SchedulePage => "schedule"
    case FAQPage         => "faq"
    case _: IndexPage    => ""
  }

  val indexRoute = Route.onlyQuery[IndexPage, Option[Boolean]](
    encode = x => x.withDraft,
    decode = args => IndexPage(args),
    (root / endOfSegments) ? draftParam
  )
  val talksRoute = Route.onlyQuery[TalksPage, Option[Boolean]](
    encode = x => x.withDraft,
    decode = args => TalksPage(args),
    (root / "talks" / endOfSegments) ? draftParam
  )
  val talkRoute = Route[TalkPage, String](
    encode = x => x.slug,
    decode = args => TalkPage(args),
    root / "talks" / segment[String] / endOfSegments
  )
  val sponsorsRoute = Route.static(
    SponsorsPage,
    root / "sponsors" / endOfSegments
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
  val faqRoute = Route.static(
    FAQPage,
    root / "faq" / endOfSegments
  )


  val router = new Router[Page](
    routes = List(indexRoute, talksRoute, talkRoute, sponsorsRoute, venueRoute, scheduleRoute, faqRoute),
    getPageTitle = page => page.title + " - ScalaIO",
    serializePage = page => write(page)(pageArgBasicCodec),
    deserializePage = pageStr => read(pageStr)(pageArgBasicCodec)
  )(
    popStateEvents = windowEvents(_.onPopState),
    owner = unsafeWindowOwner
  ) {
    override def relativeUrlForPage(page: Page): String = super.relativeUrlForPage(page).replaceAll("\\?$", "")
  }

  val splitter: SplitRender[Page, HtmlElement] =
    SplitRender(router.currentPageSignal)
      .collect[IndexPage](args => IndexView.render(args.withDraft.getOrElse(false)))
      .collect[TalksPage](args => TalkList.render(args.withDraft.getOrElse(false)))
      .collectSignal[TalkPage](args => TalkView.render(args))
      .collectStatic(SponsorsPage)(SponsorsList.render())
      .collectStatic(VenuePage)(VenueView.render())
      .collect[SchedulePage](arg => ScheduleView.render(arg.withDraft.getOrElse(false)))
      .collectStatic(FAQPage)(FAQView.render())

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
