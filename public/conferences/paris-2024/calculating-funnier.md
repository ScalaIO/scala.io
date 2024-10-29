# Calculating is funnier than guessing

- Kind: Talk
- Slug: calculating-is-funnier-than-guessing
- Category: Algebra
- confirmed: true
- DateTime: 2024-11-07T16:30:00
- Room: B

## Abstract

```
We’ve all written functional interpreters. They are easy to write—one usually proceeds by structural induction on the datatype. There’s almost no opportunity for errors. But, interpreters can be slow and fragile. One remedy is to write a compiler that turns syntax into a machine language, and a virtual machine to execute a script written in that language. The thought of writing a compiler may conjure up the two-steps process to draw an owl in your mind, a sort of magical manifestation that only a select few are capable of, who have been passed down the location of the little black notebook with all the secret tricks. But I’m here to tell you there is no black notebook, and writing compilers is not so much about guessing as much as it is about calculating via a rigorous step by step process.

In this talk I will start by dusting off your knowledge of equational reasoning and structural induction. I’ll start by showing how to define a denotational semantics for a simple expression language Expr of booleans. That will be done by writing an evaluation interpreter that brings an expression to its boolean value. Then I will show you how to prove that negation is an involution. Past that point, we are ready to attack the cool stuff and proceed to the derivation of a compiler, a machine language and a stack machine for Expr. This will be a nice refresher on equational reasoning by starting from a correctness equation, deriving each case starting from the left side of the equation and proceed step by step to arrive at the right side of the equation.

Expr is very simple though, in real life we seldom face such simplistic cases. To dispel any doubt, we’ll tackle a more ambitious case in the name of [the ZPure datatype](https://zio.dev/zio-prelude/zpure) from the zio-prelude library.
```

## Speakers

### Regis Kuckaertz

- photoRelPath: /images/profiles/paris-2024/rKuckaertz.webp
- job: Principal Engineer @ Pirum

#### Links

- [Linkedin](https://www.linkedin.com/in/regiskuckaertz)
- [Twitter](https://twitter.com/regiskuckaertz)

#### Bio

```
Regis is a software engineer currently working on post-trade services in the securities lending business. His work involves working at the bottom of the stack, designing systems from first principles, applying research and development to make teams more efficient, teach people to exercise their creativity and lead them to enjoy spending 1/3 of their day at work.

When not sitting at his desk, Regis can be found playing with his dogs, cooking a meal for his wife or exploring the coastal regions of England, waiting for creativity to strike.
```
