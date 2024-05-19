package io.scala.data

import io.scala.data.SponsorsNantes2024

import knockoff.ChunkParser
import io.scala.domaines.Sponsor

object SponsorsInfo:
  val parser = new ChunkParser
  import parser.{*, given}
  val infoParser = (parser.textLine.filter(!_.content.startsWith("-"))).? ~ parser.bulletItem.*

  val allSponsors = SponsorsNantes2024.sponsors.map: sponsorString =>
    parser.parse(infoParser, sponsorString) match
      case parser.Success(result, _) =>
        val (colSpan, rowSpan) = result._1
          .map: dimensions =>
            dimensions.content.split(":") match
              case Array(col, row) => (col.trim.toInt, row.trim.toInt)
              case _               => (1, 1)
          .getOrElse((1, 1))

        result._2 match
          case List(name, link, logo, rank) =>
            Sponsor(name.content, link.content, logo.content, Sponsor.Rank.valueOf(rank.content), colSpan, rowSpan)
          case _ => ???
      case fail: parser.NoSuccess => ???
