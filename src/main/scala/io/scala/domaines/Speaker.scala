package io.scala.domaines

import io.scala.svgs.Twitter
import io.scala.svgs.Linkedin

import com.raquo.laminar.api.L.{*, given}

case class Speaker(
    name: String,
    photo: Option[String],
    job: String,
    company: String,
    socials: List[Social],
    talk: Talk
):
    def socialNetworks = socials.map {
        case Social(Social.Kind.Twitter, url)   => a(Twitter(), href := url)
        case Social(Social.Kind.Linkedin, url)  => a(Linkedin(), href := url)
        case Social(Social.Kind.Other, url)     => a(href := url)
    }
