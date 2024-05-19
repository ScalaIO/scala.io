import knockoff.ChunkParser
import knockoff.HeaderChunk

object TalkParser {
  val parser = new ChunkParser
  import parser.{*, given}
  val header      = parser.emptyLine ~> parser.header <~ parser.emptyLine
  val bulletItems = parser.bulletItem.+

  val speakerParser = header ~ bulletItems

  def speakerStringToObject(speaker: String): Speaker =
    parser.parse(speakerParser, speaker) match {
      case Success(HeaderChunk(_, speakerName) ~ infos, bio) =>
        val infosMap =
          infos.map { x =>
            val (key, value) = x.content.splitAt(x.content.indexOf(":"))
            (key.trim(), value.drop(1).trim())
          }.toMap

        val descriptionHtml = parseDescription(bio.source)("Failed to parse bio's markdown")
        Speaker(
          name = speakerName,
          photo = infosMap.get("photo"),
          bio = descriptionHtml,
          job = infosMap.get("job").getOrElse(""),
          company = infosMap.get("company").getOrElse(""),
          socials = List(),
          confirmed = infosMap.get("confirmed").map(_ == "true").getOrElse(false)
        )
      case _ => ???
    }
}
