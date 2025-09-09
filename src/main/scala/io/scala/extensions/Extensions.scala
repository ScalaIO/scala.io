package io.scala.extensions

import com.raquo.laminar.api.L.*
import io.scala.Page

extension [T](x: T | Null)
  inline def isEmpty   = x == null
  inline def isDefined = x != null
  inline def nullToOption: Option[T] =
    if x == null then None else Some(x)
  inline def nullMap[U](f: T => U): U | Null =
    if x != null then f(x) else null
  inline def nullFlatMap[U](f: T => U | Null): U | Null =
    if x != null then f(x) else null
  inline def nullGetOrElse[U >: T](default: => U): U =
    if x == null then default else x
  inline def nullOrElse[U >: T](alternative: => U | Null): U | Null =
    if x == null then alternative else x
  inline def nullFold[U](ifNull: => U)(f: T => U): U =
    if x == null then ifNull else f(x)

extension [T](xs: List[T])
  def keepAs[U](f: PartialFunction[T, T & U]): List[T & U] = xs.collect(f)
  def filterWhen(p: Boolean)(f: T => Boolean): List[T]     = if p then xs.filter(f) else xs

extension [T](x: HtmlElement)
  inline def withBinder(page: Page)                              = a(x, Page.navigateTo(page))
  inline def withLink(ref: String, modifiers: Modifier[Anchor]*) = a(x, href := ref, target := "_blank", modifiers)

extension [T <: HtmlElement](e: T)
  def grayOutIf(p: => Boolean): T =
    if (p) then e.amend(className := "grayed-out")
    else e
  def grayOutIf(p: Signal[Boolean]): T =
    e.amend(className.toggle("grayed-out") <-- p)
