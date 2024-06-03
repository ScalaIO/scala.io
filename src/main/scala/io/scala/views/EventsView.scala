package io.scala.views

import com.raquo.laminar.api.L._
import io.scala.data.EventFilesName
import io.scala.domaines.Meetup
import io.scala.modules.elements.Containers
import io.scala.modules.elements.Titles
import io.scala.utils.SttpClient.backend
import org.scalajs.dom.console
import sttp.client3._

import scala.collection.immutable.TreeSet
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
object EventsView extends SimpleView {

  val events: Var[TreeSet[Meetup]] = Var(TreeSet.empty[Meetup])

  val request =
    EventFilesName.names
      .foreach: name =>
        basicRequest
          .get(uri"/public/scalafr-meetups/$name")
          .response(asStringAlways)
          .send(backend)
          .onComplete:
            case Success(value) =>
              events.update(_ + Meetup(value.body))
            case _ => console.error("Error while fetching events")

  override def body(): HtmlElement = {
    sectionTag(
      className := "container events",
      Titles("Other events"),
      Containers.flexCards(
        children <-- events.signal.map(ev =>
          ev.toList.zipWithIndex.map { (e, idx) =>
            if idx % 2 == 0 then e.render.amend(className := "normal")
            else e.render.amend(className := "reverse")
          }
        )
      )
    )
  }
}
