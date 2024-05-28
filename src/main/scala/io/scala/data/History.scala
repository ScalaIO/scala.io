package io.scala.data

import io.scala.data.parsers.Parsers
import io.scala.domaines.Sponsor
import io.scala.domaines.Talk

import scala.collection.mutable.{HashMap => MutableMap}

object TalksHistory:
  val current = "paris-2024"
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
    cachedTalks(confName.getOrElse(current))

object SponsorsHistory:
  val current = "paris-2024"
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
