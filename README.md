# Purpose

A web application used to retrieve a Github user profile

### Requirements

- JDK 11
- Docker

### Building

```bash
./gradlew clean build
```

### Running

```bash
docker-compose up -d --build
```

### Example Request

```bash
http://localhost:8080/api/v1/username/octocat
```

### Package Structure

- Shared packages
    - commons
        - Contain objects / services need through all the packages & services in the application
    - config
        - Application initial configuration exists in this package

- Feature packages
    - profile
        - Main package that contains the REST endpoint that exposes the user profile
    - repo
        - Contains service to access Github user repos
    - user
        - Contains service to access Github user info

### Design Decisions

- Made application modular in the case we decide to split out the `repo` or `user` packages into their own services,
  which now this could be done more easily

- Implemented caching on multiple layers in the case we build other services that may just want data from the `user`
  service, from a user that was already requested

- Split objects into BO (Business Objects) and DTO (Data Transfer Objects). DTO are objects that are serialized /
  deserialized and passed over the network. BO are objects that remain within the application but belong to an
  individual service

- Created an additional service inside the `profile` package that leverages the `repo` and `user` services to get the
  data it needs and then caches it in the form that it wants so following requests don't have to take the extra
  operations of forming the data from `repo` and `user`

### Known Improvements

- There could be more objects interfaces / separated out. I could consider having the `GithubClient` return its own
  response vs returning the javax response object. I would also consider interfacing all major libraries & boundary
  services

- When the `user` service throws a Runtime exception from the response it received from the http client, it should
  include the status code, message, and headers from that response so that can get passed along to the original caller

- There are only three unit test, in a production environment I would make sure to write unit tests for all the mappers,
  write a unit test for the `GithubClient` and use `WireMock` for that test, and also test all the services. I would
  also write integration tests where I could test the caching

- The caching performance is really good, but I notice that the normal response sometimes can hit around 750 - 900 ms.
  Most of that time is due to the http client making request to Github. I would explore another client that can maybe
  help with the performance, consider if maybe this endpoint is doing too much and needs to be split up, or look into
  using asynchronous http client calls (would be the last option)

- I would add a lot more logging in the application (especially around the http client calls and the exception mappers)

- In the way we use the `repo` service currently there is no response status checking, but if another service were to
  use this we would need to make sure it also has appropriate response error handling. Jersey client has out of the box
  support when client errors are thrown, but we would want to map this to a consistent error layout

