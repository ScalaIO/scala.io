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
) extends Person