package io.scala.views

import io.scala.modules.elements.Lists
import io.scala.modules.elements.Titles

import com.raquo.laminar.api.L._

object CoCView extends SimpleView:
  override def body(): HtmlElement = sectionTag(
    className := "container coc",
    Titles.withSub("Scala-FR Code of Conduct (SFCoC)", p("Version 0.1.0")),
    div(),
    p(
      "This Code of Conduct is heavily inspired form ",
      a(href := "https://www.functionalscala.com/code-of-conduct", "Functional Scala")
    ),
    Titles.medium("Introduction"),
    p(
      "The Scala-FR Code of Conduct (SFCoC) dictates the terms and conditions underwhich you can freely participate in the community."
    ),
    p(
      "The purpose of SFCoC is to facilitate an inclusive and diverse community of professionals who productively work toward shared professional goals."
    ),
    p(
      "In service of this purpose, we open the community to all qualified individuals and we require that all members refrain from unprofessional conduct. Except as otherwise noted, we do not impose any ideology onto members, nor do we restrict participation based on people's personal attributes or their conduct in other communities."
    ),
    Titles.medium("Community Conduct"),
    p(
      "We strongly encourage members to assume the best in every interaction; to be open, honest, and empathic in all communication; and to demonstrate politeness and professional courtesy in every situation."
    ),
    p(
      "We welcome any closed group of members to engage in any conduct for which there is explicit and unanimous mutual consent. In the absence of such consent, we impose the following restrictions on conduct, which are further clarified by the Glossary:"
    ),
    Lists.flat(
      li(
        Titles.paragraph("No harassment"),
        " Don't force yourself on other people. They must consent to the interaction"
      ),
      li(Titles.paragraph("No prying"), " People private content/communications are well... private"),
      li(
        Titles.paragraph("No insults"),
        " Don't be rude, it just makes things worse for everybody (not just the people involved)"
      )
    ),
    p(
      "These requirements on conduct apply to members interacting in any Scala.IO / Scala-FR community space, physically as well as virtually (Twitter/X, LinkedIn, Reddit, etc.)"
    ),
    Titles.medium("Universal Conduct"),
    p(
      "We require that all members refrain from unprofessional conduct. This includes, but is not limited to:"
    ),
    Lists.flat(
      li(
        Titles.paragraph("No Doxing"),
        " Do not publish details of members that you learn about while actively participating. It is a criminal act in France"
      ),
      li(
        Titles.paragraph("No retaliation"),
        " Do not retaliate against members for the resolution or the reporting of a violation of the Code of Conduct"
      )
    ),
    p(
      "These requirements on conduct apply to members at all times, even when they are not actively participating in the community."
    ),
    Titles.medium("Reporting"),
    p(
      Titles.paragraph("Unofficial Resolution"),
      "For unofficial resolution, we encourage aggrieved members to speak to accused members directly, using the language of non-violent communication (NVC). At the request of an aggrieved member, we will appoint a mediator who can facilitate an unofficial resolution and serve as a witness for subsequent reporting."
    ),
    p(
      Titles.paragraph("Official Resolution"),
      "For official resolution, aggrieved members must report the incident to us and request an official resolution. We will appoint an arbiter, who will speak individually to all parties, including witnesses, before deciding on a course of action. The course of action will involve rejecting the incident, or accepting it as a violation and imposing a consequence on the violator."
    ),
    p(
      Titles.paragraph("Consequences"),
      "Violators may be asked to apologize or to undergo training, counseling, or mediation as a condition of continued participation. They may also be banned from the community, at the sole discretion of the arbiter. In no case will the consequence exceed banishment, unless the violation is also governed by separate contractual agreements or local laws. We reserve the right to re-evaluate a previously imposed consequence."
    ),
    p(
      Titles.paragraph("Social Rehabilitation"),
      "We will only banish individuals for a pre-determined time (To be determined yet). We reserve the right to reintegrate banned individuals into the community through our own rehabilitation process."
    ),
    p(
      Titles.paragraph("Confidentiality"),
      "We reserve the right to publicly share information about any violation that results in banishment, including personally identifiable information on the violator, limited to name and online identities. In all other cases, we will keep incidents and the identities of aggrieved and accused confidential to the extent this confidentiality is fully respected by all parties."
    ),
    p(
      Titles.paragraph("Reporting Requirements"),
      "Incidents that happen while actively participating must be reported to us within X days by aggrieved members (we will investigate third-party reports at our sole discretion). Incidents involving doxxing or retaliation must be reported to us within Y years of their occurrence. Information that any individual believes may affect our view of the qualifications of an individual may be reported at any time."
    ),
    Titles.medium("Our pledge"),
    p(
      Titles.paragraph("Enforcement"),
      "We pledge that we will make reasonable efforts to enforce the terms and conditions set forth in FSOC, and to hold ourselves to these same standards, thereby setting a positive example for others to follow."
    ),
    p(
      Titles.paragraph(
        "Contact Information"
      ),
      "We pledge to make reasonable efforts to provide contact information for use in reporting incidents, including an estimate of how quickly members can expect to receive a response."
    ),
    p(
      Titles.paragraph(
        "Community Boundaries"
      ),
      "We pledge to make reasonable efforts to provide information on the boundaries of the community, including information on any regions in time or space that are partially or fully exempt from FSOC."
    ),
    p(
      Titles.paragraph(
        "Non-Coercion"
      ),
      "We pledge to make reasonable efforts to never coerce members into revealing private information, making statements, or otherwise requiring actions that are unnecessary for the scope of participating in a given community activity."
    ),
    p(
      Titles.paragraph(
        "Disclosure"
      ),
      "Upon request from an unqualified individual, we pledge to make reasonable efforts to provide a written, anonymized summary to the individual that discloses the rationale and evidence resulting in their status, and we grant them permission to share this disclosure privately or publicly, providing they do so in its entirety."
    ),
    p(
      Titles.paragraph(
        "Unbiased Arbitration"
      ),
      "We pledge to make reasonable efforts to appoint balanced or neutral arbiters for every arbitration, without special relationships to aggrieved or accused, or vested interest in any specific outcome. In the event that unbiased arbitration is impossible or too onerous, we pledge to disclose all known biases."
    ),
    p(
      Titles.paragraph(
        "Merit Transparency"
      ),
      "We pledge to make reasonable efforts to continuously communicate whether the status, authority, or responsibility of individual members is awarded or withheld based on personal attributes that are not relevant to their function in the community."
    ),
    p(
      Titles.paragraph(
        "Amendments"
      ),
      "Any part of FSOC, including but not limited to definitions in the glossary, may be unilaterally amended by us. We pledge to clearly specify and distribute such amendments together with FSOC."
    ),
    Titles.small("Disclaimers"),
    p(
      Titles.paragraph("Disputes of Interpretation"),
      "In the event there is a dispute about the meaning of any term or clause in FSOC, we alone will clarify the intent, independent of how other FSOC communities have clarified the meaning. We pledge to be consistent in our interpretation of any ambiguity."
    ),
    p(
      Titles.paragraph(
        "Criminal Conduct"
      ),
      "FSOC is not intended to supplant the laws of governing jurisdictions, but to impose a set of terms and conditions on community membership for law-abiding individuals. Criminal conduct of any kind should be immediately reported to relevant authorities and dealt with as recommended by such authorities. Notwithstanding the foregoing, if criminal conduct also violates FSOC, we reserve the right to deal with such conduct as described herein."
    ),
    p(
      Titles.paragraph(
        "Non-Endorsement"
      ),
      "In recognition of the pluralistic composition of the community, the community does not endorse the attitudes, beliefs, moral systems, personality traits, political views, preferences, religions, or values of ourselves or of any member of the community. No member ever speaks for or represents the community, and neither do we speak for or represent the community unless we are acting in our official capacities, and then only for the scope of the task at hand."
    ),
    p(
      Titles.paragraph(
        "Limitation of Remedy"
      ),
      "Unless otherwise stated, FSOC is not intended as a legally binding agreement. In the event we fail to uphold any of our pledges as made herein, remedy is limited to a formal public apology and reversal of any consequences (if applicable), unless the breach is governed by separate contractual agreements or local laws."
    ),
    Titles.small("Glossary"),
    p(
      Titles.paragraph("Accused"),
      "We define accused to be any member who has been attributed a violation by an aggrieved member."
    ),
    p(
      Titles.paragraph("Actively Participating"),
      "We define actively participating to be the state of being a member who is located within the boundaries of the community."
    ),
    p(
      Titles.paragraph("Aggrieved"),
      "We define aggrieved to be any member who claims that one or more of the following holds:",
      Lists.innerDiscs(
        li("Harassment. Someone has harassed them"),
        li("Prying. Someone has violated their privacy."),
        li("Insulting. Someone has insulted them or a group they belong to."),
        li("Doxxing. Someone has doxxed them."),
        li("Retaliating. Someone has retaliated against them.")
      ),
    ),
    p(
      Titles.paragraph("Arbiter"),
      "We define arbiter to be one or more persons designated by us to resolve an incident report."
    ),
    p(
      Titles.paragraph("Communication"),
      "We define communication to be any transmission of information between people, including but not limited to verbal, written, electronic, pictorial, gestural, and tactile."
    ),
    p(
      Titles.paragraph("Community"),
      "We define the community to be a group of people with a shared professional interest that exists at some location (online or geographical) and within some span of time. The boundaries of the community are dictated by us, but may not extend in space or time to any place where we do not have the actual or legal ability to impose FSOC-prescribed consequences on violators."
    ),
    p(
      Titles.paragraph("Community Qualification Criteria"),
      "We define community qualification criteria to be published and publicly accessible criteria that are useful and objectively relevant to the professional goals of the community. This criteria may include objectively verifiable criteria (such as capability to perform a task), or unverifiable criteria. The satisfaction of all unverifiable criteria, including requirements on the attitudes, beliefs, moral systems, personality traits, political views, preferences, religions, and values of an individual, must be determined solely by the individual's own confessions, and may not be determined by third-party statements, except insofar as they are witnesses for the individual's own confessions."
    ),
    p(
      Titles.paragraph("Community Sabotage"),
      "We define community sabotage to be any conduct that does have or is intended to have the effect of harming the professional goals of the community, including but not limited to attempting to defund or blackball the community, attempting to disrupt or diminish the activities of the community, and authoring, spreading, or endorsing libel or slander about the community; or encouraging others to engage in the preceding conduct. Explicitly excluded from this definition is communication of truthful information regarding the community and truthful criticism on the failure of the community to achieve its professional goals."
    ),
    p(
      Titles.paragraph("Conduct"),
      "We define conduct to include all and only observable actions of people, and to explicitly exclude all personal attributes."
    ),
    p(
      Titles.paragraph("Criminal Conduct"),
      "We define criminal conduct to be any conduct that is illegal under the laws of the governing jurisdiction. Explicitly excluded from criminal conduct is any illegal conduct in which those directly and immediately impacted by the conduct are legally adults and do not consider themselves to be victims (including recreational use of drugs, consensual prostitution, failure of adults to wear seat belts, and the like)."
    ),
    p(
      Titles.paragraph("Destructive Individual"),
      "We define destructive individual to be any individual that we intentionally label destructive, and for whom one or more of the following hold:"
    ),
    Lists.innerDiscs(
      li(
        "We believe the individual is likely to engage in criminal conduct, and we or other individuals have contacted authorities to report the conduct that motivated this belief."
      ),
      li(
        "We believe the individual is likely to engage in community sabotage, and the individual has participated in previous community sabotage or there is credible evidence suggesting the individual is planning to engage in future community sabotage."
      ),
      li(
        "We believe the individual is likely to engage in professional sabotage, and the individual has participated in previous professional sabotage or there is credible evidence suggesting the individual is planning to engage in future professional sabotage."
      )
    ),
    p(
      Titles.paragraph("Doxxing"),
      "We define doxxing to be the unauthorized publication of any personal attributes of identified members learned while actively participating and not previously known to the public."
    ),
    p(
      Titles.paragraph("Harassment"),
      "We define harassment to be any interaction with someone who does not consent to the interaction. For interactions of a professional nature, you may assume consent for the first interaction, until the recipient communicates otherwise (examples include handshakes, looking at someone who is speaking, providing feedback). For all other interactions, you must assume non-consent until the person clearly and unambiguously communicates otherwise (examples include persistent gaze at someone who is not speaking, sexual interactions of any kind)."
    ),
    p(Titles.paragraph("Incident"), "We define incident to be any possible infringement of FSOC."),
    p(
      Titles.paragraph("Interaction"),
      "We define interaction to be any one-on-one communication, physical contact (with person or property), close proximity, or persistent gaze. Explicitly excluded from this definition is opt-in, broadcast-based communication within the community."
    ),
    p(
      Titles.paragraph("Members"),
      "We define a member of the community to be any qualified individual who participates in the community."
    ),
    p(
      Titles.paragraph("Personal Attribute"),
      "We define personal attribute to be an individual as a whole, or the presence or absence of any individual characteristic, including but not limited to age, BDSM or kink preferences, biological sex, body modifications, body shape, body size, ethnic origin, gender identity, gender expression, gender orientation, intellectual disability, physical appearance, physical disability, physical strength, presented appearance, race, sexual identity, sexual orientation, sexual preferences, and skin color; attitudes, beliefs, moral systems, personality traits, political views, preferences, religions, and values; current or historical personal facts, including address, citizenship, lifestyle choices, experience, job title, membership in groups or communities, nationality, preferred name, real name, relationship status, relationship type, salary, sexual behavior, socio-economic status, and type and extent of education."
    ),
    p(
      Titles.paragraph("Professional Sabotage"),
      "We define professional sabotage to be any conduct that does have or is intended to have the effect of harming a member's career, including but not limited to attempting to no-platform, blackball, or fire a member; stealing or taking credit for the work of a member; authoring, spreading, or endorsing libel or slander about the member; or encouraging others to engage in the preceding conduct. Explicitly excluded from professional sabotage is all harmful but truthful communication that is exclusively motivated by and communicated in terms of the member's inability to fulfill their professional duties."
    ),
    p(
      Titles.paragraph("Prying"),
      "We define prying to be any unauthorized attempt (whether legal or illegal) to ascertain the communication of semi-private forms of communication (including phone calls, direct messages, texts, private conversations), or to ascertain content on private devices or private channels of communication belonging to other members, where unauthorized means the individual prying does not have explicit permission from the owner of said device or by the authors of said communication."
    ),
    p(
      Titles.paragraph("Qualified Individual"),
      "We define qualified individual to be any individual for whom the following hold:",
      Lists.innerDiscs(
        li("The individual is not a destructive individual."),
        li("The individual is not currently banned from the community."),
        li("The individual satisfies all community qualification criteria.")
      ),
    ),
    p(
      Titles.paragraph("Qualifications"),
      "We define the qualifications of an individual to be the minimal set of properties sufficient to determine whether the individual is a qualified individual."
    ),
    p(
      Titles.paragraph("Retaliation"),
      "We define retaliation to be the imposition of any negative consequence on a member for their unofficial resolution or reporting of an incident, or for their participation in such resolution or reporting (including participation as arbiter), where negative consequence is defined as any consequence that does or is intended to have the effect of harming the member's career."
    ),
    p(
      Titles.paragraph("Insulting"),
      "We define insulting as any communication of the idea that any personal attribute of any person is inferior, including all personal insults and ad hominem. Explicitly excluded from insulting is all communication of the idea that objective, falsifiable statements espoused by a member are inconsistent, unsupported, or falsified, as well as communication of the idea that some personal attributes offer an advantage or disadvantage in obtaining specified measurable objectives."
    ),
    p(
      Titles.paragraph("Violation"),
      "We define violation to be any actual infringement of FSOC as determined by an arbiter."
    ),
    p(
      Titles.paragraph("Violator"),
      "We define violator to be any accused member who has broken the terms and conditions of FSOC, as determined by an arbiter."
    ),
    p(
      Titles.paragraph("Unprofessional Conduct"),
      "We define unprofessional conduct to be any conduct that qualifies as harassing, prying, or insulting for individuals who are actively participating, and any conduct that qualifies as doxxing or retaliating."
    ),
    p(
      Titles.paragraph("Unqualified Individual"),
      "We define unqualified individual to be any individual that is not a qualified individual."
    ),
    p(
      Titles.paragraph("We"),
      "We define we to be those who have the actual or legal ability to impose FSOC-prescribed consequences on violators within the physical or virtual boundaries of the community."
    )
  )