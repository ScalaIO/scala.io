package io.scala.data

import scala.collection.mutable.HashMap as MutableMap

import io.scala.data.parsers.Parsers
import io.scala.models.Session
import io.scala.models.Sponsor

val current = "paris-2024"
object SessionsHistory:
  private val historyMap =
    Map(
      "nantes-2024" -> SessionsData.nantes_2024,
      "paris-2024"  -> SessionsData.paris_2024
    )

  private val cachedSessions = MutableMap
    .empty[String, List[Session]]
    .withDefault:
      historyMap
        .get(_)
        .getOrElse(historyMap(current))
        .map(Parsers.ConferenceSession.fromText)

  def sessionsForConf(confName: Option[String]): List[Session] =
    cachedSessions(getConfName(confName))

  def sessionsForConf(confName: String): Option[List[Session]] =
    cachedSessions.get(confName)

  inline def getConfName(confName: Option[String]): String =
    confName.getOrElse(current)

object SponsorsHistory:
  private val sponsorsMap =
    Map(
      "nantes-2024" -> SponsorsData.nantes_2024,
      "paris-2024"  -> SponsorsData.paris_2024
    )

  private val cachedSponsors = MutableMap
    .empty[String, List[Sponsor]]
    .withDefault { key =>
      Parsers.ConferenceSponsor.fromText {
        sponsorsMap
          .get(key)
          .getOrElse(sponsorsMap(current))
      }
    }

  def sponsorsForConf(confName: Option[String]): List[Sponsor] =
    cachedSponsors(confName.getOrElse(current))
