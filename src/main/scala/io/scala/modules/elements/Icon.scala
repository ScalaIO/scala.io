package io.scala.modules.elements

import com.raquo.laminar.api.L.*
import com.raquo.laminar.api.L.svg.*

object Icon:
  def apply(name: String) =
    img(src := s"/images/icons/$name.svg")
