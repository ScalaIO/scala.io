package io.scala.domaines

import com.raquo.laminar.api.L.{*, given}
import io.scala.svgs.*

trait Person {
  def name: String
  def photo: Option[String]
  def description: String
  def job: String
  def company: String
  def socials: List[Social]

  lazy val renderDescription = description.split("\n").map(p(_))

  lazy val socialNetworks = socials.map {
    case Social(Social.Kind.Twitter, url)  => a(Twitter(), href := url)
    case Social(Social.Kind.Linkedin, url) => a(Linkedin(), href := url)
    case Social(Social.Kind.Github, url)   => a(Github(), href := url)
    case Social(Social.Kind.Other, url)    => a(Chain(), href := url)
  }

  lazy val socialNetworksWithAccount = socials.map {
    case Social(Social.Kind.Twitter, url) =>
      a(Twitter(), href := url, span("@", url.split("twitter.com/").last.stripSuffix("/")))
    case Social(Social.Kind.Linkedin, url) => a(Linkedin(), href := url, span(url.split("/in/").last.stripSuffix("/")))
    case Social(Social.Kind.Github, url) =>
      a(Github(), href := url, span("@", url.split("github.com/").last.stripSuffix("/")))
    case Social(Social.Kind.Other, url) => a(Chain(), href := url, span(url.split("://").last.stripSuffix("/")))
  }
}
