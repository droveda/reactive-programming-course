# Threading & Schedulers

## Schedulers
* Scheduler -> Thread pool
  * boundedElastic -> Network / time-consuming / blocking operations
  * parallel -> CPU intensive tasks
  * single -> A single dedicated thread for one-off tasks
  * immediate -> Current thread


## Operators for Scheduling
* subscribeOn -> for upstream
* publishOn -> for downstream

## Schedulers != Parallel-execution
* All the operations are always executed in sequential
* Data is processed one by one by a thread from the ThreadPool for a Subscriber
* Schedulers.parallel() - is a thread pool for CPU tasks, Does NOT mean parallel execution.

## Parallel-execution
* parallel
* runOn


# Combining Publishers

## Options (not exhaustive)
* **startWith**
  * useful when you have multiple producers and you want to check one producer first before you go to another producer. (Example cache)
* **concat**
  * it is the other whay of the startWith
* **merge**
  * it will allow subscribing to multiple producers at the same time (example getting flights from the flight companies)
* **zip**
  * It looks like the merge nut it is all or nothing (you need a response from all the producers (they shoukld give one item))
* **flatMap**
  * the flaMap will be subscribing to the inner publisher
* **concatMap**
* **then**
  * when you are not interested in the result of a producer/chain
  * for ex: You are inserting a bunch of records into the DB. You just need to know if it success or not. Not the intermediate results!

### What about dependent sequentials calls?
* independent producers
  * startWith
  * concat
  * merge
  * zip
* What about dependent sequential calls?

Scenario:  
* Given
  * User Service
    * Get user id for the given username
    * Ger All users
  * Payment Service
    * get user balance for the given user id
  * Order Service
    * get user orders for the given user id
  * Scenario:
    * I have username. I need to get user orders!


### Operator - then
* Use when you are not interested in the result of a producer / chain multiple asychronous calls to execute one by one!
* Fox ex:
  * You are inserting a bunch of records into the DB. You just need to know if it success or not. Not the intermediate results!


## Differece between .map and .flatMap

The primary difference between `.map` and `.flatMap` in reactive programming (and functional programming in general) is how they handle the transformation of elements:

- **`.map`**: Transforms each element emitted by a Publisher into another element. The transformation function returns a single value, and the resulting Publisher emits these transformed values.
The .map is good for some kind of in-memory computation, for instance:
```
UserService.getUserId("sam")
  .map(userId -> "Hello " + userId)
  .subscribe(Util.subscriber());
```

- **`.flatMap`**: Transforms each element emitted by a Publisher into another Publisher. The transformation function returns a Publisher, and the resulting Publisher emits the values from these inner Publishers in a flattened manner.
The flat map is good when you need to call for instance an external service or a database which returns a Mono of Lux Type.  
The flatMap is going to subscribe to the inner publisher  
flatMap is used to flatten the inner publisher / to subscribe to the inner publisher  
Sequential non-blocking IO calls!  
For instance:    
```
 UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());
```

### Example

#### Using `.map`
Transforms each `Customer` to its name:
```java
Flux<Customer> customers = repository.findAll();
Flux<String> customerNames = customers.map(Customer::getName);
```

#### Using `.flatMap`
Transforms each `Customer` to a Publisher that fetches additional details:
```java
Flux<Customer> customers = repository.findAll();
Flux<CustomerDetails> customerDetails = customers.flatMap(customer -> fetchDetails(customer.getId()));
```

In summary, use `.map` when you want to transform elements directly, and use `.flatMap` when you need to work with Publishers resulting from the transformation.



## what is the difference between .transform and .as?

The `.transform` and `.as` operators in Reactor are both used to apply transformations to a `Publisher`, but they have different use cases and behaviors.

- **`.transform`**: This operator is used to apply a transformation function to a `Publisher` and return a new `Publisher`. The transformation function is applied immediately and can be used to chain multiple transformations.

- **`.as`**: This operator is used to apply a transformation function to a `Publisher` and return a different type. It is typically used to convert a `Publisher` to another type, such as applying a repository method that returns a `Publisher`.

Here is an example to illustrate the difference:

```java
import reactor.core.publisher.Flux;

public class TransformVsAsExample {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // Using .transform to apply a transformation function
        Flux<Integer> transformedFlux = flux.transform(f -> f.map(i -> i * 2));
        transformedFlux.subscribe(System.out::println); // Output: 2, 4, 6, 8, 10

        // Using .as to apply a transformation function and return a different type
        Flux<Integer> asFlux = flux.as(f -> f.map(i -> i * 2));
        asFlux.subscribe(System.out::println); // Output: 2, 4, 6, 8, 10
    }
}
```

In this example, both `.transform` and `.as` are used to double the values in the `Flux`, but `.as` can be used to return a different type if needed.