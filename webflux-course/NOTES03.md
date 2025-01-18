# Functional Endpoints - sec06

## Controller Annotation
@GetMapping("{id}")

## Functional Endpoints
```
route()
    .GET("/customers", handler::getAllCustomers)
    .GET("/customers/{id}", handler::getCustomer)
    .POST("customers", handler::saveCustomer)
    ..
    .onError(CustomerNotFoundException.class, this::badRequestHandler)
    .build();
```

* Route
  * GET/POT/PUT/DELETE
  * Path based routing
  * RequestPredicate
  * PATH + RequestPredicate
* Handler Function
  * ServerRequest
    * headers/cookies/principal/path variables/ query params
  * return ServerResponse


# WebClient - sec07
* Reactor based fluent API for making HTTP requests
  * Wrapper around **reactor-netty**
    * It uses 1 thread/CPU
    * It is non-blocking
* Non-Blocking
* Immutable
* Thread safe!

## WebClient, some examples
* this.client.get()
  * post()
  * put()
  * delete()
* uri("/path)
* retrieve()
  * sends the request
  * receives the response
  * It is non-blocking
* java -jar external-services.jar (for the demos)

```
@Bean
public WebClient orderClient() {
    return WebClient.builder()
        .baseUrl("http://order-service.com")
        .build();


@Bean
public WebClient paymentClient() {
    return WebClient.builder()
        .baseUrl("http://payment-service.com")
        .build();
}
```

## WebClient - mutate
```
@Bean
public WebClient orderClient() {
    return WebClient.builder()
        .baseUrl("http://order-service.com")
        .build();

var newClient = oldClient.mutate()
    .baseUrl("http://new-base-url")
    .build();
}
```

## Retrieve VS Exchange
```
this.client().get()
    .uri("/product/{id}", 10)
    .retrieve()
    .bodyToMono(Product.class)
    .onError...
    //success
    //error
```

```
//headers
//cookies
//status code

this.client().get()
    .uri("/product/{id}", 10)
    .exchangeToMono(clientResponse -> )
```

## Exchange Filter Function - Cross Cutting Concerns!
* for instance generate a bearer token in each request
* you can attach 1 or N... filters
* **WebClient Attributes** (One filter function can pass information to another filter function using attributes)