package io.scala.domaines

import com.raquo.laminar.api.L.{*, given}

final case class Organizer(
  name: String,
  photo: Option[String] = None,
  job: String = "",
  company: String = "",
  socials: List[Social] = List.empty,
  representative: Boolean = false
):
  def photoPath = photo.fold(io.scala.profilePlaceholder)(path => s"/images/profiles/$path")
