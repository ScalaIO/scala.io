package io.scala.data

import io.scala.data.TalksInfo.allTalks
import io.scala.domaines.Talk.Category
import io.scala.domaines._

import scala.util.Random

object ScheduleInfo {
  val blankSpeakers = Array(
    Speaker(
      name = "John Doe",
      description = "To be scheduled",
      confirmed = true
    ),
    Speaker(
      name = "Jane Doe",
      description = "To be scheduled",
      confirmed = true
    )
  )
  def speaker = if Random.nextBoolean() then blankSpeakers(0) else blankSpeakers(1)
  def talk(day: ConfDay, start: Time, kind: Talk.Kind) =
    Talk(
      title = "Lorem ipsum dolor sit amet consectetur adipiscing elit",
      slug = "lorem-ipsum",
      description = """# Lorem ipsum dolor sit amet consectetur adipiscing elit
        
        Lorem ipsum dolor sit amet consectetur adipiscing elit. Sed vitae eros quis nisl aliquam aliquet. Sed vitae eros quis nisl aliquam aliquet. Sed vitae eros quis nisl aliquam aliquet. Sed vitae eros quis nisl aliquam aliquet.""",
      speakers = List(speaker),
      day = day,
      room = Room.One,
      start = start,
      kind = kind,
      category = Category.Effects
    )

  val blankTalks = List(
    talk(
      ConfDay.Thursday,
      Time(9, 30),
      Talk.Kind.Keynote
    ),
    talk(
      ConfDay.Thursday,
      Time(10, 50),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Thursday,
      Time(11, 40),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Thursday,
      Time(13, 30),
      Talk.Kind.Short
    ),
    talk(
      ConfDay.Thursday,
      Time(14, 0),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Thursday,
      Time(14, 50),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Thursday,
      Time(15, 40),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Thursday,
      Time(16, 40),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Thursday,
      Time(17, 30),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(9, 0),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(9, 50),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(10, 50),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(11, 40),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(13, 30),
      Talk.Kind.Short
    ),
    talk(
      ConfDay.Friday,
      Time(14, 0),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(14, 50),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(15, 40),
      Talk.Kind.Speech
    ),
    talk(
      ConfDay.Friday,
      Time(16, 40),
      Talk.Kind.Lightning
    ),
    talk(
      ConfDay.Friday,
      Time(17, 0),
      Talk.Kind.Keynote
    )
  )
  val breaks = List(
    Break(
      day = ConfDay.Thursday,
      start = Time(10, 30),
      kind = Break.Kind.Large,
      overrideDuration = Some(20)
    ),
    Break(
      day = ConfDay.Thursday,
      start = Time(12, 30),
      kind = Break.Kind.Lunch
    ),
    Break(
      day = ConfDay.Thursday,
      start = Time(16, 25),
      kind = Break.Kind.Large
    ),
    Special(
      day = ConfDay.Thursday,
      start = Time(18, 35),
      kind = Special.Kind.End
    ),
    // Special(
    //   day = ConfDay.Thursday,
    //   start = Time(19, 30),
    //   kind = Special.Kind.CommunityParty
    // ),
    Break(
      day = ConfDay.Friday,
      start = Time(10, 35),
      kind = Break.Kind.Large
    ),
    Break(
      day = ConfDay.Friday,
      start = Time(12, 30),
      kind = Break.Kind.Lunch
    ),
    Break(
      day = ConfDay.Friday,
      start = Time(16, 25),
      kind = Break.Kind.Large
    ),
    Special(
      day = ConfDay.Friday,
      start = Time(18, 0),
      kind = Special.Kind.End
    )
  )

  val minStart           = Time(9, 0)
  val maxEnd             = Time(19, 0)
  val pxByHour           = 600
  lazy val blankSchedule = blankTalks ++ breaks
  lazy val schedule      = allTalks ++ breaks
}
