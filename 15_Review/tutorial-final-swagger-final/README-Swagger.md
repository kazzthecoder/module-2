# Swagger

This version of the tutorial has Swagger integrated into it. Swagger provides a number of
Java annotations that can be used to document methods and parameters in `@RestController` classes.

In addition, Swagger provides a user interface to inspect and execute the REST services
exposed by the service.

Adding Swagger into an application involves a few steps:
- add dependencies to the POM
- add annotations to the RestController methods
- add a Swagger configuration class to the application

## Dependencies

Open [pom.xml](pom.xml) and scroll to the `<dependendcies>` section. The required dependencies have
have been added at the bottom of the section

## Annotations

Documenting the REST methods involves adding a number of annotations to each method. An example of
an annotated method looks like this:

```java
    @ApiOperation("Returns all of the auctions available in the system")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") @ApiParam(value = "Optional. Auction title to search for", defaultValue = "") String title_like,
                              @RequestParam(defaultValue = "0") @ApiParam(value = "Optional. Search for bids under given amount", defaultValue = "0") double currentBid_lte) {
```

The `@ApiOperation` annotation provides a description of what the method does.

The `@ApiParam` annotations describe each parameter passed to the method.

## Configuration

Swagger requires a configuration class. The configuration class is discovered and executed by Spring when
the application starts. The configuration for this tutorial is [SpringFoxConfig](src/main/java/com/techelevator/auctions/swagger/SpringFoxConfig.java).  

## User Interface

When the server is running, the Swagger UI can be accessed http://localhost:8080/swagger-ui.html