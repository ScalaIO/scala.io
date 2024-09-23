package io.scala.modules.layout

import com.raquo.laminar.api.L.*
import org.scalajs.dom.window

import io.scala.modules.elements.Buttons
import io.scala.svgs.Icons

/* Provide a tabbed interface. The tab content must be calculated by the caller for better flexibility (e.g. allow usage of several display layouts)
 */
class Tabs[T](elements: Seq[(T, HtmlElement)]):
  val selection       = Var(elements.head._1)
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
          onClick --> { _ => selection.set(e._1) },
          h2(e._1.toString()),
          className.toggle("underlined") <-- selection.signal.map(_ == e._1)
        )
      }
    )

  def headersMobile =
    Buttons.dropdown("tabs-header")(
      className.toggle("svg-rotate") <-- dropdownClicked,
      onClick.stopImmediatePropagation.mapTo(!dropdownClicked.now()) --> dropdownClicked,
      child <-- selection.signal.map(_.toString()),
      Icons.down
    )(
      elements.map { e =>
        button(
          onClick.mapTo(e._1) --> selection,
          e._1.toString()
        )
      },
      className.toggle("show-flex") <-- dropdownClicked
    )

  def render =
    div(
      className := "tabs-outer",
      headersFull,
      headersMobile,
      div(
        className := "tabs-content",
        elements.map { element =>
          div(
            className := "tab-content",
            display <-- selection.signal.map { selected =>
              if selected == element._1 then "flex"
              else "none"
            },
            child <-- selection.signal.map {
              case selected if selected == element._1 =>
                element._2
              case _ => emptyNode
            }
          )
        }
      )
    )
