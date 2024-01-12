package io.scala.modules.elements

import com.raquo.laminar.api.L.{*, given}

object YurPlan:
  def apply() =
    div(
      className := "yurplan-container",
      iframe(
        title          := "Tickets ScalaIO 2024",
        src            := "https://yurplan.com/events/Scala-IO-2024/115152/tickets/widget?wversion=1&culture=fr",
        className      := "yurplan-widget",
        dataAttr("id") := "12404"
      ),
      scriptTag(src := "https://assets.yurplan.com/yurplan-v1/dist/widget.js", `type` := "text/javascript")
    )
