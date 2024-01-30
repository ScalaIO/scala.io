# Armored type safety with Iron

When designing an application, we often ends up with domain specific types, that all behold constraints that we try to enforce as much as possible : an age is positive, a delivery date can’t be in the past, etc. Modeling the data right is a part of the success of scala and functional programming in general, but it also brings either boilerplate (we have to do again and again validation), or rely purely on conventions.

But there is hope. Meet the Iron library.

Iron is, a type constraint library that allow us to have a safe, declarative and smarter model. It enable us to have a continuous stream of valid data from our API endpoints to the database, and removed a whole class of bugs. Using advanced features like opaque types, inlines and the new macro system, it offer a true 0 cost, 0 dependency library that don’t hamper compile time.

In this talk, we’ll show first the different technique we can use to apply constraints is our domains. Then, we’ll present Iron, its features, extensions, and integrations. We’ll finish by showcasing a fully-integrated constraint-enforcing app.
