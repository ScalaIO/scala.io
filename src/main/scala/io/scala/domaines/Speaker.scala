package io.scala.domaines

import io.scala.svgs.Twitter
import io.scala.svgs.Linkedin
import io.scala.svgs.Github

import com.raquo.laminar.api.L.{*, given}
import io.scala.svgs.Chain

case class Speaker(
    name: String,
    photo: Option[String] = None,
    description: String = "",
    job: String = "",
    company: String = "",
    socials: List[Social] = List.empty,
    confirmed: Boolean
):
    // TODO: find a better replacement
    val slug = name.toLowerCase().replaceAll(" ", "-")

    def socialNetworks = socials.map {
        case Social(Social.Kind.Twitter, url)   => a(Twitter(), href := url)
        case Social(Social.Kind.Linkedin, url)  => a(Linkedin(), href := url)
        case Social(Social.Kind.Github, url)  => a(Github(), href := url)
        case Social(Social.Kind.Other, url)     => a(Chain(), href := url)
    }
