# Unleashing Scalafix potential with custom rules

- Kind: Talk
- Slug: unleashing-scalafix-potential
- Category: ToolsEcosystem
- confirmed: true
- Slides: https://scala\.io/conferences/nantes-2024/slides/2024/scalafix.pdf
- Replay: https://www.youtube.com/watch?v=qexJYvo2EwY&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=15&pp=iAQB

## Abstract

```
Scalafix is a mature tool which was started [nearly 8 years ago by Ólafur Páll Geirsson at the Scala Center](https://www.scala-lang.org/blog/2016/10/24/scalafix.html), originally driven by the need to provide a smooth transition from Scala 2.x to Dotty (now known as Scala 3, which provides its own rewriting features).

Beyond built-in rules execution to organize imports or remove unused code, Scalafix is very extensible thanks to its stable and beginner-friendly API to inspect Scala sources by traversing Scala Abstract Syntax Trees, raise linter errors and/or suggest rewrites to fix them. Like Scalafmt and the Metals language server, it heavily relies on the vibrant Scalameta ecosystem, allowing support of Scala 2.x and Scala 3.

In this talk, we will [demystify AST traversal and semantic information lookup](https://scalacenter.github.io/scalafix/docs/developers/tutorial.html) to [write custom rules](https://scalacenter.github.io/scalafix/docs/developers/local-rules.html) and run them locally and/or on CI, through the sbt-scalafix plugin or via Scala Steward.
```

## Speakers

### Brice Jaglin

- photoRelPath: /images/profiles/speakers/bJaglin.webp
- job: Staff Engineer @ Swile

#### Links

- [Linkedin](https://www.linkedin.com/in/bjaglin)
- [Github](https://github.com/bjaglin)

#### Bio

```
I am the main maintainer of Scalafix since 2020, after I discovered the potential of using custom rules in a modular monolith at work and started contributing to the project to make that easier. Currently a Staff Engineer at Swile, I no longer use Scala on a day-to-day basis but I continue to maintain Scalafix on my free time as I have yet to find such a powerful tool in another ecosystem!
```
