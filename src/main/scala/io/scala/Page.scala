package io.scala

import io.scala.data.SpeakersInfo
import io.scala.domaines.Speaker
import io.scala.views.*
import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import org.scalajs.dom.html
import upickle.default.*
import upickle.default.ReadWriter
import urldsl.errors.DummyError
import urldsl.language.PathSegmentWithQueryParams
import urldsl.vocabulary.{FromString, Printer, UrlMatching}


enum BasicPage(val view: GenericView):
  def toPageArg: PageArg = PageArg.Generic(this, false)

  def title: String = this match {
    case BasicPage.Index => "Home"
    case BasicPage.Speakers => "Speakers"
    case BasicPage.Sponsors => "Sponsors"
    case BasicPage.Venue => "Venue"
    case BasicPage.Schedule => "Schedule"
  }

  case Index extends BasicPage(IndexView)
  case Speakers extends BasicPage(SpeakersList)
  case Sponsors extends BasicPage(SponsorsList)
  case Venue extends BasicPage(VenueView)
  case Schedule extends BasicPage(ScheduleView)


sealed trait PageArg {

  final def title: String = this match {
    case PageArg.Generic(page, _) => page.title
    case PageArg.Speaker(speakerSlug, _) => "Speaker" + speakerSlug //incorrect
  }

  def withDraft: Boolean
}

object PageArg {
  case class Generic(page: BasicPage, withDraft: Boolean) extends PageArg

  case class Speaker(speakerSlug: String, withDraft: Boolean) extends PageArg
}


object Page {

  given pageArgBasicCodec: ReadWriter[BasicPage] = macroRW

  given pageArgGenericCodec: ReadWriter[PageArg.Generic] = macroRW

  given pageArgSpeakerCodec: ReadWriter[PageArg.Speaker] = macroRW

  given pageArgCodec: ReadWriter[PageArg] = macroRW


  val draftParam = param[Boolean]("withDraft").?


  val indexRoute: Route[PageArg, Unit] = Route.static(BasicPage.Index.toPageArg, root / endOfSegments)


  given FromString[BasicPage, DummyError] = {
    case "speakers" => Right(BasicPage.Speakers)
    case "sponsors" => Right(BasicPage.Sponsors)
    case "venue" => Right(BasicPage.Venue)
    case "schedule" => Right(BasicPage.Schedule)
    case _ => Left(DummyError.dummyError)
  }

  given Printer[BasicPage] = {
    case BasicPage.Speakers => "speakers"
    case BasicPage.Sponsors => "sponsors"
    case BasicPage.Venue => "venue"
    case BasicPage.Schedule => "schedule"
    case _ => ""
  }

  val pattern: PathSegmentWithQueryParams[BasicPage, DummyError, Option[Boolean], DummyError] = (root / segment[BasicPage] / endOfSegments) ? draftParam

  val basicPages: Route[PageArg.Generic, PatternArgs[BasicPage, Option[Boolean]]] = Route.withQuery[PageArg.Generic, BasicPage, Option[Boolean]](
    encode = page => UrlMatching(page.page, Some(page.withDraft).filter(identity)),
    decode = urlMatching => PageArg.Generic(urlMatching.path, urlMatching.params.getOrElse(false)),
    pattern
  )

  val speakerRoute: Route[PageArg.Speaker, String] = Route[PageArg.Speaker, String](
    encode = page => page.speakerSlug,
    decode = args => PageArg.Speaker(args, false),
    root / "speakers" / segment[String] / endOfSegments
  )

  val router: Router[PageArg] = new Router[PageArg](
    routes = List(indexRoute, speakerRoute , basicPages ),
    getPageTitle = page => page.title + " - ScalaIO",
    serializePage = page => write(page)(pageArgCodec),
    deserializePage = pageStr => read(pageStr)(pageArgCodec)
  )(
    popStateEvents = L.windowEvents(_.onPopState),
    owner = L.unsafeWindowOwner
  ) {
    override def relativeUrlForPage(page: PageArg): String = super.relativeUrlForPage(page).replaceAll("\\?$", "")
  }

  val splitter: SplitRender[PageArg, L.HtmlElement] = SplitRender[PageArg, HtmlElement](router.currentPageSignal).collectStaticPF({
    case PageArg.Generic(basicPage, withDraft) =>
      basicPage.view.render(withDraft)

    case PageArg.Speaker(speakerSlug, withDraft) =>
      SpeakersInfo.allSpeakers.find(_.slug == speakerSlug).map(new SpeakerView(_).render(withDraft)).getOrElse(L.div())
  })


  def navigateTo(page: PageArg): Binder[HtmlElement] = Binder { el =>
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
