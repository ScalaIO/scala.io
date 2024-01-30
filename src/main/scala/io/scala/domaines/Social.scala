package io.scala.domaines

import io.scala.svgs.*

import com.raquo.laminar.api.L.{*, given}

case class Social(
    kind: Social.Kind,
    url: String
):
  lazy val icon = kind match
    case Social.Kind.Twitter  => Twitter()
    case Social.Kind.Linkedin => Linkedin()
    case Social.Kind.Github   => Github()
    case Social.Kind.Other    => Chain()

  val linkName = kind match
    case Social.Kind.Other => url.split("://").last.stripSuffix("/")
    case _                 => url.stripSuffix("/").splitAt(url.lastIndexOf("/") + 1).last

object Social:
  given Ordering[Social] = Ordering.by(_.kind.ordinal)

  enum Kind:
    case Twitter
    case Linkedin
    case Github
    case Other

  def render(socials: List[Social], owner: String) = socials.sorted.map: social =>
    a(social.icon, href := social.url, aria.label := s"$owner's ${social.kind}")

  def renderWithAccount(socials: List[Social]) = socials.sorted.map:
    case social @ (Social(Kind.Twitter, _) | Social(Kind.Github, _)) =>
      a(social.icon, href := social.url, span("@", social.linkName))
    case social @ (Social(Kind.Linkedin, _) | Social(Kind.Other, _)) =>
      a(social.icon, href := social.url, span(social.linkName))
