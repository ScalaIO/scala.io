package io.scala.views

import com.raquo.laminar.api.L.*

import io.scala.extensions.withLink
import io.scala.modules.elements.Links
import io.scala.modules.elements.Lists
import io.scala.modules.elements.Titles

object VenueView extends SimpleView {

  def body(): HtmlElement = sectionTag(
    className := "container venue",
    Titles("Our Venue"),
    div("For this edition, we are invited by EPITA, on their site 'Kremlin-Bicêtre'"),
    img(
      src := "images/places/epita-kb.jpeg"
    ),
    br(),
    Titles("Access"),
    div(
      "Campus Paris Kremlin-Bicêtre",
      br(),
      "Building 'Voltaire', ",
      a(href := "https://epimap.fr/kb/voltaire/4", "4th floor (epimap)"),
      br(),
      a(
        href := "https://www.google.com/maps/place//data=!4m2!3m1!1s0x47e6718002cb5611:0x581b7c8cd0a77c3e?sa=X&ved=1t:8290&ictx=111",
        "14-16 rue Voltaire",
        br(),
        "FR, 94270 Le Kremlin-Bicêtre"
      )
    ),
    Titles("Accommodations"),
    div(
      display.flex,
      flexDirection.row,
      flexWrap.wrap,
      div(
        Titles.small("Economical"),
        Lists.innerDiscs(
          li(
            Links.highlighted(
              "Ibis budget Porte d'Italie Est",
              href := "https://all.accor.com/hotel/6031/index.fr.shtml?utm_campaign=seo+maps&utm_medium=seo+maps&utm_source=google+Maps"
            )
          ),
          li(
            Links.highlighted(
              "Ibis budget Porte d'Italie Ouest",
              href := "https://all.accor.com/lien_externe.svlt?goto=fiche_hotel&code_hotel=5269&merchantid=seo-maps-FR-5269&sourceid=aw-cen&utm_medium=seo%20maps&utm_source=google%20Maps&utm_campaign=seo%20maps"
            )
          ),
          li(
            Links.highlighted(
              "Break & Home Paris Italie Porte de Choisy",
              href := "https://www.tripadvisor.fr/Hotel_Review-g196570-d19980749-Reviews-Break_Home_Paris_Italie_Porte_de_Choisy-Ivry_sur_Seine_Val_de_Marne_Ile_de_France.html"
            )
          ),
          li(
            Links.highlighted(
              "B&B Hotel Paris Italie Porte de Choisy",
              href := "https://www.tripadvisor.fr/Hotel_Review-g196570-d8240311-Reviews-B_B_HOTEL_Paris_Italie_Porte_de_Choisy-Ivry_sur_Seine_Val_de_Marne_Ile_de_France.html"
            )
          )
        )
      ),
      div(
        Titles.small("Less economical :)"),
        Lists.innerDiscs(
          li(
            Links.highlighted(
              "Novotel Kremlin-Bicêtre",
              href := "https://all.accor.com/lien_externe.svlt?goto=fiche_hotel&code_hotel=5586&merchantid=seo-maps-FR-5586&sourceid=aw-cen&utm_medium=seo%20maps&utm_source=google%20Maps&utm_campaign=seo%20maps"
            )
          ),
          li(
            Links.highlighted(
              "Ibis Styles Paris Météor Avenue d'Italie",
              href := "https://all.accor.com/lien_externe.svlt?goto=fiche_hotel&code_hotel=5586&merchantid=seo-maps-FR-5586&sourceid=aw-cen&utm_medium=seo%20maps&utm_source=google%20Maps&utm_campaign=seo%20maps"
            )
          ),
          li(
            Links.highlighted(
              "Ibis Paris Avenue d'Italie",
              href := "https://all.accor.com/hotel/5543/index.fr.shtml?utm_campaign=seo+maps&utm_medium=seo+maps&utm_source=google+Maps"
            )
          ),
          li(
            Links.highlighted(
              "Mercure Avenue d'Italie",
              href := "https://all.accor.com/hotel/1191/index.fr.shtml?utm_campaign=seo+maps&utm_medium=seo+maps&utm_source=google+Maps"
            )
          ),
          li(
            Links.highlighted(
              "Citadines Apart'hotel Place d'Italie",
              href := "https://www.discoverasr.com/en/citadines/france/citadines-place-ditalie-paris?utm_source=google&utm_medium=maps&utm_campaign=hq-google-maps-alwayson-20240531-all-all-eu-fr-citadinesplaceditalieparis-yext-gbp"
            )
          )
        )
      )
    )
  )
}
