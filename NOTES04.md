# Operators
=> Decorators  

## Operators - Pipeline
You can have multiple operators, you can build a pipeline of operators.  

### List of Operators (not exhaustive)
* filter
* map
* log
* take
* takeWhile
* takeUntil

## Operator Handle
Handle behaves like filter + map  


## doOnNext
* Immutability is good. (But it does not mean mutation is bad)
* Functional Programming prefers pure functions (with no side effects)!
  * Prefer pure functions!
* Your Entity objects are Mutable objects!

```
//pseudo code

//traditional
var customer = this.repositry.findById(123).get();
customer.setAge(10);
this.repositpry.save(customer);

//reactive
this.repositpry.findById(123)
    .doOnNext(customer -> customer.setAge(10))
    .flatMap(this.repositpry::save);
```

# Hot/Cold Publishers

## Cold Publishers
In the publisher subscriber concept, we had said that nothing happens until the subscriber subscribes.  
Example a Netflix movie. One or more person can watch the same movie.  
The data is not shared.  

## Hot Publisher
We will have one data producer for all the subscriber, in some cases we do not even need a subscriber, the producer can start producing items on its own.  
Example a TV channel producing content.  
Shared.  

### Summary - Hot Publisher:
* share
* publish().refCount(1) -> at least 1 subscriber. It will start when there is at least 1 subscriber and stop when there is 0 subscriber. We can re-subscribe
* publish().autoConnect(1) -> same as above. It does NOT stop when subscribers leave
* publish().autoConnect(0) -> Real hot publisher - no subscriber required
* replay(n).autoConnect(0) -> Cache the emitted item for late subscribers


# Context
It is simillar to ThreadLocal  

* Metadata about the request
* Simple Key/value pairs
  * An immutable Map!
* Cross cutting concerns
  * Authentication
  * Rate limiting
  * Monitoring/Tracing
  * ..
  * ..