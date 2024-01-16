package io.scala.views

import com.raquo.laminar.api.L.*
import io.scala.modules.elements.Title


object FAQView extends SimpleView {
  override def title: String = "Frequently Asked Questions"

  def question(question: String, answer: String): HtmlElement = {
    div(
      className := "question",
      h2(question),
      p(answer)
    )
  }

  override def body(withDraft: Boolean): HtmlElement = {
    sectionTag(
      className := "container",
      Title("Frequently Asked Questions"),

      question("Why 2023-10 was cancelled?","It was difficult to get sponsorship to close the budget, that's why we had to work on a new format for this edition."),

      question(
        "Is the a Code of Conduct?",
        """We had a Code of Conduct, the new one is lighter : 'please be nice', 'we are here to exchange idea and appreciate a shared moment', 'if there is something disturbing, we will take care of it'.
          |
          |We are not avoiding the idea of a CoC and it was discussed with others in the community. Writing something is not the same thing are making sure everyone is welcomed.
          |So we moved to a more convergent approach,
          | in continuity with the quality of our event.
          |""".stripMargin
      ),
      question("Do you reimburse the tickets?", "We would prefer not to, however you can always send use an email and we will see what we can do. (We always reimbursed tickets if needed)")


    )
  }
}