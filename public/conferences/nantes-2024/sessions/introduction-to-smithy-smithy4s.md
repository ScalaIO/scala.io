# Introduction to Smithy/Smithy4s

- Kind: Talk
- Slug: introduction-to-smithy-smithy4s
- Category: Cloud
- confirmed: true
- Slides:
- Replay: https://www.youtube.com/watch?v=UorOxZTJDxg&list=PLjkHSzY9VuL96myavOIICS-x6yVyAMPjg&index=10&pp=iAQB

## Abstract

```
AWS (Amazon Web Services), one of the biggest cloud providers, provides hundreds of services, and offers SDKs in multiple languages to interact with these services. These public-facing services are backed by tens of thousands of services internal to the AWS platform. In order to streamline the development process of such a behemoth, AWS relies on code-generation.

Smithy is the culmination of ~14 years of iterations in the field of code-generation. It is an elegant declarative language that enables defining data types, operations and services in a clear and concise manner. The unique aspect of Smithy is that protocol concerns (transport, serialisation) are abstracted away in an extensible annotation-based mechanism. This means that Smithy can be used to describe things like rest/json services, but an infinite amount of other things.

Smithy4s is a Scala code-generator that feeds off Smithy files. It is unique in that it retains the protocol-agnostic nature of Smithy :the code-generator is not biased towards any protocol or serialisation mechanism. Users can generate Scala code from Smithy to get case classes and interfaces, that can be wired in runtime-interpreters in an opt-in fashion, to derive http services, or CLIs, or even pure-Scala AWS SDKs. Developers could provide support for specific protocols as third party libraries, without ever having to touch code-generation.

This talk will serve as an introduction to the Smithy IDL, and a demo of what is possible with Smithy4s
```

## Speakers

### Olivier Mélois

- photoRelPath: /images/profiles/speakers/oMelois.webp
- job: Principal Engineer @ Disney Streaming Services

#### Links

- [Linkedin](https://www.linkedin.com/in/olivier-mélois-99234bbb)
- [Github](https://github.com/Baccata)

#### Bio

```
My name is Olivier Mélois. I have been using Scala as my main language for since 2013. I maintain weaver-test and smithy4s, and have contribute to many libraries and tools, from ammonite to cats-effect.

After leaving in Manchester (UK) for a while, I moved back to the countryside in the south of France, and now work remotely full-time in the countryside. I try to grow vegetables.
```
