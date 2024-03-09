package io.scala.domaines

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
  def renderDescription = description.split("\n").map(p(_))
  def photoPath = photo.fold(io.scala.profilePlaceholder)(path => s"/images/profiles/$path")
