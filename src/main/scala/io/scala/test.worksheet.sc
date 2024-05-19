import java.nio.file.Files
import knockoff.Chunk
import knockoff.ChunkParser
import knockoff.HeaderChunk
import knockoff.LinkDefinitionChunk

val raphaëlLemaitre = 
  """
|# Raphaël Lemaitre
|
|- photo: rLemaitre.webp
|- job: Senior Staff Engineer
|- company: Ledger
|- confirmed: true
|- socials: [Twitter](https://twitter.com/rlemaitre), [Linkedin](https://www.linkedin.com/in/rlemaitre), [Github](https://github.com/rlemaitre), [Other](https://rlemaitre.com)
|
|Bonjour! I'm Raphaël Lemaitre, a seasoned programmer passionate about crafting efficient and sustainable technology. Since embarking on my career journey in 2000, I've been deeply involved in the realm of software development, with a focus on JVM languages like Java and Scala. My technical proficiency includes functional programming, database technologies such as PostgreSQL and Cassandra, along with system design.
|
|Currently, in my role as a Back-end Senior Staff Engineer at Ledger, I concentrate on designing and writing maintainable systems. My goal is to develop software that is not only functional but also robust and adaptable for future advancements. I strive for clean, effective, and scalable solutions that emphasize long-term sustainability in software design.
|
|Beyond my professional pursuits, I'm an enthusiastic golfer, an escape game enthusiast, and a proud father. I firmly believe in the power of continuous learning and sharing knowledge, which led me to create a blog for exchanging ideas, particularly focusing on ADHD strategies for developers.
|
|My interests span from software development to history and technology, where I eagerly engage in conversations that ignite curiosity and foster innovation. Join me in exploring the dynamic landscape of technology, as we seek to build more efficient, maintainable, and impactful technological solutions.
  """.stripMargin

val parser = new ChunkParser
import parser.{*, given}
val header      = parser.emptyLine ~> parser.header <~ parser.emptyLine
val textBlocks  = (parser.emptyLine.* ~> parser.textBlock <~ parser.emptyLine.*).*
val bulletItems = parser.bulletItem.+

val speakerParser = header ~ bulletItems ~ textBlocks

parser.parse(speakerParser, raphaëlLemaitre) match
  case Success(HeaderChunk(_, speakerName) ~ infos ~ bio, next) =>
    infos.map(x => x.content.splitAt(x.content.indexOf(":"))).toMap
  case Failure(msg, next)    => msg
  case Error(_, _)           => ???

val test =
  """
  |# Raphaël Lemaitre
  |
  |- photo: rLemaitre.webp
  |- job: Senior Staff Engineer
  |Bonjour!
  |""".stripMargin

parser.parse(speakerParser, test)
