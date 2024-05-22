package io.scala.modules.layout

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.scala.Lexicon
import io.scala.modules.elements.Line
import io.scala.modules.elements.LineKind
import io.scala.modules.elements.ShinyButton
import io.scala.svgs.Icons

object Footer {
  private val summary: Div =
    div(
      Icons.logo(),
      p(Lexicon.Footer.Description.text, className := "description"),
      className := "summary"
    )

  private val newsletter: Div =
    div(
      className := "newsletter",
      h3(Lexicon.Footer.Newsletter.title),
      p(
        Lexicon.Footer.Newsletter.description,
        className := "description"
      ),
      form(
        action := "https://scala.us21.list-manage.com/subscribe/post?u=1cda6358b5f519bda81ecb37a&amp;id=e31dddaa5a&amp;f_id=005af1e6f0",
        method     := "post",
        idAttr     := "mc-embedded-subscribe-form",
        target     := "_blank",
        noValidate := true,
        input(
          idAttr     := "mce-EMAIL",
          typ        := "email",
          nameAttr   := "EMAIL",
          aria.label := "Search"
        ),
        ShinyButton(Lexicon.Footer.Newsletter.button)
      )
    )

  def social(icon: SvgElement, url: String, name: String) = a(
    icon,
    href := url,
    aria.label := name
  )

  lazy val render: HtmlElement = footerTag(
    className := "container",
    div(
      summary,
      newsletter,
      className := "main"
    ),
    Line(kind = LineKind.Contrasted),
    div(
      div(
        Lexicon.Footer.copyright,
        className := "copyright"
      ),
      div(
        social(Icons.linkedin, "https://www.linkedin.com/company/scala-io", "Linkedin ScalaIO"),
        social(Icons.twitter, "https://twitter.com/ScalaIO_FR", "Twitter ScalaIO"),
        className := "social"
      ),
      className := "others"
    )
  )
}
