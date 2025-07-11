package io.scala.app.faq

import com.raquo.laminar.api.L.*
import knockoff.Paragraph

import io.scala.modules.elements.Links
import io.scala.modules.elements.Lists
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles
import io.scala.views.SimpleView

case class Law(
    link: Anchor,
    summary: String,
    sanction: Option[String]
):
  def render: HtmlElement =
    tr(
      td(strong(link)),
      td(summary),
      td(sanction.getOrElse("no standalone sanction defined"))
    )

val importantLaws = Seq(
  Law(
    Links.highlighted(
      "225-1 (Penal code)",
      href := "https://www.legifrance.gouv.fr/codes/article_lc/LEGIARTI000045391831"
    ),
    "Define what is discrimination (origins, sex, disabilities, etc.)",
    None
  ),
  Law(
    Links.highlighted(
      "225-2 (Penal code)",
      href := "https://www.legifrance.gouv.fr/codes/article_lc/LEGIARTI000045391831"
    ),
    "Define the sanction for discriminating",
    Some("3 years of prison and 45k€ fine")
  ),
  Law(
    Links
      .highlighted("Art. 24 (Law 1881)", href := "https://www.legifrance.gouv.fr/loda/article_lc/LEGIARTI000043982456"),
    "Incitement to hatred, violence or discrimination",
    Some("1 year of prison and 45k€ fine")
  ),
  Law(
    Links
      .highlighted("Art. 32 (Law 1881)", href := "https://www.legifrance.gouv.fr/loda/article_lc/LEGIARTI000038313312"),
    "Public diffamation",
    Some("1 year of prison and 45k€ fine")
  ),
  Law(
    Links
      .highlighted("Art. 33 (Law 1881)", href := "https://www.legifrance.gouv.fr/loda/article_lc/LEGIARTI000049312747"),
    "Discriminatory public insult",
    Some("1 year of prison and 45k€ fine")
  )
)

object CoCView extends SimpleView:
  override def body(): HtmlElement = sectionTag(
    className := "container coc",
    Titles.withSub("Scala-FR Code of Conduct (CoC)", p("Version 0.0.1")),
    Paragraphs.description(
      "This Code of Conduct is heavily inspired from ",
      Links.highlighted(
        href     := "https://www.functionalscala.com/code-of-conduct",
        nameAttr := "Functional Scala",
        "Functional Scala"
      )
    ),
    Titles.medium("Introduction"),
    Paragraphs.description(
      "The Scala-FR Code of Conduct (CoC) explain the conditions to participate in the community."
    ),
    Paragraphs.description(
      "The purpose of CoC is to facilitate an inclusive and diverse community of professionals who productively work toward shared interests and professional goals."
    ),
    Paragraphs.description(
      "In service of this purpose, we open the community to all qualified individuals and we recommend that all members refrain from any unprofessional or uncomfortable conducts."
    ),
    Paragraphs.description(
      "To sum it up: \"please be nice, we are here for a beneficial share moment, else (explained in the reporting section)\""
    ),
    Titles.small("Scope"),
    Paragraphs.description(
      "CoC applies in any French community space, including"
    ),
    Titles.medium("Community Conduct", idAttr := "community-conduct"),
    Paragraphs.description(
      "We strongly encourage members to assume the best in every interaction, to be open, honest, and empathetic in all communications, and to demonstrate courtesy in every situation. Above all, we must respect each individual's autonomy and free will."
    ),
    Titles.medium("Reporting"),
    Paragraphs.description(
      "The reporting and resolution section may be the most important part of this Code of Conduct. Scala-Fr organizers commit to taking all reports seriously and will do everything possible to resolve situations in the best way for all parties involved."
    ),
    Titles.small("Resolution"),
    Paragraphs.withTitle(
      "Unofficial Resolution",
      "We strongly encourage aggrieved members to speak directly with the accused members using non-violent communication (NVC). Upon request, we will find or appoint a third person to facilitate an unofficial resolution and help mediate the situation."
    ),
    Paragraphs.withTitle(
      "Official Resolution",
      "For an official resolution, aggrieved members must report the incident and request an official intervention. We will designate an arbiter, either from within or outside our organization, who will independently speak with all parties involved, including potential third parties knowledgeable about the situation. The arbiter will then decide on a course of action: either rejecting the incident, acknowledging the situation but determining that our organization cannot move forward on it, or indicating it as a violation and recommending appropriate consequences."
    ),
    Paragraphs.withTitle(
      "Consequences",
      "Violators may be asked to apologize, take training, ... as a condition of continued participation. They may also be banned from our community. We may re-evaluate later a previously imposed consequence."
    ),
    Titles.small("Our pledge"),
    p("We pledge that we will make reasonable efforts on"),
    Lists.flat(
      Lists.Items.titledItem(
        "Enforcement",
        "Enforcing the terms and conditions set forth in CoC."
      ),
      Lists.Items.titledItem(
        "Contact Information",
        "Providing contact information for use in reporting incidents, including an informations on our internal processes"
      )
    ),
    Titles.small("Disclaimers"),
    Paragraphs.withTitle(
      "Important reminders",
      "Under French law, speakers are fully responsible for their content, while organizers must prevent discrimination including but not limited to political views, religion, sexual orientation, or ethnicity – with legal consequences for violations.",
      table(
        tableLayout := "auto",
        className   := "table laws",
        tr(
          th(strong("Law")),
          th(strong("Summary")),
          th(strong("Sanction (up to)"))
        ),
        importantLaws.map(_.render)
      )
    ),
    Paragraphs.withTitle(
      "Criminal Conduct",
      "CoC is not intended to supplant the laws of governing jurisdictions. Criminal conduct of any kind should be immediately reported to relevant authorities and dealt with as recommended by such authorities."
    ),
    Paragraphs.withTitle(
      "Non-Endorsement",
      "In recognition of the pluralistic composition of the community, the community does not endorse the attitudes, beliefs, moral systems, personality traits, political views, preferences, religions, or values of ourselves or of any member of the community. No member ever speaks for or represents the community, and neither do we speak for or represent the community unless we are acting in our official capacities, and then only for the scope of the task at hand."
    ),
    Paragraphs.withTitle(
      "Limitation",
      "CoC is not intended as a legally binding agreement."
    )
  )
