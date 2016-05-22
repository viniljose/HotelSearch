# Hotel Search Services
https://github.com/viniljose/HotelSearch.git

## Overview and Architecture

### Overview

The Hotel Search Services is exposing the API which is responsible to hotel search based on city and prices can be optionally sorted.
Application exposes a rest http post service which accepts a JSON and response also in JSON format.
http://localhost:8080/search
{"city":"Amsterdam","sortDescending":true,"apiKey":"B"}
Clients are provided with apiKey and which needs to be passed in the request.
RateLimit is implemented with one request per 10 secs.
If second request comes with in 10 secs,then that key will be suspended for 5 mins.

### Application Architecture 

The architecture of the HTTP service Java application follows a [Clean Architecture/Hexagonal](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html).  
As such, the core of the application itself is devoid of any delivery framework mechanisms and technology specific details.  
I.e. Things like SQL and Spring MVC are kept outside of the boundary of the application.  It is developed in a use case centric approach.


#### Technology view

### Technology Standards
   

| Technology class | Technology employed | Version |
| ---------------- |:------------------- | ------- |
| Platform         | Java 				 | 1.8     |
| Bootstrap framework | SpringBoot       | 1.3.5   |

## Getting up and running locally

### System requirements

* Maven 3.x
* Java 1.8


### Steps

#### To Build

```
mvn clean install
```

#### To Run

```
java -jar target/hotel-service-app-0.0.1-SNAPSHOT.jar
```


