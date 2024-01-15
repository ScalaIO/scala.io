package io.scala.utils

import com.raquo.airstream.state.Var
import org.scalajs.dom
import org.scalajs.dom.UIEvent
import org.scalajs.dom.console
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

  def fromWidth(width: Double): Screen = width match
    case w if w < 450  => Screen.Mobile
    case w if w < 768  => Screen.Tablet
    case w if w < 1024 => Screen.Laptop
    case _             => Screen.Desktop
