package io.scala.modules

import io.scala.domaines.Break
import io.scala.domaines.Break.Kind
import io.scala.svgs.Coffee
import io.scala.svgs.Food
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}
import io.scala.svgs.Chat

object BreakCard:
  def apply(b: Break) = div(
    className := "blank-card",
    b match
      case Break(_, _, Break.Kind.Coffee) =>
        div(display.flex, justifyContent.spaceEvenly, Coffee(), Coffee())
      case Break(_, _, Break.Kind.Large) =>
        div(display.flex, justifyContent.spaceEvenly, Chat(), Chat())
      case Break(_, _, Break.Kind.Launch) =>
        div(display.flex, justifyContent.spaceEvenly, Food(), Food())
      case Break(_, _, Break.Kind.End)    => Logo("#222222")
  )
