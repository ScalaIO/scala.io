package io.scala.svgs

import com.raquo.laminar.api.L.SvgElement
import com.raquo.laminar.api.L.svg.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Cross:
  val render =
    svg(
      viewBox := "0 0 24 24",
      xmlns   := "http://www.w3.org/2000/svg",
      g(
        fill := "none",
        path(
          d           := "M6 6L18 18M6 18L18 6",
          stroke      := "currentColor",
          strokeWidth := "2"
        )
      )
    )
