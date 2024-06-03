package io.scala.modules.elements

import com.raquo.laminar.api.L._

sealed trait Slides:
  def url: String

case class WebHosted(url: String) extends Slides
case class SelfHosted(slug: String, year: Int) extends Slides:
  def url = s"/slides/$year/$slug"
