# Direct-style Scala using Ox

The arrival of Java 21 prompted a re-evaluation of the asynchronous programming approaches that we are currently using. In Scala, this covers both Future-based code and the ‘functional’ IOs, as known from cats-effect or ZIO.

Once we have an asynchronous runtime with direct syntax as part of the VM, what are the benefits of the “wrapped” approach? And what are the costs that we can now avoid?

The Ox library explores direct-style Scala, offering tools for managing concurrency and resiliency. Although still far from complete, functionalities such as structured concurrency, high-level concurrency operators, and go-like channels with stream-like APIs are available today.

The presentation will begin with a code-first overview of what Ox currently offers. Then, we will compare the Ox approach with using library-level asynchronous runtimes. We will focus on safety, developer experience, “reactiveness”, type-level guarantees, and features offered by each approach.
