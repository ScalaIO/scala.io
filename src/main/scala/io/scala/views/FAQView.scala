package io.scala.views

import io.scala.domaines.Social
import io.scala.data.OrgaInfo.allOrga
import io.scala.domaines.Organizer
import io.scala.domaines.Social
import io.scala.modules.elements.Title
import io.scala.svgs.Github

import com.raquo.laminar.api.L.*

object FAQView extends SimpleView {
  override def title: String = "Frequently Asked Questions"

  private def question(question: String, answer: String): HtmlElement = {
    div(
      className := "question",
      h2(question),
      p(answer)
    )
  }

  private def question(question: String, answer: HtmlElement*): HtmlElement = {
    div(
      className := "question",
      h2(question),
      answer
    )
  }

  override def body(withDraft: Boolean): HtmlElement = {
    sectionTag(
      className := "container faq",
      Title("Frequently Asked Questions"),
      question(
        "Why 2023-10 was cancelled?",
        "It was difficult to get sponsorship to close the budget, that's why we had to work on a new format for this edition."
      ),
      question(
        "Why Nantes?",
        """The conference was in Paris (2013, 2014, 2022) and Lyon (2016, 2017, 2018, 2019).
          |The organisers are in majority familiar with Nantes, Clever Cloud and _icilundi offered to facilitate hosting the event.""".stripMargin
      ),
      question(
        "Is there a Code of Conduct?",
        """We had a Code of Conduct, the new one is lighter : 'please be nice', 'we are here to exchange idea and appreciate a shared moment', 'if there is something disturbing, we will take care of it'.
          |
          |We are not avoiding the idea of a CoC and it was discussed with others in the community. Writing something is not the same thing are making sure everyone is welcomed.
          |So we moved to a more convergent approach,
          | in continuity with the quality of our event.
          |""".stripMargin
      ),
      question(
        "Do you reimburse tickets?",
        "We would prefer not to, however you can always send us an email. We always reimbursed tickets if needed."
      ),
      question(
        "Can I have a receipt?",
        "In the confirmation email, there is a link to download it. If you can't find it, send us an email and we will send you a new one."
      ),
      question(
        "What did you use to build this website?",
        ul(
          li(
            "Source code: ",
            a(href := "https://scala-lang.org", "Scala 3"),
            " with ",
            a(href := "https://www.scala-js.org", "Scala.js"),
            " to compile Scala to JavaScript and ",
            a(href := "https://laminar.dev", "Laminar"),
            " to build a reactive UI."
          ),
          li(
            "Hosting: ",
            a(href := "https://github.com/ScalaIO/scala.io", "Github repository"),
            " for the source code and ",
            a(href := "https://www.clever-cloud.com", "Clever Cloud"),
            " for the website"
          )
        )
      ),
      div(
        className := "logos",
        a(
          href     := "https://www.scala-js.org",
          nameAttr := "Scala.js",
          img(
            src := "logos/scalajs.svg",
            alt := "Scala.js logo"
          )
        ),
        a(
          href     := "https://laminar.dev",
          nameAttr := "Laminar",
          img(
            src := "logos/laminar.webp",
            alt := "Laminar logo"
          )
        ),
        a(
          href     := "https://www.clever-cloud.com",
          nameAttr := "Clever Cloud",
          img(
            src := "logos/clever.svg",
            alt := "Clever Cloud logo"
          )
        ),
        a(
          href     := "https://github.com/ScalaIO/scala.io",
          nameAttr := "Github",
          Github()
        )
      ),
      question(
        "I found a bug / an error on the website, what should I do?",
        p(
          "Feel free to open an issue us on the ",
          a(href := "https://github.com/ScalaIO/scala.io/issues/new", "Github repository"),
          " or send us an email at ",
          a(href := "mailto:contact@scala.io", "contact@scala.io")
        )
      ),
      Title("Who is behind Scala.IO?"),
      div(),
      Title.small("Representatives"),
      organizersList(allOrga.filter(_.representative)),
      Title.small("Organizers"),
      organizersList(allOrga.filterNot(_.representative))
    )
  }

  def organizersList(orgs: List[Organizer]): Div = div(
    className := "card-container organizers",
    orgs.map: org =>
      div(
        className := "orga",
        img(
          className := "photo",
          src       := org.photoPath,
          alt       := s"${org.name}'s profile picture"
        ),
        div(
          p(org.name),
          p(org.job),
          org.misc.map(misc => p(misc)),
          Social.render(org.socials)
        )
      )
  )
}
