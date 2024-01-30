import java.nio.file.Files
import java.nio.file.Path
import knockoff.ChunkParser
import knockoff.LinkDefinitionChunk
import scala.jdk.CollectionConverters.*

val parser = new ChunkParser
// import parser.{*, given}
val linkParser = parser.linkDefinition
val paragraph = (parser.textBlock ~ parser.linkDefinition.*).+
val textBlock = (parser.emptyLine.* ~> paragraph <~ parser.emptyLine.*).+
val talk      = parser.phrase(parser.header ~ textBlock)

val str = Files
  .readAllLines(
    Path.of("/home/lucasn/Projects/Scala/scala.io/src/main/resources/md/chasing-arrows-functors-monads.md")
  )
  .asScala

case class Link(href: String, title: String)

val link = "[scala.io]: <abc>(https://scala.io)"

parser.parse(linkParser, link)

val regex = """[ ]{0,3}\[[^\[\]]*\]:[ ]+<?[\w\p{Punct}]+>?""".r
regex.findFirstIn(link)
// str.map: s =>
//   parser.parse(paragraph, s) match
//     case parser.Success(result, next) =>
//       result.map:
//         case text ~ others =>
//           others.map:
//             case (link: LinkDefinitionChunk) ~ None => Link(link.url, link.title.get)
//             case (link: LinkDefinitionChunk) ~ Some(text2) =>
//               Link(link.url, link.title.get)
//             case _ => List()
//     case fail: parser.NoSuccess =>
//       (fail.msg, Nil)
