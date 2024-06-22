package io.scala.modules.elements

import com.raquo.laminar.api.L.*

object YurPlan:
  def apply() =
    div(
      className := "yurplan-container",
      iframe(
        title          := "Tickets ScalaIO 2024",
        src            := "https://yurplan.com/events/Scala-IO-2024/115152/tickets/widget?wversion=1&culture=fr",
        className      := "yurplan-widget",
        dataAttr("id") := "12404"
      )
    )
