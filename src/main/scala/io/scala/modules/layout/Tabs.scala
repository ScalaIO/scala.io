package io.scala.modules.layout

import com.raquo.laminar.api.L.*
import org.scalajs.dom.window

import io.scala.modules.elements.Line
import io.scala.modules.elements.LineKind
import io.scala.svgs.Icons

/* Provide a tabbed interface. The tab content must be calculated by the caller for better flexibility (e.g. allow usage of several display layouts)
 */
class Tabs[T](elements: Seq[(T, HtmlElement)]):
  val selection       = Var(elements.head._1)
  val dropdownClicked = Var(false)
  window.onclick = _ => dropdownClicked.set(false)

  def headersFull =
    div(
      className := "tabs-header inline",
      elements.map { e =>
        div(
          className := "tab",
          button(
            onClick --> { _ => selection.set(e._1) },
            h2(e._1.toString())
          ),
          Line(margin = 0.5, size = 3, kind = LineKind.Colored).amend {
            display <-- selection.signal.map { selected =>
              if selected == e._1 then "flex" else "none"
            }
          }
        )
      }
    )

  def headersMobile =
    div(
      className := "tabs-header dropdown",
      button(
        className := "dropdown-btn",
        className.toggle("svg-rotate") <-- dropdownClicked,
        onClick.stopImmediatePropagation.mapTo(!dropdownClicked.now()) --> dropdownClicked,
        child <-- selection.signal.map(_.toString()),
        Icons.down
      ),
      div(
        className := "dropdown-content",
        elements.map { e =>
          button(
            onClick --> { _ => selection.set(e._1) },
            e._1.toString()
          )
        },
        display <-- dropdownClicked.signal.map {
          case true  => "flex"
          case false => "none"
        }
      )
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
