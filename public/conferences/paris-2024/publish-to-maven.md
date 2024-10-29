# Joyful & secure publishing to Maven Central!

- Kind: Talk
- Slug: joyful-and-secure-publishing-to-maven-central
- Category: Tools & Ecosystem
- confirmed: true
- DateTime: 2024-11-07T14:30:00
- Room: A

## Abstract

```
The Guardian is a hefty open-source contributor, publishing over 40 open-source Scala libraries to Maven Central - some specific to us (like our [content-api-scala-client](https://github.com/guardian/content-api-scala-client)), and others more generally applicable, like [play-googleauth](https://github.com/guardian/play-googleauth) or [etag-caching](https://github.com/guardian/etag-caching). Teams responsible for those libraries need to be able to publish updates at will, but that can be both difficult and dangerous:

- **Difficult**, because every new developer on a team needs release credentials, and those credentials are laborious to set up and manage per developer. Shared-credentials and CI-release can help fix that - but how can we keep those release credentials **secure**, when they’re used by so many projects, with so many third-party dependencies?
- **Dangerous**, because seemingly minor library updates can be startlingly binary incompatible, and humans are just terrible at seeing that coming. Subtle binary incompatibility can sneak past pre-production unit & integration testing, only to completely crash production systems in unpredictable circumstances. There’s no point in releasing new library versions if we’re too scared to update to them! How can we make that safe?
This talk will show how the Guardian’s new GitHub Action release workflow makes the difficult easy, and the dangerous safe. I’ll explain why a single JVM process shouldn’t be responsible for an entire release! We’ll talk about how reducing config - that is, the amount of config per repository - sells the work of adoption, and how we can capitalise on a whole shopping list of improvements (coming from sbt’s versionScheme, sbt-version-policy, and more) whose benefit–cost ratios greatly improve when you’re applying them collectively at the level of your organisation, rather than one repository at time.

[gha-scala-library-release-workflow](https://github.com/guardian/gha-scala-library-release-workflow) is a [*reusable*](https://docs.github.com/en/actions/using-workflows/reusing-workflows) GitHub workflow, meaning it can be used by other GitHub organisations - it’s already being used by [Scanamo](https://github.com/scanamo/scanamo) - so if you’re publishing to Maven Central, there’s a good chance it can help you too!
```

## Speakers

### Roberto Tyley

- photoRelPath: /images/profiles/paris-2024/rTyley.webp
- job: Principal Engineer @ The Guardian

#### Links

- [Twitter](http://twitter.com/rtyley)
- [Linkedin](https://www.linkedin.com/in/robertotyley)
- [Other](https://www.theguardian.com/profile/roberto-tyley)

#### Bio

```
Roberto Tyley is Principal Engineer at the Guardian, creator of the BFG Repo-Cleaner, the CI/CD tool Prout, and many more open-source Java/Scala projects!

He also loves karaoke, anyone up for karaoke afterwards?
```
