package io.scala.views

import io.scala.data.EventFilesName
import io.scala.domaines.Meetup
import io.scala.modules.elements.Titles
import io.scala.utils.SttpClient.backend

import com.raquo.laminar.api.L._
import org.scalajs.dom.console
import scala.collection.immutable.TreeSet
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import sttp.client3._

object EventsView extends SimpleView {

  val events: Var[TreeSet[Meetup]] = Var(TreeSet.empty[Meetup])

  val request =
    EventFilesName.names
      .map: name =>
        basicRequest
          .get(uri"/public/scalafr-meetups/$name")
          .response(asStringAlways)
          .send(backend)
      .foreach(response =>
        response.onComplete {
          case Success(value) => events.update(_ + Meetup(value.body))
          case _              => console.error("Error while fetching events")
        }
      )

  override def body(): HtmlElement = {
    sectionTag(
      className := "container events",
      Titles("Other events"),
      children <-- events.signal.map(ev =>
        ev.zipWithIndex.map { (e, idx) =>
          if idx % 2 == 0 then e.render.amend(className := "normal")
          else e.render.amend(className := "reverse")
        }.toSeq
      )
    )
  }
}
