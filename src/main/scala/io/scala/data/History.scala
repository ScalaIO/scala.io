package io.scala.data

import scala.collection.MapView

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
  case `paris-2025`
      extends Event(
        SessionsData.paris_2025.map(Parsers.ConferenceSession.fromText),
        Parsers.ConferenceSponsor.fromText(SponsorsData.paris_2025)
      )

object Event:
  val Current: Event = Event.`paris-2025`
  // makes me want to use enumeratum again...
  def withName(name: String): Option[Event] =
    Event.values.find(_.toString == name)
  def withNameOrCurrent(name: String): Event =
    withName(name).getOrElse(Current)
  def withNameOrCurrent(maybeName: Option[String]): Event =
    maybeName.flatMap(Event.withName).getOrElse(Current)

object SessionsHistory:

  def sessionsForConf(args: Routeable & Draftable): List[Session] =
    Event
      .withNameOrCurrent(args.conference)
      .sessions
      .filterWhen(args.withDraft.fold(true)(!_))(_.info.confirmed)

  def sessionsForSchedule: List[Session] =
    Event.Current.sessions

  def sessionsForConf(confName: String): Option[List[Session]] =
    Event.withName(confName).map(_.sessions)

  inline def getConfName(confName: Option[String]): String =
    confName.getOrElse(Event.Current.toString)

object SponsorsHistory:

  private lazy val allSponsors: MapView[Sponsor.Rank, List[Sponsor]] =
    Event.values
      .flatMap(_.sponsors.filter(_.rank != Sponsor.Rank.Partner))
      .groupBy(_.name)
      .mapValues(_.minBy(_.rank))
      .values
      .groupBy(_.rank)
      .mapValues(_.toList.sortBy(_.name))

  val allRanks: List[Sponsor.Rank] = allSponsors.keys.toList.sorted

  def values(rank: Sponsor.Rank): List[Sponsor] = allSponsors(rank)

  def sponsorsForConf(confName: Option[String]): List[Sponsor] =
    confName.flatMap(Event.withName).getOrElse(Event.Current).sponsors
