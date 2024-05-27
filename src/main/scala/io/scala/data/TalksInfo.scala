package io.scala.data

import io.scala.domaines.Talk
import io.scala.domaines.Talk.Speaker
import io.scala.data.parsers.Parsers

object TalksInfo:
  lazy val talksBySpeaker =
    allTalks
      .foldLeft(Map.empty[Speaker, Set[Talk]].withDefaultValue(Set.empty)): (acc, next) =>
        acc ++ next.speakers.map(_ -> (acc(next.speakers.head) + next))
      .mapValues(_.toList.sortBy(_.info.title))

  lazy val allTalks: List[Talk] = ConfFilesName.nantes2024.map(Parsers.ConferenceTalk.fromText(_))
