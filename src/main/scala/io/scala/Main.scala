package io.scala


import com.raquo.laminar.api.L._
import org.scalajs.dom

val profilePlaceholder = "/images/profile.webp"

object Main {

  def main(args: Array[String]): Unit = {
    val app = div(child <-- Page.splitter.signal)

    renderOnDomContentLoaded(dom.document.querySelector("#app"), app)
  }
}
