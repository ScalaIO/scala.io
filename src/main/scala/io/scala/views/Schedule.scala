package io.scala.views

import io.scala.Lexicon
import io.scala.domaines.ConfDay
import io.scala.domaines.Speaker
import io.scala.domaines.Talk
import io.scala.modules.{ClassyButton, Line, SpeakerCard, SpeakerModal, Title}
import io.scala.modules.LineKind
import io.scala.modules.ScheduleDay
import io.scala.modules.TalkModal

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import org.scalajs.dom
import org.scalajs.dom.Element
import io.scala.data.TalksInfo
import io.scala.modules.TalkCard

object ScheduleState:
  val selectedTalk: Var[Option[Talk]] = Var(None)

case object Schedule extends View {
  private val selectedDay: Var[Option[String]] = Var(Lexicon.Schedule.days.headOption.map(_._1))
  private val tabLinkName: String              = "tablinks"
  private val talksByDay: Map[Option[ConfDay], Seq[Talk]] =
    TalksInfo.allTalks.groupBy(_.day)
  private var days: Map[ConfDay, ScheduleDay] = Map()

  override def body: HtmlElement = sectionTag(
    className := "container",
    Title("Schedule"),
    p(
      "The schedule will be available soon.",
      className := "catch-phrase"
    ),
    a(
      ClassyButton(Lexicon.Speakers.callToAction),
      href   := "https://www.papercall.io/scalaio-2024-nte",
      target := "_blank"
    ),
    Line(margin = 55),
    div(
      h2("To be scheduled"),
      div(
        className := "card-container unassigned",
        TalksInfo.allTalks.filter(_.day.isEmpty).map(TalkCard(_))
      )
    ),
    Line(margin = 55),
    div(
      className := "schedule__tab",
      Lexicon.Schedule.days.map { (id, day) =>
        div(
          button(
            idAttr    := id,
            className := tabLinkName,
            onClick --> { _ => changeDay(id) },
            h2(day.toString())
          ),
          child <-- selectedDay.signal.map {
            case Some(i) if i == id => Line(margin = 24, size = 3, kind = LineKind.Colored)
            case _                  => emptyNode
          }
        )
      }
    ),
    Lexicon.Schedule.days.map { (id, day) =>
      div(
        idAttr    := id,
        className := "schedule__tabcontent",
        display <-- selectedDay.signal.map {
          case Some(i) if i == id => "block"
          case _                  => "none"
        },
        child <-- selectedDay.signal.map {
          case Some(i) if i == id =>
            days.get(day).map(_.body).getOrElse {
              val newDay = ScheduleDay(talksByDay.get(Some(day)).getOrElse(Seq.empty))
              days += (day -> newDay)
              newDay.body
            }
          case _ => emptyNode
        }
      )
    },
    TalkModal(ScheduleState.selectedTalk),
    onClick.compose {
      _.withCurrentValueOf(ScheduleState.selectedTalk.signal)
        .collect { case (event, Some(_)) =>
          event.target match
            case e: Element if e.classList.contains("card-overlay") =>
              ScheduleState.selectedTalk.set(None)
        }
    } --> ()
  )

  def changeDay(id: String) = selectedDay.set(Some(id))

}
