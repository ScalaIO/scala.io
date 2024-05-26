package io.scala.data

import parsers.Parsers

object MeetupsInfo:
  def fromText(source: String) = Parsers.MeetupTalk.parseText(source)
