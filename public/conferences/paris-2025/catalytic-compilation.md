# Catalytic Compilation : A modern take on safe spark 

- Kind: Talk
- Slug: catalytic-compilation
- Category: Tools & Ecosystem
- confirmed: true

## Abstract

```
Spark SQL is a wonderful piece of technology, enabling concise expression of scalable and resilient data pipelines. It has been a cornerstone of the ecosystem for many years, and has brought many people (including myself) to use and love Scala.

Unfortunately, today, its user experience in Scala is pretty much the same as what we have with the Python interface: strings everywhere, runtime failures, and no help from the compiler. Tools like Frameless (or more recently Iskra) may ease the pain, but they come with disadvantages such as slow compilation times, mandatory case class definitions and derivation, and the burden of having to define everything the way the library expects.

In this talk, we are taking a different path. Instead of forcing SQL and the DataFrame API to fit into Scala, why not go the other way around? By enabling the use of Catalyst’s engine and analyzer at compile time, we can do the opposite: apply compile-time validation to SQL queries and table definitions, giving us insightful error messages, compile-time execution plans, and intermediate schemas - all while supporting all Spark SQL features by design.

We will also explore the advanced metaprogramming techniques used to achieve this, and see how powerful the Scala 3 macro toolkit is for modern programming.

Let’s make Scala the best language to make your data pipelines production-ready!
```

## Speakers

### Valentin Bergeron

- photoRelPath: /images/profiles/paris-2024/vBergeron.webp
- job: 

#### Links

[Github](https://github.com/vbergeron)

#### Bio

``````