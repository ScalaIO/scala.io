package io.scala.modules.elements

import com.raquo.laminar.api.L.*

object Image:
  inline def lazyLoaded(modifiers: Modifier[HtmlElement]*) =
    img(
      modifiers,
      loadingAttr := "lazy"
    )

  inline def photo(modifiers: Modifier[HtmlElement]*) =
    img(
      loadingAttr := "lazy",
      modifiers,
      className := "photo"
    )
