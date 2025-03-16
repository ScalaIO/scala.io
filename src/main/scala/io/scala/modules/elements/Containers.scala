package io.scala.modules.elements

import com.raquo.laminar.api.L.*

object Containers:
  def flex(content: Modifier[HtmlElement]*)      = div(className := "container-flex", content)
  def flexCards(content: Modifier[HtmlElement]*) = div(className := "card-container-flex", content)
  def gridCards(content: Modifier[HtmlElement])  = div(className := "card-container", content)
