package io.scala.data

import upickle.default.*
import urldsl.errors.DummyError
import urldsl.vocabulary.FromString
import urldsl.vocabulary.Printer

import io.scala.Draftable
import io.scala.Routeable
import io.scala.data.parsers.Parsers
import io.scala.extensions.filterWhen
import io.scala.models.Session
import io.scala.models.Sponsor

// TODO: propagate usage of Event type instead of raw strings
enum Event(val sessions: List[Session], val sponsors: List[Sponsor]):
  case `nantes-2024`
      extends Event(
        SessionsData.nantes_2024.map(Parsers.ConferenceSession.fromText),
        Parsers.ConferenceSponsor.fromText(SponsorsData.nantes_2024)
      )
  case `paris-2024`
      extends Event(
        SessionsData.paris_2024.map(Parsers.ConferenceSession.fromText),
        Parsers.ConferenceSponsor.fromText(SponsorsData.paris_2024)
      )

object Event:
  val Current: Event = Event.`paris-2024`

  given ReadWriter[Event] = macroRW
  given FromString[Event, DummyError] = {
    case "paris-2024"  => Right(Event.`paris-2024`)
    case "nantes-2024" => Right(Event.`nantes-2024`)
    case _             => Left(DummyError.dummyError)
  }

  given Printer[Event] = {
    case Event.`paris-2024`  => "paris-2024"
    case Event.`nantes-2024` => "nantes-2024"
  }

object SessionsHistory:

  def sessionsForConf(args: Routeable & Draftable): List[Session] =
    args.conference
      .getOrElse(Event.Current)
      .sessions
      .filterWhen(args.withDraft.fold(true)(!_))(_.info.confirmed)

