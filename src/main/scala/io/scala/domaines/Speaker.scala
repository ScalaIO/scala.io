package io.scala.domaines

case class Speaker(
    name: String,
    photo: Option[String],
    job: String,
    company: String,
    socials: List[Social],
    talk: Talk
)
