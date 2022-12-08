package io.scala

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import io.scala.Page

object Main {

  def main(args: Array[String]): Unit = {
    val app = div(child <-- Page.splitter.$view)

    renderOnDomContentLoaded(dom.document.querySelector("#app"), app)
  }
}