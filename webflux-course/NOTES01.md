# WebFlux

## Spring WebFlux
* curl http://localhost:8080/traditional/products
* curl http://localhost:8080/reactive/products
* curl -N http://localhost:8080/reactive/products


## R2DBC
* JPA is a specification
  * It is for traditional synchronous programming/
* R2DBC is a separate specification!
  * For reactive programming.
* R2DBC != JPA
* Prioritizes
  * Performance
  * Scalability
  * Streaming + Backpressuer
* It does NOT have features like
  * @OneToMany
  * @ManyToMany

## Spring Data R2DBC
* `interface ReactivePersonRepository extends ReactiveSortingRepository<Person, Long>`

## R2DBC Drivers / Config
* h2 -> r2dbc:h2:mem:///userdb
* postgres -> r2dbc:postgresql://localhosy:5432/userdb
* mysql -> r2dbc:mysql://localhost:3306/userdb

```
spring:
  r2dbc:
    url: r2dbc:postgresql://localhosy:5432/userdb
    username: myuser
    password: mypassword
```

### Mutating Objects
* Reactive programming if functional style programming for IO
* Functional programming prefers pure functions (with no side effetcs)!
  * Prefer pure functions where it is possible! But not blindly everywhere!
* You table is mutable! Your entity object is mutable!
  * It is OK to mutate! 


## Pageable
* To request for chuncks of data from larger data set.
  * Page 1, Size 10
  * Sort by Price ascending

## Complex Queries / Join
* prefer SQL
  * it is efficient
  * No N+1 problem
* Repository
  * @Query
* Database client