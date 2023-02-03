package io.scala.domaines

case class Social(
    kind: Social.Kind,
    url: String
)

object Social {
  enum Kind:
    case Twitter
    case Linkedin
    case Other
}
