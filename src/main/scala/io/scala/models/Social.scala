package io.scala.models

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLAnchorElement

import io.scala.modules.elements.Links
import io.scala.svgs.*

case class Social(
    kind: Social.Kind,
    url: String
):
  private lazy val icon = kind match
    case Social.Kind.Twitter  => Icons.twitter
    case Social.Kind.Mastodon => Icons.mastodon
    case Social.Kind.Linkedin => Icons.linkedin
    case Social.Kind.Github   => Icons.github
    case Social.Kind.Other    => Icons.chain
    case Social.Kind.Gitlab   => Icons.gitlab

  private val linkName = kind match
    case Social.Kind.Other => url.split("://").last.stripSuffix("/")
    case _                 => url.stripSuffix("/").splitAt(url.lastIndexOf("/") + 1).last

object Social:
  given Ordering[Social] = Ordering.by(_.kind.ordinal)

  enum Kind:
    case Twitter, Mastodon, Linkedin, Github, Gitlab, Other

  def renderIcons(socials: List[Social], owner: String): Seq[ReactiveHtmlElement[HTMLAnchorElement]] =
    socials.sorted.map: social =>
      a(social.icon, href := social.url, aria.label := s"$owner's ${social.kind}")

  def renderWithAccount(socials: List[Social]): Seq[ReactiveHtmlElement[HTMLAnchorElement]] =
    socials.sorted.map:
      case social @ (Social(Kind.Twitter | Kind.Mastodon | Kind.Github | Kind.Gitlab, _)) =>
        Links.highlighted(social.icon, href := social.url, span("@", social.linkName))
      case social @ (Social(Kind.Linkedin, _) | Social(Kind.Other, _)) =>
        Links.highlighted(social.icon, href := social.url, span(social.linkName))
