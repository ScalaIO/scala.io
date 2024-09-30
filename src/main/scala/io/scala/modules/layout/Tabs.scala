package io.scala.modules.layout

import com.raquo.laminar.api.L.*
import org.scalajs.dom.window

import io.scala.modules.elements.Buttons
import io.scala.svgs.Icons
import io.scala.extensions.nullGetOrElse

/* Provide a tabbed interface. The tab content must be calculated by the caller for better flexibility (e.g. allow usage of several display layouts)
 */
class Tabs[T](elements: Seq[(T, HtmlElement)], default: T | Null = null):
  val selection       = Var(default.nullGetOrElse(elements.head._1))
  val dropdownClicked = Var(false)
  window.addEventListener(
    "click",
    _ => dropdownClicked.set(false)
  )

  def headersFull =
    div(
      className := "tabs-header inline",
      elements.map { e =>
        button(
          className := "tab",
          onClick.mapTo(e._1) --> selection,
          h2(e._1.toString()),
          className("underlined") <-- selection.signal.map(_ == e._1)
        )
      }
    )

  def headersMobile =
    Buttons.dropdown("tabs-header", dropdownClicked.signal)(
      className("svg-rotate") <-- dropdownClicked,
      onClick.stopImmediatePropagation.mapTo(!dropdownClicked.now()) --> dropdownClicked,
      child <-- selection.signal.map(_.toString()),
      Icons.down
    )(
      elements.map { e =>
        button(
          onClick.mapTo(e._1) --> selection,
          e._1.toString()
        )
      }
    )

  def render =
    div(
      className := "tabs-outer",
      headersFull,
      headersMobile,
      div(
        className := "tabs-content",
        elements.map { element =>
          child(
            div(className := "tab-content", element._2)
          ) <-- selection.signal.map(_ == element._1)
        }
      )
    )
