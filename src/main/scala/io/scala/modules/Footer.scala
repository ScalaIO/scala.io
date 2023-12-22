package io.scala.modules

import io.scala.Lexicon
import io.scala.svgs.{Linkedin, Logo, Twitter}

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Footer {
  private val summary: Div =
    div(
      Logo(),
      p(Lexicon.Footer.Description.text, className := "description"),
      className := "footer__summary"
    )

  private val newsletter: Div =
    div(
      h3(
        Lexicon.Footer.Newsletter.title,
        className := "title-small"
      ),
      div(
        div(
          Lexicon.Footer.Newsletter.description,
          className := "description"
        ),
        form(
          action := "https://pm.us21.list-manage.com/subscribe/post?u=4b3f57c730963d7046d571d18&amp;id=66f1c9c459&amp;f_id=008ae1e6f0",
          method := "post",
          idAttr := "mc-embedded-subscribe-form",
          target := "_blank",
          noValidate := true,
          input(
            idAttr    := "mce-EMAIL",
            typ       := "email",
            nameAttr  := "EMAIL",
            className := "footer__newsletter__email"
          ),
          ShinyButton(Lexicon.Footer.Newsletter.button),
          className := "form"
        ),
      ),
      className := "footer__newsletter"
    )

  def social(icon: SvgElement, url: String) = a(
    icon,
    href      := url,
  )

  lazy val render: HtmlElement = footerTag(
    div(
      summary,
      newsletter,
      className := "footer__main"
    ),
    Line(kind = LineKind.Contrasted),
    div(
      div(
        Lexicon.Footer.copyright,
        className := "footer__copyright"
      ),
      div(
        social(Linkedin(), "https://www.linkedin.com/company/scala-io"),
        social(Twitter(), "https://twitter.com/ScalaIO_FR"),
        className := "social"
      ),
      className := "footer__others"
    ),
    className := "footer"
  )
}
