package io.scala

import com.raquo.laminar.api.L.*
import io.scala.modules.layout.Footer
import io.scala.modules.layout.Header
import org.scalajs.dom

val profilePlaceholder = "/images/profile.webp"

object Main {

  def main(args: Array[String]): Unit = {
    val app = div(
      Header.render,
      child <-- Page.splitter.signal,
      Footer.render
    )

    renderOnDomContentLoaded(dom.document.querySelector("#app"), app)
  }
}
