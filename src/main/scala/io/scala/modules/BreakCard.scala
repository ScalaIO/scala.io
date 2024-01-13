package io.scala.modules

import io.scala.domaines.Break
import io.scala.domaines.Break.Kind
import io.scala.svgs.Chat
import io.scala.svgs.Coffee
import io.scala.svgs.Food
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}

object BreakCard:
  val blankCardClass = "blank-card"
  def apply(b: Break) =
    b.kind match
      case Break.Kind.Coffee =>
        div(className := blankCardClass, Coffee(), Coffee())
      case Break.Kind.Large =>
        div(className := blankCardClass, Chat(), Chat())
      case Break.Kind.Launch =>
        div(className := blankCardClass, Food(), Food())
      case Break.Kind.End            => div(className := blankCardClass, Logo("#222222"))
      case Break.Kind.CommunityParty => div(className := s"$blankCardClass community-party")
