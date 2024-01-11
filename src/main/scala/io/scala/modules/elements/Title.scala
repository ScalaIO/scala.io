package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}

object Title {
  def apply(name: String) = h1(
    name,
    className := "title"
  )
}
