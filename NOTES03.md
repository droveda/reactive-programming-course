# Flux
It emits 0, 1 ... N Items  
Followed by an onComplete / onError  
It can deliver infinite items  

## Flux (not exhaustive)
* Just
* fromIterable
* fromArray
* fromStream

## Mono/Flux - Are they Data Structures?
* List<T>, Set<T>...
  * represent the data in the memory!
  * storage
* Flux<T>, Mono<T>
  * represent a tunnel / pipe through which **data can be transfered** from one place to another.


## Flux - defer
// how to delay the execution?  
Flux.defer(() -> Flux.fromIterable(createList()));  


# Flux - create / generate

## Flux.create -> FluxSink
* It is designed to be used when we have a single subscriber
* FluxSink is thread safe. We can share it with multiple threads
* We can keep on emitting data into the sink w/o worrying about downstream demand
* FluxSink will deliver everything to Subscriber safely.

## Flux Generate With State
```
Flux.Generate(
  () -> someObject, // invoked once
  (someObject, sink) -> {
    ...
    ...
    return someObject;
  }
)


Flux.Generate(
  () -> someObject, // invoked once
  (someObject, sink) -> {
    ...
    ...
    return someObject;
  },
  someObject -> close //invoked once
)
```

### Summary - Flux - CreateGenerate
* Create
  * Accepts a **Consumer<FluxSink<T>>**
  * Consumer is invoked only once
  * Consumer can emit 0..N elements immediatly without worrying about downstream demand
  * Thread-safe
* Generate
  * Accepts a **Consumer<SynchronousSink<T>>**
  * Consumer is invoked again and again
  * Consumer can emit only on element. Downstream demend is respected
  * N/A (Thread-safe)