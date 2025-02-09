# Java Functional Programming With Lanbdas & Streams Course

* https://github.com/in28minutes/functional-programming-with-java


## Sec01
* Paradigm shift - Fundamental change in approach
* Basic
  * Streams
  * Lambda Expressions
  * Method References
* Functional vs Structured Programming

### Remember about Intermediate and Terminal Operations on using Streams


## Funcional Interface
A functional interface has exactly one abstract method.  

### Some Functional Interfaces presents on JAVA (Very important)
* **Predicate**:
  * accepts one parameter and returns a boolean. `` boolean test(T t); ``
* **Function**:
  * Represents a function that accepts one argument and produces a result `` R apply(T t); `` 
* **Consumer**:
  * Represents an operation that accepts a single input argument and returns no result `` void accept(T t); `` 
  * use cases: store to a database, write to a file, log a message, print a message in the console, etc...
* **BinaryOperator**:
  * Represents an operation upon two operands of the same type, producing a result of the same type as the operands. This is a specialization of BiFunction for the case where the operands and the result are all of the same type.
  * `` R apply(T t, U u); ``
* **BiFunction**:
  * Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.
  * `` R apply(T t, U u); ``
* **Supplier**: No input -> Return Something
  * use case: A Factory Pattern (you want to create a new object)
  * example: 
    ``` 
        Supplier<Integer> supRandom = () -> 2;
        System.out.println(supRandom.get()) 
    ```
* **UnaryOperator**:
  * It accepts one paratemer and return a result of the same type as output
  * `` UnaryOperator<Integer> unaryOperator = (x) -> 3 * x ``  
* **BiPredicate**:
  * Thre will be two inputs and the optput type is boolean
* **BiConsumer**: accepsts two parameters and returns no result
* **IntBinaryOperator**: good to use when having primitive types, (good for performance because boxing and unboxing is not necessary)
* IntConsumer
* IntFunction
* IntPredicate
* IntSupplier
* IntToDoubleFunction
* IntToLongFunction
* IntUnaryOperator


**NOTE:** All of this are present on the java.util.function package  
**NOTE:** Function has a first class status in Java, we can pass a Function (some behaviour) as a parameter for a method or assign it to a variable.  


### High order Function
A high order function is a function that returns a function.  


### Parallel Streams
example: `` long sum = LongStream.range(0, 1000000000L).parallel().sum(); ``  
It will improve the performance  