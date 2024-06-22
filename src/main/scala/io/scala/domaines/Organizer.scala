package io.scala.domaines

import com.raquo.laminar.api.L.*

final case class Organizer(
    name: String,
    photo: Option[String] = None,
    job: String = "",
    misc: Option[String] = None,
    socials: List[Social] = List.empty,
    representative: Boolean = false
):
  def photoPath = photo.fold(io.scala.profilePlaceholder)(path => s"/images/orgas/$path")
