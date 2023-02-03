package io.scala.domaines

case class Speaker(
    name: String,
    presentation: Presentation,
    job: String,
    company: String,
    socials: List[Social],
    talk: Talk
)
