package io.scala

import io.scala.views.*

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import org.scalajs.dom.html
import upickle.default.*

sealed trait Page:
  def title =
    this match
      case IndexPage       => "Home"
      case _: SpeakersPage => "Speakers"
      case SponsorsPage    => "Sponsors"
      case VenuePage       => "Venue"
      case _: SchedulePage => "Schedule"
case object IndexPage                       extends Page
case class SpeakersPage(withDraft: Option[Boolean] = None) extends Page
case object SponsorsPage                    extends Page
case object VenuePage                       extends Page
case class SchedulePage(withDraft: Option[Boolean] = None) extends Page

object Page {
  given pageCodec: ReadWriter[Page] = macroRW
  given ReadWriter[SpeakersPage]    = macroRW
  given ReadWriter[SchedulePage]    = macroRW

  val draftParam = param[Boolean]("withDraft").?
  val indexRoute = Route.static(IndexPage, root / endOfSegments)
  val speakersRoute = Route.onlyQuery[SpeakersPage, Option[Boolean]](
    encode = page => page.withDraft,
    decode = args => SpeakersPage(withDraft = args),
    (root / "speakers" / endOfSegments) ? draftParam
  )
  val sponsorsRoute = Route.static(SponsorsPage, root / "sponsors" / endOfSegments)
  val venueRoute    = Route.static(VenuePage, root / "venue" / endOfSegments)
  val scheduleRoute = Route.onlyQuery[SchedulePage, Option[Boolean]](
    encode = page => page.withDraft,
    decode = args => SchedulePage(withDraft = args),
    (root / "schedule" / endOfSegments) ? draftParam
  )

  val router = new Router[Page](
    routes = List(indexRoute, speakersRoute, sponsorsRoute, venueRoute, scheduleRoute),
    getPageTitle = page => page.title + " - ScalaIO",
    serializePage = page => write(page)(pageCodec),
    deserializePage = pageStr => read(pageStr)(pageCodec)
  )(
    popStateEvents = L.windowEvents(_.onPopState),
    owner = L.unsafeWindowOwner
  )

  val splitter = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectStatic(IndexPage)(Index.render(Index.body))
    .collect[SpeakersPage](pge => SpeakersList.render(SpeakersList.body(pge.withDraft.getOrElse(false))))
    .collectStatic(SponsorsPage)(SponsorsList.render(SponsorsList.body))
    .collectStatic(VenuePage)(VenueView.render(VenueView.body))
    .collect[SchedulePage](pge => ScheduleView.render(ScheduleView.body(pge.withDraft.getOrElse(false))))

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
      --> (_ => router.pushState(page))).bind(el)
  }
}
