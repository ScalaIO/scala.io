# Unwrapping IO: is it a path that you want to follow?

- Kind: Keynote
- Slug: unwrapping-io
- Category: Effects
- DateTime: 2024-02-16T17:00
- Room: 0
- Slides: https://adamw.github.io/unwrapping-io
- Replay: https://www.youtube.com/watch?v=qR_Od7qbacs&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=1&pp=iAQB

## Abstract

```
The arrival of Java 21 prompted a re-evaluation of the asynchronous programming approaches that we are currently using. In Scala, this covers both Future-based code and the 'functional' IOs, as known from cats-effect or ZIO.

Once we have an asynchronous runtime with direct syntax as part of the VM, what are the benefits of the "wrapped" approach? And what are the costs that we can now avoid?

We'll explore what direct-style Scala, represented by Ox, might be able to offer in the space of managing concurrency and resiliency. This will be in contrast to functional effect systems, represented by ZIO.

We will compare both the low-level aspects, as well as take a look at structured concurrency and high-level concurrency operators. We’ll examine safety, developer experience and type-level guarantees offered by each approach.
```

## Speakers

### Adam Warski

- photoRelPath: /images/profiles/nantes2024/aWarski.webp
- job: Co-founder @ SoftwareMill
- confirmed: true

#### Links

- [Twitter](http://twitter.com/adamwarski)
- [Linkedin](https://www.linkedin.com/in/adamwarski)
- [Other](http://softwaremill.com)

#### Bio

```
I am one of the co-founders of SoftwareMill, where I code mainly using Scala and other interesting technologies. I am involved in open-source projects, such as sttp, MacWire, Quicklens, ElasticMQ and others. I have been a speaker at major conferences, such as JavaOne, LambdaConf, Devoxx and ScalaDays.

Apart from writing closed- and open-source software, in my free time I try to read the Internet on various (functional) programming-related subjects. Any ideas or insights usually end up with a blog (https://softwaremill.com/blog)
```
