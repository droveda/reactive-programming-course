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