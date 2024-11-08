# Migrating Gallia to Scala 3: the good, the bad, and the very good.

- Kind: Short
- Slug: migrating-gallia-to-scala-3
- Category: ToolsEcosystem
- confirmed: true
- Slides: https://scala.io/slides/2024/gallia-migration.pdf
- Replay: https://www.youtube.com/watch?v=DzjvFx5YYik&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=14&pp=iAQB

## Abstract

```
Following my [previous talk](https://www.youtube.com/watch?v=hl4GiFNCUv8) at Scala Days last year, two major pieces of feedback emerged for [Gallia](https://github.com/galliaproject/gallia-core): one was the need for performance improvements (for another talk) and the other was migration to Scala 3. The strong demand for the latter surprised me as I did not expect the community to have adopted it so quickly, and so successfully. I therefore decided to tackle the migration first, albeit not without some hesitation and some serious apprehension.

In this talk I will highlight my journey tackling this endeavor in concrete terms (where does one start?), lessons I've learned for other projects that will also need migration, and the positive/negative aspects of the overall process. As the title of the presentation suggests, I would rate my overall experience as quite positive. This was unexpected because while Scala 3 looked great on paper, this kind of migration can be an extremely frustrating experience irrespective of the context.

I will briefly introduce how certain features [worked before](https://github.com/galliaproject/gallia-core/blob/f8101f4244e0231c5517a5509c88352d5f57c335/reflect/src/main/scala-2/gallia/reflect/lowlevel/TypeLeafParserRuntime2.scala) (e.g. the now defunct `scala.reflect.{api,runtime}`) prior to showing the new approach(s), as well as why I chose to handle certain problems [the way I did](https://github.com/galliaproject/gallia-core/blob/f8101f4244e0231c5517a5509c88352d5f57c335/reflect/src/main/scala-3/gallia/reflect/macros3/TypeLeafParserMacro3.scala), notably when it came to reflection and macros.
```

## Speakers

### Anthony Cros

- photoRelPath: /images/profiles/nantes-2024/aCros.webp
- job: Software Architect @ Self-employed

#### Links

- [Twitter](https://twitter.com/anthony_cros)
- [Linkedin](https://www.linkedin.com/in/anthony-cros-3587b063)
- [Other](http://anthonycros.com)

#### Bio

```
I am an independent software engineer/architect with 20 years of professional coding experience (see LinkedIn). My focus is on data transformations (especially big data), domain modeling, software architecture in general, and bioinformatics.

My past experiences primarily include work in the biomedical field, with positions held at the Ontario Institute for Cancer Research, the Hospital for Sick Children in Toronto, the Children's Hospital of Philadelphia, the BF2I lab (INSA Lyon), and the bacteriology lab at UCBL (Lyon). I also worked for a short period of time in the telecom industry, although a less exciting venture for my tastes.

The above experiences included a great many situations in which data had to be modeled with the most extreme care, and processed with just the right trade-offs of practicality and performance. In the biomedical field in particular, errors in judgment on these aspects can have real consequences for patient care, whether indirectly via portals used by researchers (e.g. International Cancer Genome Consortium data portal), or directly in the case of internal hospital systems (typically kept private).

All these experiences led me to develop my own tool, Gallia, with the aim of streamlining the process of data transformation, and which draws on all the issues I've encountered using existing tools (as well as their strengths!). I'm also developing a tool intended to help with the modeling aspect - with an emphasis on semantics - and hope to publish it as well at some point in the future.
```
