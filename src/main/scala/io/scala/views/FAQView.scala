package io.scala.views

import io.scala.data.OrgaInfo.allOrga
import io.scala.domaines.Organizer
import io.scala.domaines.Social
import io.scala.modules.elements.{Links, Titles}
import io.scala.modules.elements.Cards
import io.scala.modules.elements.Lists
import io.scala.modules.elements.Paragraphs
import io.scala.svgs.Icons.github

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom.HTMLDivElement

object FAQView extends SimpleView {
  private def question(question: String, answer: Modifier[ReactiveElement[HTMLDivElement]]*): HtmlElement = {
    Cards.shadowed(
      Titles.small(question),
      div(answer)
    )
  }

  extension (element: HtmlElement)
    def withGridSizes(row: Int, col: Int): HtmlElement =
      element.amend(
        styleAttr := s"grid-row: span $row; grid-column: span $col;"
      )

  override def body(): HtmlElement = {
    sectionTag(
      className := "container faq",
      Titles("Frequently Asked Questions"),
      div(),
      Titles.medium("Common questions", idAttr := "common-questions"),
      commonQuestions,
      Titles.medium("Speaker questions", idAttr := "speaker-questions"),
      speakerQuestions,
      Titles("Who is behind Scala.IO?"),
      div(),
      Titles.medium("Representatives"),
      organizersList(allOrga.filter(_.representative)),
      Titles.medium("Organizers"),
      organizersList(allOrga.filterNot(_.representative))
    )
  }

  lazy val commonQuestions = Cards.container(
    question(
      "Is there a Code of Conduct (CoC) ?",
      p("Yes, you can find it ", Links.highlightedInPlace(href := "/code-of-conduct", "here")),
      Paragraphs.description("For any violation of the CoC, please fill in the form available on the CoC page.")
    ),
    question(
      "Why 2023-10 was cancelled?",
      p(
        "It was difficult to get sponsorship to close the budget, that's why we had to work on a new format for this edition."
      )
    ),
    question(
      "Why Nantes?",
      p(
        """The conference was in Paris (2013, 2014, 2022) and Lyon (2016, 2017, 2018, 2019).
          |The organisers are in majority familiar with Nantes, Clever Cloud and _icilundi offered to facilitate hosting the event.""".stripMargin
      )
    ),
    question(
      "Do you reimburse tickets?",
      p("We would prefer not to, however you can always send us an email. We always reimbursed tickets if needed.")
    ),
    question(
      "Can I have a receipt?",
      p(
        "In the confirmation email, there is a link to download it. If you can't find it, send us an email and we will send you a new one."
      )
    ),
    question(
      "What did you use to build this website?",
      Lists.innerDiscs(
        li(
          "Source code: ",
          Links.highlighted(href := "https://scala-lang.org", "Scala 3"),
          " with ",
          Links.highlighted(href := "https://www.scala-js.org", "Scala.js"),
          " to compile Scala to JavaScript and ",
          Links.highlighted(href := "https://laminar.dev", "Laminar"),
          " to build a reactive UI."
        ),
        li(
          "Hosting: ",
          Links.highlighted(href := "https://github.com/ScalaIO/scala.io", "Github repository"),
          " for the source code and ",
          Links.highlighted(href := "https://www.clever-cloud.com", "Clever Cloud"),
          " for the website"
        )
      ),
      div(
        className := "logos",
        Links.highlighted(
          href     := "https://www.scala-js.org",
          nameAttr := "Scala.js",
          img(
            src := "logos/scalajs.svg",
            alt := "Scala.js logo"
          )
        ),
        Links.highlighted(
          href     := "https://laminar.dev",
          nameAttr := "Laminar",
          img(
            src := "logos/laminar.webp",
            alt := "Laminar logo"
          )
        ),
        Links.highlighted(
          href     := "https://www.clever-cloud.com",
          nameAttr := "Clever Cloud",
          img(
            src := "logos/clever.svg",
            alt := "Clever Cloud logo"
          )
        ),
        Links.highlighted(
          href     := "https://github.com/ScalaIO/scala.io",
          nameAttr := "Github",
          github
        )
      )
    ).withGridSizes(2, 1),
    question(
      "I found a bug / an error on the website, what should I do?",
      p(
        "Feel free to open an issue us on the ",
        Links.highlighted(href := "https://github.com/ScalaIO/scala.io/issues/new", "Github repository"),
        " or send us an email at ",
        Links.highlighted(href := "mailto:contact@scala.io", "contact@scala.io")
      )
    )
  )

  lazy val speakerQuestions = Cards.container(
    question(
      "How are the talks selected?",
      p(
        "We have a call for papers (CFP) whose link is available on the website. The talks are selected by the organizers plus a few external people."
      ),
      p(
        "The selection process is based on the content of the talk and the appeal of the reviewer to the subject. Reviewer rank talks (between #1 and #5), can ask for precisions if needed. The global rank between talks is used to guide selection during overview in group sessions. We take into account diversity of subjects, and also give some room to \"experimental\" subjects, first time speakers..."
      ),
      p(
        fontSize.small,
        "There is a stratified, open ballot, exponentially weighted borda count to help with pre-selection"
      )
    ).withGridSizes(1, 2),
    question(
      "How will I be notified if my talk is selected?",
      p(
        "We will send you an email to the address you used to submit your talk. But please keep in mind that if you don't answer to our emails, within 2 weeks, we will consider that you declined the invitation. We will then contact the next speaker in the list."
      )
    ),
    question(
      "Do I need to sign something?",
      p(
        "When your talk is selected, you just need to confirm you are willing to given your talk, and we send you a form about travel and expenses, and keep you updated when we announce your talk."
      )
    ),
    question(
      "Do you reimburse travel expenses?",
      p(
        "We will reimburse travel expenses as far as the budget allows. We will send you a form to fill in with your expense expectancies and we will do our best to cover them."
      )
    ),
    question(
      "Will there be replays?",
      p(
        "Yes, we will record the talks and make them available on our Youtube channel (",
        Links.highlighted(href := "https://www.youtube.com/@scalaio", "https://www.youtube.com/@scalaio"),
        ") after processing them (checking the sound, adding an intro frame at the start if the video, etc.)"
      ),
      p(
        "We reference the video and slides on the website on your talk's page. So please, remember to send us the link/pdf of your slides :)"
      )
    ),
      question (
        "Do I have a free ticket?",
        p("Of course :)")
      ),
    question(
      "What if I am selected and already bought tickets?",
      p("You are reimbursed")
    ),
    question(
      "And if I am not selected?",
      p("You will receive a coupon for tickets")
    )
  )

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
          Social.render(org.socials, org.name)
        )
      )
  )
}
