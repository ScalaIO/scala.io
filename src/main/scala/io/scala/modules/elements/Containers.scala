package io.scala.modules.elements

import com.raquo.laminar.api.L._

object Containers:
  def flexCards(content: Modifier[HtmlElement]) = div(className := "card-container-flex", content)
  def gridCards(content: Modifier[HtmlElement]) = div(className := "card-container", content)
