# Armored type safety with Iron

- Kind: Talk
- Slug: armored-type-safety-with-iron
- Category: Modeling
- confirmed: true
- DateTime: 2024-02-15T11:40
- Room: 0
- Slides: https://scalaio-2024.rlemaitre.com
- Replay: https://www.youtube.com/watch?v=I3BvpzFVBto&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=6&pp=iAQB

## Abstract

```
When designing an application, we often ends up with domain specific types, that all behold constraints that we try to enforce as much as possible : an age is positive, a delivery date can’t be in the past, etc. Modeling the data right is a part of the success of scala and functional programming in general, but it also brings either boilerplate (we have to do again and again validation), or rely purely on conventions.

But there is hope. Meet the Iron library.

Iron is, a type constraint library that allow us to have a safe, declarative and smarter model. It enable us to have a continuous stream of valid data from our API endpoints to the database, and removed a whole class of bugs. Using advanced features like opaque types, inlines and the new macro system, it offer a true 0 cost, 0 dependency library that don’t hamper compile time.

In this talk, we’ll show first the different technique we can use to apply constraints is our domains. Then, we’ll present Iron, its features, extensions, and integrations. We’ll finish by showcasing a fully-integrated constraint-enforcing app.
```

## Speakers

### Raphaël Lemaitre

- photoRelPath: /images/profiles/nantes-2024/rLemaitre.webp
- job: Senior Staff Engineer @ Ledger

#### Links

- [Twitter](https://twitter.com/rlemaitre)
- [Linkedin](https://www.linkedin.com/in/rlemaitre)
- [Github](https://github.com/rlemaitre)
- [Other](https://rlemaitre.com)

#### Bio

```
Bonjour! I'm Raphaël Lemaitre, a seasoned programmer passionate about crafting efficient and sustainable technology. Since embarking on my career journey in 2000, I've been deeply involved in the realm of software development, with a focus on JVM languages like Java and Scala. My technical proficiency includes functional programming, database technologies such as PostgreSQL and Cassandra, along with system design.

Currently, in my role as a Back-end Senior Staff Engineer at Ledger, I concentrate on designing and writing maintainable systems. My goal is to develop software that is not only functional but also robust and adaptable for future advancements. I strive for clean, effective, and scalable solutions that emphasize long-term sustainability in software design.

Beyond my professional pursuits, I'm an enthusiastic golfer, an escape game enthusiast, and a proud father. I firmly believe in the power of continuous learning and sharing knowledge, which led me to create a blog for exchanging ideas, particularly focusing on ADHD strategies for developers.

My interests span from software development to history and technology, where I eagerly engage in conversations that ignite curiosity and foster innovation. Join me in exploring the dynamic landscape of technology, as we seek to build more efficient, maintainable, and impactful technological solutions.
```

### Valentin Bergeron

- photoRelPath: /images/profiles/nantes-2024/vBergeron.webp
- job: Engineering team lead @ Ledger

#### Links

- [Twitter](https://twitter.com/__vberg)
- [Github](https://github.com/vbergeron)

#### Bio

```
Currently Engineering team lead @Ledger, I like Scala, making programming languages, and joking about python
```
