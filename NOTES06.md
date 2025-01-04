# Repeat & Retry

* publisher -> subscriber
  * no items after
    * onComplete
    * onError

## Repeat/Retry
* repeat
  * Resubscribe after complete signal
* retry
  * Resubscribe after error signal


# Sinks
* Publishers So far...
  * Mono.fromSupplier(...)
  * Flux.range(...)
  * Flux.generate(...)
  * Flux.create(...)
  * Flux.interval(...)
  * ...
  * ...

The sinks are like both producer and subscribers  
Traditional Programming  


## Sink Types
* one               -> mono (N subscribers)
* many - unicast    -> Flux (1 Subscriber)  - Subs can join later if required. messages will be stored in the memort
* many - multicast  -> Flux (N subscribers) - late subscribers can not see the messages
* many - replay     -> Flux (N subscribers) - with replay of all values to late subscribers

# Unit Testing - StepVerifier

# Microservices
1. Spring Web + Java Virtual Thread
2. Reactive Microservice with Spring WebFlux
   1. Knowledge of Reactive programming is required

## Miroservices - Backend communication
1. Spring Web - Sync/Rest
2. Spring WebFlux - Reactive/Rest
3. gRPC
4. RSocket (reactive)
5. Kafka (event-driven async)

## Microservice - Frontend Communication?
1. GraphQL
2. Redis (Added for caching)

## What about resiliency?
Integration and Resilient Design Patterns for Microservices Architecture

## What about Testing?
Selenium * Jenkins  

## What about Deployment & Orchestration
* Docker
* Kubernetes

# Other courses
https://www.udemy.com/user/vinoth-selvaraj/