package io.scala.utils

enum Screen:
  case Mobile, Tablet, Computer

object Screen:
  def fromWidth(width: Double): Screen = width match
    case w if w < 450 => Screen.Mobile
    case w if w < 750 => Screen.Tablet
    case _            => Screen.Computer