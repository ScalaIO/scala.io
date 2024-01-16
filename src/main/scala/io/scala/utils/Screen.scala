package io.scala.utils

import io.scala.data.TalksInfo
import io.scala.views.blankDivs

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import org.scalajs.dom.UIEvent
import org.scalajs.dom.console
import org.scalajs.dom.document
import org.scalajs.dom.window

enum Screen:
  case Mobile, Tablet, Laptop, Desktop

object Screen:
  inline def width: Screen   = io.scala.utils.Screen.fromWidth(dom.window.innerWidth)
  val screenVar: Var[Screen] = Var(width)

  window.onresize = { _ =>
    val newScreen = width
    if newScreen != screenVar.now() then screenVar.set(newScreen)
  }

  window.onresize = { _ =>
    console.log("recomputing blank divs...")
    val width         = window.innerWidth
    val cardContainer = document.querySelector(".talks-list > .card-container")
    val card          = document.querySelector(".talks-list > .card-container > .talk-card")

    (cardContainer, card) match
      case (null, _) | (_, null) => ()
      case (container, el) =>
        val containerWidth = container.getBoundingClientRect().width
        val cardWidth      = el.getBoundingClientRect().width + 32
        val cardsByLine    = Math.floor(containerWidth / cardWidth).toInt
        val remainingCards = TalksInfo.allTalks.filter(_.speakers.forall(_.confirmed)).length % cardsByLine

        console.log(s"containerWidth: $containerWidth")
        console.log(s"cardWidth: $cardWidth")

        blankDivs.set(List.fill(cardsByLine - remainingCards)(div(className := "blank-div")))
  }

  def fromWidth(width: Double): Screen = width match
    case w if w < 450  => Screen.Mobile
    case w if w < 768  => Screen.Tablet
    case w if w < 1024 => Screen.Laptop
    case _             => Screen.Desktop
