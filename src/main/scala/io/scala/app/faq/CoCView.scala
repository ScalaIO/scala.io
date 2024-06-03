package io.scala.app.faq

import io.scala.modules.elements.Links
import io.scala.modules.elements.Lists
import io.scala.modules.elements.Paragraphs
import io.scala.modules.elements.Titles
import io.scala.views.SimpleView

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.RenderableText

object CoCView extends SimpleView:
  override def body(): HtmlElement = sectionTag(
    className := "container coc",
    Titles.withSub("Scala-FR Code of Conduct (SFCoC)", p("Version 0.0.1"),
    ),
    Paragraphs.description(
      "This Code of Conduct is heavily inspired form ",
      Links.highlighted(
        href     := "https://www.functionalscala.com/code-of-conduct",
        nameAttr := "Functional Scala",
        "Functional Scala"
      )
    ),
    Titles.medium("Introduction"),
    Paragraphs.description(
      "The Scala-FR Code of Conduct (SFCoC) dictates the terms and conditions underwhich you can freely participate in the community."
    ),
    Paragraphs.description(
      "The purpose of SFCoC is to facilitate an inclusive and diverse community of professionals who productively work toward shared professional goals."
    ),
    Paragraphs.description(
      "In service of this purpose, we open the community to all qualified individuals and we require that all members refrain from unprofessional conduct. Except as otherwise noted, we do not impose any ideology onto members, nor do we restrict participation based on people's personal attributes or their conduct in other communities."
    ),
    Titles.medium("Community Conduct", idAttr := "community-conduct"),
    Paragraphs.description(
      "We strongly encourage members to assume the best in every interaction; to be open, honest, and empathic in all communication; and to demonstrate politeness and professional courtesy in every situation."
    ),
    Paragraphs.description(
      "We welcome any closed group of members to engage in any conduct for which there is explicit and unanimous mutual consent. In the absence of such consent, we impose the following restrictions on conduct (which aren't standards for everyone, unfortunately):"
    ),
    Lists.flat(
      li(
        Titles.paragraph("No harassment"),
        "Don't force yourself on other people. They must consent to the interaction"
      ),
      li(Titles.paragraph("No prying"), "People private content/communications are well... private"),
      li(
        Titles.paragraph("No insults"),
        "Don't be rude, it just makes things worse for everybody (not just the people involved)"
      ),
      li(
        Titles.paragraph("No doxing"),
        "Do not publish details of members that you learn about. It is a criminal act in France"
      ),
      li(
        Titles.paragraph("No retaliation"),
        "Do not retaliate against members for the resolution or the reporting of a violation of the SFCoC"
      )
    ),
    Paragraphs.description(
      "These requirements on conduct apply to members at all times, physically as well as virtually (Twitter/X, LinkedIn, Reddit...), even when they are not actively participating in the community."
    ),
    Titles.medium("Reporting"),
    Titles.small("Resolution"),
    Paragraphs.description(
      Titles.paragraph("Unofficial Resolution"),
      "For unofficial resolution, we encourage aggrieved members to speak to accused members directly, using the language of non-violent communication (NVC). At the request of an aggrieved member, we will appoint a mediator who can facilitate an unofficial resolution and serve as a witness for subsequent reporting."
    ),
    Paragraphs.description(
      Titles.paragraph("Official Resolution"),
      "For official resolution, aggrieved members must report the incident to us and request an official resolution. We will appoint an arbiter, who will speak individually to all parties, including witnesses, before deciding on a course of action. The course of action will involve rejecting the incident, or accepting it as a violation and imposing a consequence on the violator."
    ),
    Paragraphs.description(
      Titles.paragraph("Consequences"),
      "Violators may be asked to apologize or to undergo training, counseling, or mediation as a condition of continued participation. They may also be banned from the community, at the sole discretion of the arbiter. In no case will the consequence exceed banishment, unless the violation is also governed by separate contractual agreements or local laws. We reserve the right to re-evaluate a previously imposed consequence."
    ),
    Paragraphs.description(
      Titles.paragraph("Confidentiality"),
      "We reserve the right to publicly share information about any violation that results in banishment, including personally identifiable information on the violator, limited to name and online identities. In all other cases, we will keep incidents and the identities of aggrieved and accused confidential to the extent this confidentiality is fully respected by all parties."
    ),
    Titles.small("Our pledge"),
    p("We pledge that we will make reasonable efforts on"),
    Lists.flat(
      li(
        Paragraphs.description(
          Titles.paragraph("Enforcement"),
          "Enforcing the terms and conditions set forth in SFCoC, and to hold ourselves to these same standards, thereby setting a positive example for others to follow."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Contact Information"),
          "Providing contact information for use in reporting incidents, including an estimate of how quickly members can expect to receive a response."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Community Boundaries"),
          "Providing information on the boundaries of the community, including information on any regions in time or space that are partially or fully exempt from SFCoC."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Non-Coercion"),
          "Never coerce members into revealing private information, making statements, or otherwise requiring actions that are unnecessary for the scope of participating in a given community activity."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Disclosure"),
          "Upon request from an unqualified individual, we pledge to make reasonable efforts to provide a written, anonymized summary to the individual that discloses the rationale and evidence resulting in their status, and we grant them permission to share this disclosure privately or publicly, providing they do so in its entirety."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Unbiased Arbitration"),
          "Appointing balanced or neutral arbiters for every arbitration, without special relationships to aggrieved or accused, or vested interest in any specific outcome. In the event that unbiased arbitration is impossible or too onerous, we pledge to disclose all known biases."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Merit Transparency"),
          "Continuously communicate whether the status, authority, or responsibility of individual members is awarded or withheld based on personal attributes that are not relevant to their function in the community."
        )
      ),
      li(
        Paragraphs.description(
          Titles.paragraph("Amendments"),
          "Any part of SFCoC, including but not limited to definitions in the glossary, may be unilaterally amended by us. We pledge to clearly specify and distribute such amendments together with SFCoC."
        )
      )
    ),
    Titles.small("Disclaimers"),
    Paragraphs.description(
      Titles.paragraph("Disputes of Interpretation"),
      "We alone can clarify ambiguities in the SFCoC. We pledge to be consistent in our interpretation of any ambiguity and update the SFCoC to remove ambiguities as they are found."
    ),
    Paragraphs.description(
      Titles.paragraph("Criminal Conduct"),
      "SFCoC is not intended to supplant the laws of governing jurisdictions, but to impose a set of terms and conditions on community membership for law-abiding individuals. Criminal conduct of any kind should be immediately reported to relevant authorities and dealt with as recommended by such authorities. Notwithstanding the foregoing, if criminal conduct also violates SFCoC, we reserve the right to deal with such conduct as described herein."
    ),
    Paragraphs.description(
      Titles.paragraph("Non-Endorsement"),
      "In recognition of the pluralistic composition of the community, the community does not endorse the attitudes, beliefs, moral systems, personality traits, political views, preferences, religions, or values of ourselves or of any member of the community. No member ever speaks for or represents the community, and neither do we speak for or represent the community unless we are acting in our official capacities, and then only for the scope of the task at hand."
    ),
    Paragraphs.description(
      Titles.paragraph("Limitation of Remedy"),
      "Unless otherwise stated, SFCoC is not intended as a legally binding agreement. In the event we fail to uphold any of our pledges as made herein, remedy is limited to a formal public apology and reversal of any consequences (if applicable), unless the breach is governed by separate contractual agreements or local laws."
    ),
    Titles.small("Glossary"),
    Paragraphs.description(
      Titles.paragraph("Arbiter"),
      "We define arbiter to be one or more persons designated by us to resolve an incident report."
    ),
    Paragraphs.description(
      Titles.paragraph("Community"),
      "We define the community to be a group of people with a shared professional interest that exists at some location (online or geographical) and within some span of time. The boundaries of the community are dictated by us, but may not extend in space or time to any place where we do not have the actual or legal ability to impose SFCoC-prescribed consequences on violators."
    ),
    Paragraphs.description(
      Titles.paragraph("Community Sabotage"),
      "We define community sabotage to be any conduct that does have or is intended to have the effect of harming the professional goals of the community, including but not limited to attempting to",
      Lists.innerDiscs(
        li("Defunding / blackballing the community"),
        li("Attempting to disrupt or diminish the activities of the community"),
        li(
          "Authoring, spreading, or endorsing libel or slander about the community; or encouraging others to engage in the preceding conduct"
        )
      ),
      "Explicitly excluded from this definition is communication of truthful information regarding the community and truthful criticism on the failure of the community to achieve its professional goals."
    ),
    Paragraphs.description(
      Titles.paragraph("Conduct"),
      "We define conduct to include all and only observable actions of people, and to explicitly exclude all personal attributes."
    ),
    Paragraphs.description(
      Titles.paragraph("Destructive Individual"),
      "We define destructive individual to be any individual that we intentionally label destructive, and for whom one or more of the following we believe to hold:"
    ),
    Lists.innerDiscs(
      li(
        "Likeliness to engage in criminal conduct, and we or other individuals have contacted authorities to report the conduct that motivated this belief."
      ),
      li(
        "Likeliness to engage in community sabotage, and the individual has participated in previous community sabotage or there is credible evidence suggesting the individual is planning to engage in future community sabotage."
      ),
      li(
        "Likeliness to engage in professional sabotage, and the individual has participated in previous professional sabotage or there is credible evidence suggesting the individual is planning to engage in future professional sabotage."
      )
    ),
    Paragraphs.description(
      Titles.paragraph("Harassment"),
      "We define harassment to be any interaction with someone who does not consent to the interaction. For interactions of a professional nature, you may assume consent for the first interaction, until the recipient communicates otherwise (examples include handshakes, looking at someone who is speaking, providing feedback). For all other interactions, you must assume non-consent until the person clearly and unambiguously communicates otherwise (examples include persistent gaze at someone who is not speaking, sexual interactions of any kind)."
    ),
    Paragraphs
      .description(Titles.paragraph("Incident"), "We define incident to be any possible infringement of SFCoC."),
    Paragraphs.description(
      Titles.paragraph("Members"),
      "We define a member of the community to be any qualified individual who participates in the community."
    ),
    Paragraphs.description(
      Titles.paragraph("Personal Attribute"),
      "We define personal attribute to be an individual as a whole, or the presence or absence of any individual characteristic, including but not limited to age, BDSM or kink preferences, biological sex, body modifications, body shape, body size, ethnic origin, gender identity, gender expression, gender orientation, intellectual disability, physical appearance, physical disability, physical strength, presented appearance, race, sexual identity, sexual orientation, sexual preferences, and skin color; attitudes, beliefs, moral systems, personality traits, political views, preferences, religions, and values; current or historical personal facts, including address, citizenship, lifestyle choices, experience, job title, membership in groups or communities, nationality, preferred name, real name, relationship status, relationship type, salary, sexual behavior, socio-economic status, and type and extent of education."
    ),
    Paragraphs.description(
      Titles.paragraph("Professional Sabotage"),
      "We define professional sabotage to be any conduct that does have or is intended to have the effect of harming a member's career, including but not limited to",
      Lists.innerDiscs(
        li("Attempting to no-platform, blackball, or fire a member"),
        li("Stealing or taking credit for the work of a member"),
        li(
          "Authoring, spreading, or endorsing libel or slander about the member; or encouraging others to engage in the preceding conduct."
        )
      ),
      "Explicitly excluded from professional sabotage is all harmful but truthful communication that is exclusively motivated by and communicated in terms of the member's inability to fulfill their professional duties."
    ),
    Paragraphs.description(
      Titles.paragraph("Qualified Individual"),
      "We define qualified individual to be any individual for whom the following hold:",
      Lists.innerDiscs(
        li("Not a destructive individual."),
        li("Not currently banned from the community."),
        li("Satisfies all community qualification criteria.")
      )
    ),
    Paragraphs.description(
      Titles.paragraph("Qualifications"),
      "We define the qualifications of an individual to be the minimal set of properties sufficient to determine whether the individual is a qualified individual."
    ),
    Paragraphs.description(
      Titles.paragraph("Violation"),
      "We define violation to be any actual infringement of SFCoC as determined by an arbiter."
    ),
    Paragraphs.description(
      Titles.paragraph("We"),
      "We define we to be those who have the actual or legal ability to impose SFCoC-prescribed consequences on violators within the physical or virtual boundaries of the community."
    )
  )
