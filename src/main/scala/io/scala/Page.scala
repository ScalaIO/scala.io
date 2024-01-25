package io.scala

import io.scala.data.SpeakersInfo
import io.scala.data.TalksInfo
import io.scala.domaines.Speaker
import io.scala.domaines.TalkInfo
import io.scala.views.*

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import org.scalajs.dom.html
import org.scalajs.dom.document
import upickle.default.*
import upickle.default.ReadWriter
import urldsl.errors.DummyError
import urldsl.language.PathSegmentWithQueryParams
import urldsl.vocabulary.{FromString, Printer, UrlMatching}
import urldsl.vocabulary.FromString
import urldsl.vocabulary.Printer
import urldsl.vocabulary.UrlMatching

enum BasicPage(val view: GenericView):
  def toPageArg: PageArg = PageArg.Generic(this, false)

  def title: String = this match {
    case BasicPage.Index    => "Home"
    case BasicPage.Talks    => "Talks"
    case BasicPage.Sponsors => "Sponsors"
    case BasicPage.Venue    => "Venue"
    case BasicPage.Schedule => "Schedule"
    case BasicPage.FAQ      => "FAQ"
  }

  case Index    extends BasicPage(IndexView)
  case Talks    extends BasicPage(TalkList)
  case Sponsors extends BasicPage(SponsorsList)
  case Venue    extends BasicPage(VenueView)
  case Schedule extends BasicPage(ScheduleView)
  case FAQ      extends BasicPage(FAQView)

sealed trait PageArg {

  final def title: String = this match {
    case PageArg.Generic(page, _)  => page.title
    case PageArg.Talk(talkSlug, _) => "Talk" + talkSlug // double incorrect
  }

  def withDraft: Boolean
}

object PageArg {
  case class Generic(page: BasicPage, withDraft: Boolean) extends PageArg

  case class Talk(talkSlug: String, withDraft: Boolean) extends PageArg
}

object Page {

  given pageArgBasicCodec: ReadWriter[BasicPage] = macroRW

  given pageArgGenericCodec: ReadWriter[PageArg.Generic] = macroRW

  given pageArgTalkCodec: ReadWriter[PageArg.Talk] = macroRW

  given pageArgCodec: ReadWriter[PageArg] = macroRW

  val draftParam = param[Boolean]("withDraft").?

  given FromString[BasicPage, DummyError] = {
    case "talks"    => Right(BasicPage.Talks)
    case "sponsors" => Right(BasicPage.Sponsors)
    case "venue"    => Right(BasicPage.Venue)
    case "schedule" => Right(BasicPage.Schedule)
    case "faq"      => Right(BasicPage.FAQ)
    case _          => Left(DummyError.dummyError)
  }

  given Printer[BasicPage] = {
    case BasicPage.Talks    => "talks"
    case BasicPage.Sponsors => "sponsors"
    case BasicPage.Venue    => "venue"
    case BasicPage.Schedule => "schedule"
    case BasicPage.FAQ      => "faq"
    case BasicPage.Index    => ""
  }

  val pattern: PathSegmentWithQueryParams[BasicPage, DummyError, Option[Boolean], DummyError] =
    (root / segment[BasicPage] / endOfSegments) ? draftParam

  val indexRoute: Route[PageArg, Unit] = Route.static(BasicPage.Index.toPageArg, root / endOfSegments)

  val basicPages: Route[PageArg.Generic, PatternArgs[BasicPage, Option[Boolean]]] =
    Route.withQuery[PageArg.Generic, BasicPage, Option[Boolean]](
      encode = page => UrlMatching(page.page, Some(page.withDraft).filter(identity)),
      decode = urlMatching => PageArg.Generic(urlMatching.path, urlMatching.params.getOrElse(false)),
      pattern
    )

  val talkRoute: Route[PageArg.Talk, String] = Route[PageArg.Talk, String](
    encode = page => page.talkSlug,
    decode = args => PageArg.Talk(args, false),
    root / "talks" / segment[String] / endOfSegments
  )

  val router: Router[PageArg] = new Router[PageArg](
    routes = List(indexRoute, talkRoute, basicPages),
    getPageTitle = page => page.title + " - ScalaIO",
    serializePage = page => write(page)(pageArgCodec),
    deserializePage = pageStr => read(pageStr)(pageArgCodec)
  )(
    popStateEvents = L.windowEvents(_.onPopState),
    owner = L.unsafeWindowOwner
  ) {
    override def relativeUrlForPage(page: PageArg): String = super.relativeUrlForPage(page).replaceAll("\\?$", "")
  }

  val splitter: SplitRender[PageArg, L.HtmlElement] =
    SplitRender[PageArg, HtmlElement](router.currentPageSignal).collectStaticPF({
      case PageArg.Generic(basicPage, withDraft) =>
        basicPage.view.render(withDraft)

      case PageArg.Talk(talkSlug, withDraft) =>
        TalksInfo.allTalks
          .find(_.slug == talkSlug)
          .map(new TalkView(_).render(withDraft))
          .getOrElse(L.div())
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
      --> { _ =>
        router.pushState(page)
        document.body.scrollTop = 0;            // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
      }).bind(el)
  }
}
