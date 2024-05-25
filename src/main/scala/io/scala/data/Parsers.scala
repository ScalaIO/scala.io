package io.scala.data

import knockoff.ChunkParser
import knockoff.Chunk
import knockoff.HeaderChunk

object Parsers:
  val parser    = new ChunkParser
  def emptyLine = parser.emptyLine
  def header    = parser.atxHeader <~ emptyLine.?
  def headerN(n: Int) = parser.atxHeader.filter{ case HeaderChunk(n, _) => true; case _ => false } <~ emptyLine.?
  def list      = parser.bulletItem.* <~ emptyLine.?
  def textBlock = parser.textBlock <~ emptyLine.?
  def linkRegex = """\[([^\[\]]+)\]\s*\(([^\(\)]+)\)""".r

  def listToMap(list: List[Chunk], sep: String): Map[String, String] =
    list
      .map: chunk =>
        val sepIdx       = chunk.content.indexOf(sep)
        val (key, value) = chunk.content.splitAt(sepIdx)
        key.trim -> value.drop(1).trim
      .toMap
