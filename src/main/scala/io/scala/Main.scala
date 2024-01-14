package io.scala

import io.scala.Page

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import io.scala.domaines.Talk
import io.scala.domaines.Break

type Event = Talk | Break
val profilePlaceholder = "/images/profile.webp"

object Main {

  def main(args: Array[String]): Unit = {
    val app = div(child <-- Page.splitter.signal)

    renderOnDomContentLoaded(dom.document.querySelector("#app"), app)
  }
}
