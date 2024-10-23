package io.scala.models

case class Sponsor(
    name: String,
    photo: String,
    website: String,
    rank: Sponsor.Rank,
    gridCol: Int = 1,
    gridRow: Int = 1
):
  def photoPath = s"images/sponsors/${photo}"

object Sponsor {
  enum Rank(val title: String, val css: String):
    case Platinum  extends Rank("🎖️ Platinum 🎖️", "platinum")
    case Gold      extends Rank("🥇 Gold 🥇", "gold")
    case Silver    extends Rank("🥈 Silver 🥈", "silver")
    case Bronze    extends Rank("🥉 Bronze 🥉", "silver")
    case Community extends Rank("👥 Community 👥", "community")
    case Love      extends Rank("❤️ J'aime Scala ❤️", "love")
    case Partner   extends Rank("🤝 Partner 🤝", "partner")

  def empty = Sponsor("Malformed sponsor", "", "", Rank.Partner)

  object Rank:
    given Ordering[Rank] = Ordering[Int].on(_.ordinal)
}
