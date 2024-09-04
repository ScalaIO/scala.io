package io.scala.extensions

extension [T](x: T | Null)
  inline def isEmpty = x == null
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

extension [T](xs: Seq[T])
  def keepAs[U](f: PartialFunction[T, T & U]): Seq[T & U] = xs.collect(f)