# Mono

https://github.com/vinsguru/java-reactive-programming-course  


Reactive Stream is a specification and reactor is a library and one of the implementations.  
**Publisher** is one of the interfaces in the reactive stream, for which the reactor library provider two differente implementations.  
1. Mono<T>
   1. It emits **0 or 1 item**, Followed by an onComplete / onError
2. Flux<T>
   1. It emits **0,1 .. N** items, Followed by an onComplete / onError


## Why Mono & Flux?
Mono is useful when you need to get only 1 or 0 data. It happens for example when you query a client table by ID.  
example:
```

interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
    
    Flux<Customer> findByFirstName(string firstName);

    Mono<Customer> findById(Long id);

}

```


* Flux
  * Stream of messages
  * Backpressure (Producer emits too much data which consumer can not handle)
  * Many additional methods specific to handle stream processing
* Mono
  * No Stream!
  * No Back pressure!
  * A light weight publisher
  * Request -> Response Model