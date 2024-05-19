package io.scala.data

val parser = new ChunkParser
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.defs.tags.HtmlTags
import com.raquo.laminar.nodes.ReactiveHtmlElement
import knockoff.ChunkParser
import knockoff.HeaderChunk
import parser.{*, given}
import scala.collection.mutable.{HashMap, Queue}

val textBlock = parser.emptyLine.* ~> parser.textBlock <~ parser.emptyLine.*
val talk      = parser.phrase((parser.header ~ parser.emptyLine.?) ~> (parser.atxHeader.? ~ textBlock).+)

def parseDescription(md: CharSequence)(msg: => String): List[HtmlElement] =
  parser.parse(talk, md) match
    case parser.Success(result, next) =>
      result.flatMap:
        case header ~ textBlock =>
          val initialQueue: Queue[HtmlElement] =
            Queue.from { header.map { case HeaderChunk(_, content) => h4(content) } }

          val text = textBlock.content
          val withLinks = linkRegex
            .findAllMatchIn(text)
            .foldLeft((initialQueue, 0)):
              case ((queue, start), next) =>
                queue += span(text.substring(start, next.start))
                queue += a(href := next.group(2), target := "_blank", next.group(1), aria.label := next.group(2))
                (queue, next.end)
          withLinks._1.enqueue(span(text.substring(withLinks._2)))
    case _: parser.NoSuccess =>
      List(p(msg))
