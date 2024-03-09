# Unleashing Scalafix potential with custom rules

Scalafix is a mature tool which was started [nearly 8 years ago by Ólafur Páll Geirsson at the Scala Center](https://www.scala-lang.org/blog/2016/10/24/scalafix.html), originally driven by the need to provide a smooth transition from Scala 2.x to Dotty (now known as Scala 3, which provides its own rewriting features).

Beyond built-in rules execution to organize imports or remove unused code, Scalafix is very extensible thanks to its stable and beginner-friendly API to inspect Scala sources by traversing Scala Abstract Syntax Trees, raise linter errors and/or suggest rewrites to fix them. Like Scalafmt and the Metals language server, it heavily relies on the vibrant Scalameta ecosystem, allowing support of Scala 2.x and Scala 3.

In this talk, we will [demystify AST traversal and semantic information lookup](https://scalacenter.github.io/scalafix/docs/developers/tutorial.html) to [write custom rules](https://scalacenter.github.io/scalafix/docs/developers/local-rules.html) and run them locally and/or on CI, through the sbt-scalafix plugin or via Scala Steward.
