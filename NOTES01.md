# Reactive Programming

## Basics

### Process
* An instance of a computer program
* It includes code, resource (allocated by the OS like memory, sockets, etc...)
* Heavy-weight

### Thread
* Part of a process
  * A process can contain one or more threads
* Theads within a process can share the memory space

### Scheduler / CPU / Thread
Modern CPUs can have multiple cores. Each core can be seen as a processor  


### Heap vs Stack
* Heap memory is where we store objects. we dynamically create like ArrayList, HashMap, whatever you create.
* Stack memory will contain the local vaiables, object references and the function called information.

Each and every thread will have its own stack memory like this.  


### Problem Statement!
* CPU is very expensive ($$)
  * Try to use CPU as much as you can!
* Often times in Microservices architectures, we have many network calls. But network calls are slow. Threads will be idle. So people create too many threads to make use of CPU.
* Thread is an expensive resource
  * Expensive = Heavy/Consumes Memory
* We need a mechanism to make these network calls more efficient without wasting System resources!!

### IO - Inboud / Outbound
* **Sync** -> (I will can the isurance company and wait in the line for an answer)
* **Async** -> (I will delegate the job to call the insurance company and wait for the response to my friend)
* **Non-blocking** -> (I will give my phone number to the attendant and he will call me back when he has the answer. This way I can watch a moive or read a book, I am not blocked)
* **Non-blocking + async** -> (I will give my friend's phone number)

### Communication Patterns

* Why not Virtual Thread?
  * Do I need Reactive Programming? Can I just use Virtual Threads!?

* Communication Patterns
  * Request -> Response
  * Request -> Streaming Response (Stock price updates)
  * Streaming Request -> Response (Video streaming...)
  * Bidirectional-streaming (Online Games)

Traditional Programming is Pull based!  
Request -> Response  


### What is Reactive Programming?

* Reactive Streams Specification
  * https://www.reactive-streams.org/
  * Reactive Streams in an initiative to provide a standard for asynchronous stream processing with non-blockiing back pressure. This encopasses efforts aimed at runtime environments (JVM and JavaScript) as well as network protocols.
* What is reactive programming?
  * A programming paradigm designed to process streams of messages in a non-blocking and asynchronous manner, while handling backpressure.
  * It is based on **Observer design pattern**!
  * For IO calls
  * Reactive Programming complements Object-Oriented Programming by providing powerful tools and abstractions to handle asynchronous IO calls and manage complex data flows in modern applications.

### Reactive Streams - Implementation
* Akka streams
* rxJava2
* **Reactor**
  * Spring Webflux
  * R2DBC (Postgres, MySQL, H2...)
  * Redis
  * Elasticsearch
  * Mongo
  * Kafka
  * RabbitMQ
  * ...

### Publisher / Subscriber Communication
* Step 1 - Subscriber wants to connect
* Step 2 - Publisher calls onSubscribe
* Step 3 - Subscription (The relationship is stabilished)
* Step 4 - Publisher pushes data via onNext
* Step 5 - OnComplete (Publisher will NOT send anything after onComplete)
* Step 6 - onError (Publisher will NOT send anything after onError)


### Terminologies
* Publisher
  * Source
  * Observable
  * Upstream
  * Producer
* Subscriber
  * Sink
  * Observer
  * Downstream
  * Consumer
* Processor
  * Operator


### Rules in Reactive Programming
* Subscriber has to subscribe and request for producer to produce items! Till then produces does NOT produce anything. Be lazy as much as possible.
* Subscriber can cancel anytime. (Producer should NOT produce data after that)
* Producer will produce items via onNext
* Producer will call onComplete after emitting 0..N data
* Producer will can onError in case of issues
* Producer will NOT invoke anything after onComplete/onError. Subscription request/cancel will have no effect after that.