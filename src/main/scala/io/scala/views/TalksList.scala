package io.scala.views

import io.scala.Lexicon
import io.scala.data.TalksInfo
import io.scala.domaines.*
import io.scala.modules.TalkCard
import io.scala.modules.elements.*

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.console
import org.scalajs.dom.document
import org.scalajs.dom.window

val blankDivs: Var[List[Div]] = Var(List.empty)

case object TalksList extends SimpleView {

  def bodyContent(talks: List[Talk]) = sectionTag(
    className := "container talks-list",
    Title("Talks"),
    Line(margin = 55),
    div(
      talks.map(TalkCard(_)),
      children <-- blankDivs.signal,
      className := "card-container"
    ),
  )

  def body(withDraft: Boolean): HtmlElement =
    if withDraft then bodyContent(TalksInfo.allTalks)
    else bodyContent(TalksInfo.allTalks.filter(_.speakers.forall(_.confirmed)))

  def title = "Talks"
}
