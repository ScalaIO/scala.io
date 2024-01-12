package io.scala.svgs

import com.raquo.laminar.api.L.SvgElement
import com.raquo.laminar.api.L.svg.*

object Suitcase {
  def apply(): SvgElement = svg(
    viewBox := "0 0 240 192",
    g(
      path(d := "m  40,32 0,160 -16,0 a  24,24 0 0 1 -24,-24 l 0,-112 a  24,24 0 0 1  24,-24 z"),
      rect(
        x      := "48",
        y      := "32",
        width  := "144",
        height := "160"
      ),
      path(d := "m 200,32 16,0 a  24,24 0 0 1  24,24  l 0,112 a  24,24 0 0 1 -24,24  l -16,0 z"),
      path(
        d := "m  84,30 0,-12 a  18,18 0 0 1 18,-18 l 36,0 a  18,18 0 0 1 18,18  l 0,12 -12,0 0,-12 a   6,6  0 0 0 -6,-6  l -36,0 a   6,6  0 0 0 -6,6   l 0,12 z"
      )
    )
  )
}
