# Streaming - sec08

## Communication Patterns
* request -> response
* request -> streaming response
  * A file download
  * getting messages, the driver is 5 miles away, etc...
* streaming request -> response
  * file upload
  * sensor heart rate monitor
  * uber driver location, sending data to the backend server periodically
* Bidirectional-streaming
  * online games
  * chat

## Streaming
* service => service
* create milion products

## Streaming Advantages
* We will setup connection once and keep sending the messages in a streaming fashion
* No need to wait for previous request to complete
* Reduced network traffic/latency
* You can use JSON to create a product/item

## Json Line Format
* aka ND-JSON
  * new-line delimited
* Each line is 1 JSON object
  * selft container
  * easy to parse
  * great for streaming!
  * massive databsets!
* JSON array is good for smaller and related data - For ex: reviews for a product

Example:  
```
{"id": 123, "name": "Product A", ...}
{"id": 234, "name": "Product B", ...}
{"id": 345, "name": "Product C", ...}
...
...
...
```

# Server Sent Events (SSE / Event Source) - sec09
* Stream events from backend to frontend
* one-way communication

## Text Event Stream
* // Flux<T>
* MediaType.APPLICATION_NDJSON_VALUE; //service to service
* MediaType.TEXT_EVENT_STREAM_VALUE; // to browser

Use case:  
* Users want to know new products update
  * How can we send a message when a new product is added!?
    * Sinks

## Sinks

### TO test IT
1. access the following in the browser http://localhost:8080/products/stream
2. send a request to add a product
```
curl --location 'http://localhost:8080/products' \
--header 'Content-Type: application/json' \
--data '{
    "description": "mac pro",
    "price": 2000
}'
```
* curl http://localhost:8080/products/stream
* send another request to add a product

# Performance Optimization
## Advenced Configuration / Best Practices

### Horizontal / Vertical Scaling!
* Before you run another server for horizontal scaling or increasing the RAM/CPU...
  * If he app is slow - why?
  * What could be the problem?
  * How to reduce the infrastructure cost?

### gZIP
* If the response size is large (in KBs), it will take more time for the client to receive the response!
* Client might observe increased response time. The apps might appear to be performing poorly!
* But it could be just **network latency**!
* To compress the response before sending over the network 
* It works well in a congested network + response size is large!
* Note:
  * Server requires additional processing to compress
  * It might have negative effect when the response size is small!
  * do NOT use local machine teste! You will NOT see any improvement!

#### How to configure and enable gZIP?
* on the server side:
server.compression.enabled=true  
server.compression.min-response-size=2048  
server.compression.mime-types=application/json,application/xml  

* Client Side request Header (The client needs to send this header):
Accept-Encoding: gzip  

### Keep Alive / Connection Pooling
* Connection setup takes time!
* Keep-alive -> to reuse connections
* HTTP 1.1
  * 1 connection per request

Dear All,  
I will be using this command to monitor the network connections. If it does NOT work, please ignore.  

Netstat command to monitor the network connections  
netstat -an | grep -w 127.0.0.1.7070  

To watch  
watch 'netstat -an | grep -w 127.0.0.1.7070'  


#### Connection Pooling!
* WebClient - by default - will manage 500 connections to a remote service!
  * It is configurable!

### HTTP2
* HTTP 1.1 -> 1997
* HTTP2 -> 2025
* Multiplexing
* Binary Protocol
* Header compression
* We might not see the benefit if we do NOT have many concurrent requests.
* Ensure that your load balancer supports HTTP2

#### How to enable HTTP2?
//server side  
server.http2.enabled=true  

//client should send which protocol it wants to use!  

## subscribeOn
* Try to use reactive libraries for your application!
  * R2DBC, mongo, redis, kafka, pulsar, elasticsearch..
* What if I do not have?
```
  Mono.fromSupplier(() -> yourLib.invokeMethod())
    ...
    .subscribeOn(Shcedulers.boundedElastic()) //very important
```

## Summary
* gZIP
* Connection Pooling + Keep-alive
* HTTP2
* subscribeOn