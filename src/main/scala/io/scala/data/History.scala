package io.scala.data

import scala.collection.mutable.HashMap as MutableMap

import io.scala.data.parsers.Parsers
import io.scala.domaines.Sponsor
import io.scala.domaines.Talk

val current = "paris-2024"
object TalksHistory:
  private val historyMap =
    Map(
      "nantes-2024" -> ConfFilesName.nantes2024,
      "paris-2024"  -> ConfFilesName.paris2024
    )

  private val cachedTalks = MutableMap
    .empty[String, List[Talk]]
    .withDefault:
      historyMap
        .get(_)
        .getOrElse(historyMap(current))
        .map(Parsers.ConferenceTalk.fromText)

  def talksForConf(confName: Option[String]): List[Talk] =
    cachedTalks(getConfName(confName))

  inline def getConfName(confName: Option[String]): String =
    confName.getOrElse(current)

object SponsorsHistory:
  private val sponsorsMap =
    Map(
      "nantes-2024" -> SponsorsMd.nantes2024,
      "paris-2024"  -> SponsorsMd.paris2024
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
