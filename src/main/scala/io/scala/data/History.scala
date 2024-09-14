package io.scala.data

import io.scala.data.parsers.Parsers
import io.scala.models.Session
import io.scala.models.Sponsor

// TODO: propagate usage of Event type instead of raw strings
enum Event(val sessions: List[Session], val sponsors: List[Sponsor]):
  case `nantes-2024` extends Event(
    SessionsData.nantes_2024.map(Parsers.ConferenceSession.fromText),
    Parsers.ConferenceSponsor.fromText(SponsorsData.nantes_2024),
  )
  case `paris-2024` extends Event(
    SessionsData.paris_2024.map(Parsers.ConferenceSession.fromText),
    Parsers.ConferenceSponsor.fromText(SponsorsData.paris_2024),
  )

object Event:
  val Current: Event = Event.`paris-2024`
  // makes me want to use enumeratum again...
  def withName(name: String): Option[Event] =
    Event.values.find(_.toString == name)
  def withNameOrCurrent(name: String): Event =
    withName(name).getOrElse(Current)
  def withNameOrCurrent(maybeName: Option[String]): Event =
    maybeName.flatMap(Event.withName).getOrElse(Current)

object SessionsHistory:

  def sessionsForConf(confName: Option[String]): List[Session] =
    Event.withNameOrCurrent(confName).sessions

  def sessionsForConf(confName: String): Option[List[Session]] =
    Event.withName(confName).map(_.sessions)

  inline def getConfName(confName: Option[String]): String =
    confName.getOrElse(Event.Current.toString)

object SponsorsHistory:

  def sponsorsForConf(confName: Option[String]): List[Sponsor] =
    confName.flatMap(Event.withName).getOrElse(Event.Current).sponsors
