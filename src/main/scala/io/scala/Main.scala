package io.scala

import io.scala.Page

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

object Main {

  def main(args: Array[String]): Unit = {
    val app = div(child <-- Page.splitter.signal)

    renderOnDomContentLoaded(dom.document.querySelector("#app"), app)
  }
}
