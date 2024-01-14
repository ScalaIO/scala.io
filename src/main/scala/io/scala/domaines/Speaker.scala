package io.scala.domaines

import io.scala.svgs.Chain
import io.scala.svgs.Github
import io.scala.svgs.Linkedin
import io.scala.svgs.Twitter

import com.raquo.laminar.api.L.{*, given}

case class Speaker(
    name: String,
    photo: Option[String] = None,
    description: String = "",
    job: String = "",
    company: String = "",
    socials: List[Social] = List.empty,
    confirmed: Boolean
):
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
