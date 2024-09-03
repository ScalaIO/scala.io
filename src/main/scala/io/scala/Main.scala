package io.scala

import com.raquo.laminar.api.L.*
import org.scalajs.dom

import io.scala.modules.layout.Footer
import io.scala.modules.layout.Header

val profilePlaceholder = "/images/profile.webp"

object Main {

  def main(args: Array[String]): Unit = {
    val app = div(
      child <-- Header.render,
      child <-- Page.splitter.signal,
      Footer.render
    )

    renderOnDomContentLoaded(dom.document.querySelector("#app"), app)
  }
}
