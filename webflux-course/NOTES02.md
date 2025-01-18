# Input and error handling

## Problem Detail
* Standardized format for problems details!
  * RFC7807 / RFC9457
  * Structured error response
  * Machine/Human readable

### Problem Detail Fields:
* type -> A link to the documentation for the callers to read more about the problem. If it is not provided, "about:blank" is assumed.
* title -> Human readable summary of the problem
* status -> HTTP status code
* detail -> Detailed message specific to the problem
* instance -> The URI which caused the problem

#### Example
```
{
    "type": "https://example.com/problems/not-found",
    "title": "Customer Not Found",
    "status": 404,
    "detail": "The given customer id 10 is not found",
    "instance": "/customers/10"
}
```


## What about bean validation?
You can use that with spring webflux. It supports it.  
But in the course we will not use it. We will manually do the validation.  


### Exceptions
* Customer Not Found
* Invalid Input
  * name is required
  * valid email is required

## Summary
* Bean Validation + WebFlux works fine!
* error signals can be caught via Controller Advice and return appropriate codes!
* Problem Detail Standard Messages


# WebFilter

* To handle cross cutting concerns
* It will seat before the controller
  * WebFilter -> Controller -> Service
* Use cases:
  * any requests should have a header for instance "auth-token"
* We can access
  * path, header, parameters, cookies, etc...
* We can chain multiple **WebFilter** to multiple validations before the request reaches the controller
  * webfilter -> webfilter -> controller -> service

## WebFilter - Attrubites
One webfilter can provide informations to another webfilter using **attributes**  
It can set some key/values pairs.  
