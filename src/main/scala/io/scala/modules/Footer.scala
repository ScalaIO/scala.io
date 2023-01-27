package io.scala.modules

import io.scala.Data
import io.scala.svgs.{Linkedin, Logo, Twitter}

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object Footer {
  private val summary: Div =
    div(
      Logo(),
      p(Data.Footer.Description.text, className := "footer__summary__description"),
      className := "footer__summary"
    )

  private val newsletter: Div =
    div(
      h3(
        Data.Footer.Newsletter.title,
        className := "footer__newsletter__title"
      ),
      div(
        div(
          Data.Footer.Newsletter.description,
          className := "footer__newsletter__description"
        ),
        form(
          action := "https://scala.us13.list-manage.com/subscribe/post?u=32bc8243e81ad6b3dde9a6717&amp;id=c28c7e3f2b",
          method := "post",
          idAttr := "mc-embedded-subscribe-form",
          target := "_blank",
          noValidate := true,
          input(
            idAttr    := "mce-EMAIL",
            typ       := "email",
            name      := "EMAIL",
            className := "footer__newsletter__email"
          ),
          ShinyButton(Data.Footer.Newsletter.button),
          className := "footer__newsletter__form"
        ),
        className := "footer__newsletter__body"
      ),
      className := "footer__newsletter"
    )

  def social(icon: SvgElement, url: String) = a(
    icon,
    href      := url,
    className := "footer__social"
  )

  def apply(): HtmlElement = footer(
    div(
      summary,
      newsletter,
      className := "footer__container"
    ),
    Line(),
    div(
      div(
        Data.Footer.copyright,
        className := "footer__copyright"
      ),
      div(
        social(Linkedin(), "https://www.linkedin.com/company/scala-io"),
        social(Twitter(), "https://twitter.com/ScalaIO_FR"),
        className := "footer__socials"
      ),
      className := "footer__container"
    ),
    className := "footer"
  )
}
