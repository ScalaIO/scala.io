# Unwrapping IO: is it a path that you want to follow?

The arrival of Java 21 prompted a re-evaluation of the asynchronous programming approaches that we are currently using. In Scala, this covers both Future-based code and the 'functional' IOs, as known from cats-effect or ZIO.

Once we have an asynchronous runtime with direct syntax as part of the VM, what are the benefits of the "wrapped" approach? And what are the costs that we can now avoid?

We'll explore what direct-style Scala, represented by Ox, might be able to offer in the space of managing concurrency and resiliency. This will be in contrast to functional effect systems, represented by ZIO.

We will compare both the low-level aspects, as well as take a look at structured concurrency and high-level concurrency operators. We’ll examine safety, developer experience and type-level guarantees offered by each approach.
