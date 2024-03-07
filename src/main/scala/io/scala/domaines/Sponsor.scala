package io.scala.domaines

case class Sponsor(
    name: String,
    photo: String,
    website: String,
    rank: Sponsor.Rank,
    colSpan: Int,
    rowSpan: Int
):
  def photoPath = s"images/sponsors/${photo}"

object Sponsor {
  enum Rank:
    case Platinum
    case Gold
    case Silver
    case Love
    case Community
    case Partner
    case Yearly

    def title: String = this match
      case Platinum  => "🎖️ Platinum 🎖️"
      case Gold      => "🥇 Gold 🥇"
      case Silver    => "🥈 Silver 🥈"
      case Community => "👥 Community 👥"
      case Love      => "❤️ J'aime Scala ❤️"
      case Partner   => "🤝 Partner 🤝"
      case Yearly    => "🗓️ Yearly 🗓️"

    def css: String = this match
      case Platinum  => "platinum"
      case Gold      => "gold"
      case Silver    => "silver"
      case Community => "community"
      case Love      => "love"
      case Partner   => "partner"
      case Yearly    => "yearly"

  object Rank:
    given Ordering[Rank] = Ordering[Int].on(_.ordinal)
}
