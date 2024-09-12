package io.scala.data

import io.scala.data.parsers.Parsers
import io.scala.models.Sponsor
import io.scala.models.Session

import scala.collection.mutable.HashMap as MutableMap

val current = "paris-2024"
object TalksHistory:
  private val historyMap =
    Map(
      "nantes-2024" -> TalksData.nantes_2024,
      "paris-2024"  -> TalksData.paris_2024
    )

  private val cachedTalks = MutableMap
    .empty[String, List[Session]]
    .withDefault:
      historyMap
        .get(_)
        .getOrElse(historyMap(current))
        .map(Parsers.ConferenceTalk.fromText)

  def talksForConf(confName: Option[String]): List[Session] =
    cachedTalks(getConfName(confName))

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
