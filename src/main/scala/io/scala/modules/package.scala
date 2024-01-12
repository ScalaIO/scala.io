package io.scala

import org.scalajs.dom.Event
import org.scalajs.dom.html.{Anchor, Button}
import org.scalajs.dom.svg.{Path, SVG}
import org.scalajs.dom.console

package object modules {
  val profilePlaceholder = "/images/profile.webp"

  def clickFilter: Event => Boolean = e =>
    console.log(e.target)
    e.target match
      case _: Anchor | _: SVG | _: Path | _: Button => false
      case _                            => true
}
