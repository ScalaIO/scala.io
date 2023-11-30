package io.scala.domaines

case class Sponsor(
    photo: String,
    website: String,
    rank: Sponsor.SponsorRank
)

object Sponsor {
  enum SponsorRank:
    case Platinum
    case Gold
    case Silver
    case Community
    case Love

    def title: String = this match
      case Platinum  => "🎖️ Platinum 🎖️"
      case Gold      => "🥇 Gold 🥇"
      case Silver    => "🥈 Silver 🥈"
      case Community => "👥 Community 👥"
      case Love      => "❤️ J'aime Scala ❤️"

    def css: String = this match
      case Platinum  => "platinum"
      case Gold      => "gold"
      case Silver    => "silver"
      case Community => "community"
      case Love      => "love"

  object SponsorRank:
    given Ordering[SponsorRank] = Ordering[Int].on(_.ordinal)
}
