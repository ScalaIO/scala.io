package io.scala.modules

import com.raquo.laminar.api.L.{*, given}

object YurPlan:
  def apply() =
    div(
      className := "yurplan-container",
      iframe(
        src := "https://yurplan.com/orga/Scala-IO/12404/ticketing/widget?wversion=1",
        className := "yurplan-widget",
        dataAttr("id") := "12404"
      ),
      a(
        href := "https://yurplan.com/billetterie-en-ligne?utm_source=referral&utm_medium=widget&utm_campaign=lien_billetterie_widget",
        target := "_blank",
        className := "yurplan-widget-link-12404",
        "Logiciel de gestion d'événements"
      )
      // script(
      //   src := "https://assets.yurplan.com/yurplan-v1/dist/widget.js",
      //   `type` := "text/javascript")
    )