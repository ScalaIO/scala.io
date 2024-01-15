package io.scala.views

import com.raquo.laminar.api.L.{*, given}
import io.scala.Lexicon
import io.scala.modules.elements.Line
import io.scala.modules.elements.Title

object VenueView extends SimpleView {

  def body(withDraft: Boolean): HtmlElement = sectionTag(
    className := "container venue",
    Title("Venue"),
    p(
      """
        |Situated in the vibrant center of Nantes, the Palace stands as an ideal hub for collaboration and knowledge sharing. It offers a comfortable and nurturing work environment tailored for startups, teams from major corporate innovation departments, accelerators, educational institutions, and investors.
        |""".stripMargin
      ,
      className := "catch-phrase"
    ),
    Line(margin = 55),
    h2("Conference space"),
    div(
      className := "hero",
      backgroundImage := "url(https://icilundi.fr/wp-content/uploads/2023/09/04/capture-decran-2023-09-04-a-16.27.11.png)",
      div(
        className := "overlay",
        div(
          h2(className := "title", "The Vault"),
          p(
            className := "description",
            """
              |The Vault, an auditorium  perfect place to host conferences".
              |""".stripMargin
          )
        )
      )
    ),
    h2("Community space"),
    div(
      className := "hero",
      backgroundImage := "url(https://icilundi.fr/wp-content/uploads/2021/05/06/le-palace-atrium-scaled.jpg)",
      div(
        className := "overlay",
        div(
          h2(className := "title", "The Atrium"),
          p(
            className := "description",
            """
              |The Atrium, a large open space. Perfect for exchanging ideas and networking.
              |""".stripMargin
          )
        )
      )
    ),
    Line.separator(vMargin = 32, width = 80, height = 8),

    Title("Access to the conference"),

    p(
      """
        |The conference is located in the heart of Nantes, France,
        |""".stripMargin,
      a(
        href := "https://maps.app.goo.gl/bGSepESpcX1VJKST6",
        target := "_blank",
        "4 Rue Voltaire."
      ),
    ),

    h2("Recommended hotels"),
    p(
      """
        |We recommend the following hotels:
        |""".stripMargin,

      ul(
        li(a(
          href := "https://www.booking.com/hotel/fr/de-france-nantes.en-gb.html?aid=318615&label=English_France_EN_FR_21457883905-tvCAVDJnGUWD7jawmA84BQS640819002915%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atidsa-912298701041%3Alp9055556%3Ali%3Adec%3Adm%3Aag21457883905%3Acmp339900145&sid=480d696252c6328f249922e049f38bda&all_sr_blocks=5389301_98058694_0_2_0;checkin=2024-02-14;checkout=2024-02-16;dest_id=-1454460;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=5389301_98058694_0_2_0;hpos=1;matching_block_id=5389301_98058694_0_2_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=5389301_98058694_0_2_0__28900;srepoch=1705295547;srpvid=289f2496dc760058;type=total;ucfs=1&#hotelTmpl",
          target := "_blank",
          "Oceania l'Hôtel de France"
        )),
        li(a(
          href := "https://www.booking.com/hotel/fr/maisons-du-monde-hotel-and-suites.en-gb.html?aid=318615&label=English_France_EN_FR_21457883905-tvCAVDJnGUWD7jawmA84BQS640819002915%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atidsa-912298701041%3Alp9055556%3Ali%3Adec%3Adm%3Aag21457883905%3Acmp339900145&sid=480d696252c6328f249922e049f38bda&dest_id=-1454460&dest_type=city&room1=A%2CA&;group_adults=2;group_children=0;no_rooms=1;checkin=2024-02-14;checkout=2024-02-16;highlighted_blocks=5176024_88552707_2_2_0;atlas_src=sr_iw_btn;from_sr_map=1;from=searchresults;ucfs=1",
          target := "_blank",
          "Maisons du Monde"
        )),
        li(
          a(
            href := "https://www.booking.com/hotel/fr/cholet.en-gb.html?aid=318615&label=English_France_EN_FR_21457883905-tvCAVDJnGUWD7jawmA84BQS640819002915%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atidsa-912298701041%3Alp9055556%3Ali%3Adec%3Adm%3Aag21457883905%3Acmp339900145&sid=480d696252c6328f249922e049f38bda&dest_id=-1454460&dest_type=city&room1=A%2CA&;group_adults=2;group_children=0;no_rooms=1;checkin=2024-02-14;checkout=2024-02-16;highlighted_blocks=5440202_88552713_2_2_0;atlas_src=sr_iw_btn;from_sr_map=1;from=searchresults;ucfs=1",
            target := "_blank",
            "Hotel Voltaire Opera"
          )
        )
      )
    ),

    h2("Coming from Paris"),
    p(
      """
        |If you are coming from Paris, you can take the train from Montparnasse station to Nantes.
        |
        |""".stripMargin
    ),

    //Note : some participants will be taking the train (8863) arriving at 8:54 am on thursday morning.

    /*div(
      className := "venue-map-container",
      iframe(
        className := "venue-map",
        src := "https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d1355.095279080701!2d-1.5657394!3d47.2128536!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4805ed0c3af02b81%3A0xfe8747f744fd5b97!2sLe%20Palace%20_icilundi!5e0!3m2!1sfr!2sfr!4v1701301592089!5m2!1sfr!2sfr",
        loadingAttr := "lazy"
      )
    )*/
  )

  override def title: String = "Venue"
}
