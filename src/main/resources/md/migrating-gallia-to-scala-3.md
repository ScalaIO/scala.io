# Migrating Gallia to Scala 3: the good, the bad, and the very good

Following my [previous talk](https://www.youtube.com/watch?v=hl4GiFNCUv8) at Scala Days last year, two major pieces of feedback emerged for [Gallia](https://github.com/galliaproject/gallia-core): one was the need for performance improvements (for another talk) and the other was migration to Scala 3. The strong demand for the latter surprised me as I did not expect the community to have adopted it so quickly, and so successfully. I therefore decided to tackle the migration first, albeit not without some hesitation and some serious apprehension.

In this talk I will highlight my journey tackling this endeavor in concrete terms (where does one start?), lessons I've learned for other projects that will also need migration, and the positive/negative aspects of the overall process. As the title of the presentation suggests, I would rate my overall experience as quite positive. This was unexpected because while Scala 3 looked great on paper, this kind of migration can be an extremely frustrating experience irrespective of the context.

I will briefly introduce how certain features [worked before](https://github.com/galliaproject/gallia-core/blob/f8101f4244e0231c5517a5509c88352d5f57c335/reflect/src/main/scala-2/gallia/reflect/lowlevel/TypeLeafParserRuntime2.scala) (e.g. the now defunct `scala.reflect.{api,runtime}`) prior to showing the new approach(s), as well as why I chose to handle certain problems [the way I did](https://github.com/galliaproject/gallia-core/blob/f8101f4244e0231c5517a5509c88352d5f57c335/reflect/src/main/scala-3/gallia/reflect/macros3/TypeLeafParserMacro3.scala), notably when it came to reflection and macros.