package io.scala.modules

import io.scala.Data
import io.scala.svgs.Logo

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Header {
  def apply(): Div = div(
    Logo(),
    ul(
      Navlink(Data.Header.speakers),
      Navlink(Data.Header.sponsors),
      Navlink(Data.Header.venue),
      Navlink(Data.Header.schedule),
      className := "header__navbar"
    ),
    div(
      Data.Header.buyTicket,
      className := "header__call-to-action"
    ),
    className := "header"
  )

  private def Navlink(name: String): Li =
    li(
      name,
      className := "header__navbar__link"
    )
}
