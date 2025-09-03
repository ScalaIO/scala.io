package io.scala.modules.layout

import com.raquo.laminar.api.L.*

import io.scala.IndexPage
import io.scala.Lexicon
import io.scala.Page
import io.scala.SessionsPage
import io.scala.SponsorsPage
import io.scala.data.ConfsData
import io.scala.modules.elements.Buttons
import io.scala.modules.elements.Containers
import io.scala.modules.elements.Line
import io.scala.modules.elements.LineKind
import io.scala.modules.elements.Links
import io.scala.modules.elements.Lists
import io.scala.modules.elements.Titles
import io.scala.svgs.Icons

object Footer {
  private lazy val summary: Div =
    div(
      Icons.logo(),
      p(Lexicon.Footer.Description.text, className := "description"),
      className := "summary"
    )

  private lazy val newsletter: Div =
    div(
      className := "newsletter",
      Titles.smallThin("Newsletter"),
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
        Buttons.shiny(Lexicon.Footer.Newsletter.button)
      )
    )

  def editions =
    div(
      Titles.smallThin("Editions"),
      Lists.pipes(
        ConfsData.all
          .map { key =>
            val linkKey = key.replace("_", "-")
            li(
              Links
                .highlighted(
                  key.capitalize.replace("_", " "),
                  Page.navigateTo {
                    Page.current match
                      case index: IndexPage       => index.copy(conference = Some(linkKey))
                      case talks: SessionsPage    => talks.copy(conference = Some(linkKey))
                      case sponsors: SponsorsPage => sponsors.copy(conference = Some(linkKey))
                      case _                      => SessionsPage(conference = Some(linkKey))
                  }
                )
            )
          }*
      )
    )

  def social(icon: SvgElement, url: String, name: String) = a(
    icon,
    href       := url,
    aria.label := name
  )

  def render: HtmlElement = footerTag(
    className := "container",
    Containers.flex(
      Containers.flex(stableElements._1, editions, flexDirection.column),
      stableElements._2
    ),
    stableElements._3
  )

  lazy val stableElements =
    (
      summary,
      newsletter,
      Seq(
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
    )
}
