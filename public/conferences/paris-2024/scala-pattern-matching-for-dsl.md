# Repurposing Scala's Pattern Matching for Deeply Embedded DSLs

- Kind: Talk
- Slug: scala-pattern-matching-for-deeply-embedded-dsl
- Category: Language
- confirmed: true
- DateTime: 2024-11-07T10:45:00
- Room: B

## Abstract

```
Could we reuse Scala’s linguistic features, including variable binding and pattern matching, for describing e.g. durable workflows? In particular, could we obtain a reified description of such workflows, one that we could analyze, serialize, or render graphically? Can we get nested pattern matching on user-defined domain-level ADTs?

Although Scala-Virtualized, an experimental branch of the Scala compiler, gave us means to do so a decade ago, it never made it to the mainline Scala compiler.

In this talk, I’m going to present an alternative technique to repurpose Scala’s pattern matching for deeply embedded DSLs, one that does not require changes to the compiler. Additionally, the presented library solution (from Libretto) comes with translation to point-free representation, which not only avoids the trouble of managing variable bindings, but also makes non-exhaustive pattern matches unrepresentable! And all of that without macros.

The technique is used in the Libretto project for pattern matching on domain-level ADTs. However, I am going to demonstrate it on a simpler, demo DSL for defining durable workflows. Thus, as a bonus, attendees will get an unorthodox approach to durable workflows that’s not based on replaying the event log.
```

## Speakers

### Tomas Mikula

- photoRelPath: /images/profiles/paris-2024/tMikula.webp
- job: Lead Scala developer

#### Links

- [Twitter](https://twitter.com/tomas_mikula)
- [Linkedin](https://www.linkedin.com/in/tomasmikula)
- [Github](https://github.com/TomasMikula)
- [Other](https://continuously.dev)

#### Bio

```
Software developer. Using existing tools to solve business problems by day. Working on better tools by night. Interested in functional programming, metaprogramming, DSLs. Trying to do my bit on improving programming for everyone. Author of Libretto.
```
