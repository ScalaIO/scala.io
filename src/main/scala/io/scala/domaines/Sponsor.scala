package io.scala.domaines

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
  enum Rank:
    case Platinum
    case Gold
    case Silver
    case Community
    case Partner
    case Love

    def title: String = this match
      case Platinum  => "🎖️ Platinum 🎖️"
      case Gold      => "🥇 Gold 🥇"
      case Silver    => "🥈 Silver 🥈"
      case Community => "👥 Community 👥"
      case Love      => "❤️ J'aime Scala ❤️"
      case Partner   => "🤝 Partner 🤝"

    def css: String = this match
      case Platinum  => "platinum"
      case Gold      => "gold"
      case Silver    => "silver"
      case Community => "community"
      case Love      => "love"
      case Partner   => "partner"

  object Rank:
    given Ordering[Rank] = Ordering[Int].on(_.ordinal)
}
