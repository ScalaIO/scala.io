package io.scala.domaines

import com.raquo.laminar.api.L.{*, given}

final case class Organizer(
  name: String,
  photo: Option[String] = None,
  description: String = "",
  job: String = "",
  company: String = "",
  socials: List[Social] = List.empty,
) extends Person
