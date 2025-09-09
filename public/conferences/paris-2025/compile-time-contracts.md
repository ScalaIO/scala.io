# Compile-Time Contracts & Fiber-Safe Data Pipelines: Scala’s Effect System in Action

- Kind: Talk
- Slug: compile-time-contracts-fiber-safe-data-pipelines
- Category: Data
- confirmed: true

## Abstract

```
What if your data platform stopped relying on configuration-driven / metadata-driven and postmortems - and instead enforced correctness, traceability, and effect boundaries at compile time?

In this talk, we’ll walk through how we built a production-ready data pipeline archetype system in Scala. We used Giter8 templates to scaffold pipelines that are contract-driven, type-safe, effectful, and pluggable - all enforced through the Scala type system and effect libraries.

🔧 You’ll see how: • 📦 Giter8 templates bootstrap consistent, compile-time safe projects • 🛡️ Refined types validate configuration before runtime • ✅ Cats ValidatedNel catches multi-rule violations in DQ checks • 🔌 Type classes enable pluggable validation and ingestion • ⚙️ ZIO Layers and Cats Effect offer fiber-safe orchestration • 🚀 Trait-based runners switch between Spark, Flink, and Kafka • 📊 Data Quality and custom rules enforce data contract quality at runtime • 🧠 Experimental: Kyo - tracks multiple effects via intersection types and Caprese - capture-checking tracks capabilities

🧪 We’ll demo a real pipeline: scaffolded from template, validated with contracts, and executed via a modular runtime - showing how functional design meets engineering scale.

Whether you’re a platform engineer, data architect, or Scala practitioner, this talk showcases how to build systems that scale with correctness, traceability, and developer delight.
```

## Speakers

### Vitthal Mirji

- photoRelPath: /images/profiles/paris-2025/vMirji.webp
- job: Staff Data Engineer @ Walmart Global Tech India

#### Links

- [Twitter](https://twitter.com/whoami_vim)
- [Linkedin](https://www.linkedin.com/in/vitthal10)
- [Github](https://github.com/vim89)
- [Other](https://vitthalmirji.com/?i=1)

#### Bio

```


Vitthal Mirji is a Staff Data Engineer with over 12 years of experience crafting robust data platforms across retail, finance, and supply chain domains. He specializes in building type-safe, effect-aware, and contract-enforced data pipelines - with a deep focus on functional design, runtime reliability, and developer productivity.

His journey includes architecting large-scale data frameworks, automating pipeline archetypes, leading engineering teams, and contributing to open-source projects like Apache Spark-HBase and Databricks XML. A strong advocate of scala, Vitthal is passionate about bringing structure, correctness, and simplicity into complex systems - without sacrificing performance.

When he’s not scaling pipelines, he’s scaling spice levels in his kitchen. A curious explorer of everything from investment strategies to vintage watches, cars, long drives, tennis matches, whisky nosing, Maharashtrian culture (Maharashtra - A state in country India), and blogging life lessons - Vitthal brings creativity and curiosity into everything he does.

His favorite member of One Direction? - Well, still waiting for one to upstream a pull request.. or the one who composes side-effect-free functions, of course.
```
