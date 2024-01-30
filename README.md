# Ktor War Sample

Sample gradle project of how to build and deploy a Ktor web application as a
`war` file on an serlvet container. It also allow to test locally the
application without the need of building the war or using the servlet container.


## Tool versions

To set the Java and Gradle versions required for this project in the current
shell, you can use:

``` bash
sdk env
```
This will configure the appropriate versions as indicated in the .gradlerc file.

To return to the versions defined globally, use:

``` bash
sdk env clear
```

## Gradle daemon

It is best to keep the Gradle daemon running because it is already so heavy that
compiling and recompiling the project becomes very slow.

Therefore, it is important to make sure that you do not have it disabled in
`GRADLE_OPTS`.

To manage the daemon, you can use `gradle --status` to see if you have one
running and `gradle --stop` to stop it.

## To test locally

To run the project locally without the need to deploy it to an
application container, you can use:

``` bash
gradle run
```

And access it at <http://localhost:8080>.

### To deploy with Docker

The project contains a Dockerfile to create an image that runs the project by
creating a war that is deployed to a container with jetty.

``` bash
gradle war
docker build -t jetty-war . 
docker run -p 8080:8080 my-application
```

You can then access it at <http://localhost:8080/jetty-war>.

### To test with Docker

If you don't want to create the image and use the jetty with Docker:

``` bash
gradle war
docker run \
 -v $PWD/build/libs:/var/lib/jetty/webapps \
 -p 8080:8080 \
 jetty:11.0.19 \
 java -jar /usr/local/jetty/start.jar
```

It is accessed in the same way as the previous one with
<http://localhost:8080/jetty-war>.
