package io.scala.modules.layout

import com.raquo.laminar.api.L.*

import io.scala.modules.elements.Line
import io.scala.modules.elements.LineKind

/* Provide a tabbed interface. The tab content must be calculated by the caller for better flexibility (e.g. allow usage of several display layouts)
 */
object Tabs:
  def apply[T](tabElements: Seq[(T, Seq[HtmlElement])], tabContentModifier: Modifier[Div]*) =
    val selection = Var(tabElements.head._1)
    div(
      className := "tabs-outer",
      div(
        className := "tabs-header",
        tabElements.map { element =>
          div(
            className := "tab",
            button(
              onClick --> { _ => selection.set(element._1) },
              h2(element._1.toString())
            ),
            Line(margin = 0.5, size = 3, kind = LineKind.Colored).amend {
              display <-- selection.signal.map { selected =>
                if selected == element._1 then "flex" else "none"
              }
            }
          )
        }
      ),
      div(
        className := "tabs-content",
        tabElements.map { element =>
          div(
            className := "tab-content",
            display <-- selection.signal.map { selected =>
              if selected == element._1 then "flex"
              else "none"
            },
            children <-- selection.signal.map {
              case selected if selected == element._1 =>
                element._2
              case _ => List(emptyNode)
            },
            tabContentModifier,
          )
        }
      )
    )
