package io.scala.model

case class Speaker(
    name: String,
    photo: Option[String] = None,
    bio: Seq[HtmlElement] = Seq(),
    job: String = "",
    company: String = "",
    socials: List[Social] = List.empty,
    confirmed: Boolean
) {
  def photoPath = photo.fold(io.scala.profilePlaceholder)(path => s"/images/profiles/$path")
}
