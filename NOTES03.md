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