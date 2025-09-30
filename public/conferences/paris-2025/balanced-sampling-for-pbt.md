# Balanced sampling as a tool for useful PBT random tree generation

- Kind: Talk
- Slug: balanced-sampling-for-pbt
- Category: Tools & Ecosystem
- confirmed: true

## Abstract

```
Have you ever tried to randomly generate abstract syntax trees (ASTs) for property-based testing? If so, you’ve probably run into this phenomenon: your generator seems to work fine, but most of the results are boring or meaningless.

A recent paper by Florent Koechlin and Pablo Rotondo (Université Paris-Est Gustave-Eiffel) offers a new way to fix this. It’s called BST-like sampling, and it helps bias the kinds of trees you generate. Why does that matter? Because if you pick trees uniformly, giving each one the same chance, you often end up with misleading biases. For example, most of your regular expressions might match nothing. Arithmetic expressions often just reduce to zero. Boolean expressions almost always simplify to just True or False.

In this talk, we’ll show how we hit this exact problem when generating Regular Type Expressions (RTEs), a kind of advanced regular expression used in symbolic finite automata. At first, our test cases looked okay, but when we dug deeper, most of them boiled down to trivial patterns that weren’t useful.

Once we started using BST-like sampling, the results got much better. Trivial cases showed up way less often, and we started getting more interesting and diverse test cases.

If you care about better property-based testing, especially for symbolic or structured data, come check it out. This technique might save you a lot of time and frustration.

```

## Speakers

### Jim Newton

#### Links

#### Bio