# Contravariance: intuition building from first principles

## Summary

Contravariance throws many developers off the first time they run into it. This talk will equip the audience with an intuitive understanding of contravariance and the tools for working out from first principles whether a type parameter should be covariant or contravariant.

## Objective

The goal of this talk is to help the audience build an intuition for contravariance from first principles, instead of relying on memorised shortcuts such as “input type parameters tend to be contravariant and output type parameters covariant”.

## Prerequisites

This talks is designed for beginner to intermediate Scala developers. Attendees need to be familiar with subtyping and typeclasses. An understanding of covariance would be beneficial but is not required as we’ll cover it in the talk.

## Talk outline

We’ll begin by working with an Animal type hierarchy and some PetRescue and PetClinic typeclasses. By working out what can be substituted for what, we’ll begin building up intuition for covariance and contravariance. We’ll then test this intuition by working through a second example involving JsonDecoder and JsonEncoder typeclasses.
