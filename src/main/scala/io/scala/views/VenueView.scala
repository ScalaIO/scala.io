package io.scala.views

import io.scala.modules.elements.Line
import io.scala.modules.elements.Title

import com.raquo.laminar.api.L.{*, given}

object VenueView extends SimpleView {

  def body(): HtmlElement = sectionTag(
    className := "container venue",
    Title("Our Venue"),
    div(
      className := "paragraph",
      p(
        """Situated in the vibrant center of Nantes, """,
        a(className := "basic-link", href := "https://icilundi.fr/lieux/le-palace/", "the Palace", target := "_blank"),
        """ is an ideal hub for collaboration
          |and knowledge sharing. It offers a comfortable and nurturing work environment tailored for startups,
          | teams from major corporate innovation departments, accelerators, educational institutions, and investors.
          |""".stripMargin
      ),
      p("""
          |As with every edition of Scala.IO, we have a focus on nurturing the exchange between participants, for this edition with have two equally sized spaces.
          |one large conference space and on large community space.
          |""".stripMargin)
    ),
    div(
      className := "conference-space",
      div(
        div(
          h2("Conference space"),
          p(
            "Our conference space is a large auditorium with a large capacity called 'la salle des coffres' (it was formerly a bank vault)."
          )
        ),
        div(
          className       := "hero",
          backgroundImage := "url(../images/places/icilundi1.webp)",
          div(
            className := "overlay",
            h2(className := "title", "The Vault")
          )
        )
      ),
      div(
        h2("Community space"),
        p("""Our community space is a large open space, full of light, called the Atrium. It is the perfect place to exchange ideas and network. The community space
            |will be the place where you can meet our sponsors, speakers and other participants. It is also the place where you can enjoy a coffee, a snack or a drink.
            |They are sited area where you can engage in longer conversations.
            |""".stripMargin),
        p(
          className := "small-note",
          """Note : we plan to have a setup like the edition in 2013, with some retransmission available in the community space."""
        ),
        div(
          className       := "hero",
          backgroundImage := "url(../images/places/icilundi2.webp)",
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
        )
      )
    ),
    Title("Access to the conference"),
    p(
      """
        |The conference is located in the heart of Nantes, France,
        |""".stripMargin,
      a(
        href   := "https://maps.app.goo.gl/bGSepESpcX1VJKST6",
        target := "_blank",
        "4 Rue Voltaire."
      )
    ),
    h2("Recommended hotels"),
    p(
      """
        |We recommend the following hotels:
        |""".stripMargin,
      ul(
        className := "basic-list",
        li(
          u(
            a(
              href := "https://www.booking.com/hotel/fr/de-france-nantes.en-gb.html?aid=318615&label=English_France_EN_FR_21457883905-tvCAVDJnGUWD7jawmA84BQS640819002915%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atidsa-912298701041%3Alp9055556%3Ali%3Adec%3Adm%3Aag21457883905%3Acmp339900145&sid=480d696252c6328f249922e049f38bda&all_sr_blocks=5389301_98058694_0_2_0;checkin=2024-02-14;checkout=2024-02-16;dest_id=-1454460;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=5389301_98058694_0_2_0;hpos=1;matching_block_id=5389301_98058694_0_2_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=5389301_98058694_0_2_0__28900;srepoch=1705295547;srpvid=289f2496dc760058;type=total;ucfs=1&#hotelTmpl",
              target := "_blank",
              "Oceania l'Hôtel de France"
            )
          )
        ),
        li(
          u(
            a(
              href := "https://www.booking.com/hotel/fr/maisons-du-monde-hotel-and-suites.en-gb.html?aid=318615&label=English_France_EN_FR_21457883905-tvCAVDJnGUWD7jawmA84BQS640819002915%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atidsa-912298701041%3Alp9055556%3Ali%3Adec%3Adm%3Aag21457883905%3Acmp339900145&sid=480d696252c6328f249922e049f38bda&dest_id=-1454460&dest_type=city&room1=A%2CA&;group_adults=2;group_children=0;no_rooms=1;checkin=2024-02-14;checkout=2024-02-16;highlighted_blocks=5176024_88552707_2_2_0;atlas_src=sr_iw_btn;from_sr_map=1;from=searchresults;ucfs=1",
              target := "_blank",
              "Maisons du Monde"
            )
          )
        ),
        li(
          u(
            a(
              href := "https://www.booking.com/hotel/fr/cholet.en-gb.html?aid=318615&label=English_France_EN_FR_21457883905-tvCAVDJnGUWD7jawmA84BQS640819002915%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atidsa-912298701041%3Alp9055556%3Ali%3Adec%3Adm%3Aag21457883905%3Acmp339900145&sid=480d696252c6328f249922e049f38bda&dest_id=-1454460&dest_type=city&room1=A%2CA&;group_adults=2;group_children=0;no_rooms=1;checkin=2024-02-14;checkout=2024-02-16;highlighted_blocks=5440202_88552713_2_2_0;atlas_src=sr_iw_btn;from_sr_map=1;from=searchresults;ucfs=1",
              target := "_blank",
              "Hotel Voltaire Opera"
            )
          )
        )
      )
    ),
    h2("Coming from Paris"),
    p(
      """
        |If you're traveling from Paris, you have the option to take a train from Montparnasse station directly to Nantes.
        |
        |""".stripMargin
    ),
    p(
      className := "small-note",
      """
        |Note : Some participants will be arriving by train (Train Number 8863) at 8:54 am on Thursday morning. We are currently exploring options to expedite the transfer from the train station to the conference venue.
        |If this applies to you, we kindly request that you
        |""".stripMargin,
      a(className := "basic-link", href := "mailto:contact@scala.io", "send us an email for further coordination.")
    ),
    h2("Arriving from Nantes Train Station"),
    h3("Walking (25 minutes)"),
    h3("By Bus (7 minutes + 4 minutes walk downhill)"),
    ul(
      className := "basic-list",
      li("Reach the south exit of the train station (also called Bus/Taxi exit);"),
      li("In front of the exit, purchase a bus ticket and board the C3 line in the direction \"Armor\";"),
      li("Get off at the \"Copernic\" bus stop;"),
      li("Walk down to the venue;")
    ),
    h3("By tramway (9 minutes + 5 minutes walk uphill)"),
    ul(
      className := "basic-list",
      li("Reach the north exit of the train station (also called the \"Nantes Centre\" exit);"),
      li("In front of the exit, cross the railway and purchase a ticket at the dispenser;"),
      li("Board Tramway Line 1 in the direction of \"François Mitterrand/Jamet\";"),
      li("Get off at the tramway stop 'Médiathèque';"),
      li("Walk up to the venue;")
    ),
    //
    h2("Arriving from Nantes Atlantique Airport"),
    p("The airport is unusually close to the city center (less than 10km):"),
    ul(
      className := "basic-list",
      li("20 minutes by car/taxi (depending on traffic);"),
      li("40 minutes by public transportation (bus + tramway);")
    ),
    h3("With public transportation"),
    ul(
      className := "basic-list",
      li(
        "Reach the airport shuttle \"Navette Aéroport\" departure place, located in the middle of the outside area behind the taxi lane;"
      ),
      li("Buy a ticket at the dispenser (10€ per passenger);"),
      li("Get off at the \"Hôtel Dieu\" stop;"),
      li("Board the C3 line in the direction \"Armor\";"),
      li("Get off at the \"Copernic\" bus stop"),
      li("Walk down to the venue;")
    ),
    h3("With taxi or private driver"),
    ul(
      className := "basic-list",
      li("A taxi from their lane is around 40€ for the transfer;"),
      li("Uber, Bolt, and some other private driver services operate in the area of Nantes;")
    ),
    p(
      className := "small-note",
      """
        |Note: Every public transportation mode (tramway, bus, and airport shuttle) requires you to buy a ticket; there are dispensers around their stops. It's currently 1.80€ per passenger for 1 hour and 10€ for a ticket from the airport (you don't need another ticket to use the C3 when traveling from the airport).
        |""".stripMargin
    ),
    Line.separator(vMargin = 32, width = 80, height = 8),
    div(
      className := "venue-map-container",
      iframe(
        className := "venue-map",
        src := "https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d1355.095279080701!2d-1.5657394!3d47.2128536!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4805ed0c3af02b81%3A0xfe8747f744fd5b97!2sLe%20Palace%20_icilundi!5e0!3m2!1sfr!2sfr!4v1701301592089!5m2!1sfr!2sfr",
        loadingAttr := "lazy"
      )
    )
  )
}
