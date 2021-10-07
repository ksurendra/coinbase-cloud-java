# Coinbase Cloud Java Client Libraries - JAXRS

Coinbase Cloud APIs' Java client libraries built using JAX-RS. 

This project is built with [Helidon MP](https://helidon.io/docs/latest/#/mp/guides/02_quickstart)

## Build and run

With JDK11+
```bash
mvn package
java -jar target/coinbase-cloud-java-jaxrs.jar
```

## Test the application

```
curl -X GET http://localhost:9393/coinbase
{"message": "The endpoint /coinbase is Available!"}
```

## FAQs
```
1. How to change the port?
   Update the `port` in file `src/main/resources/META-INF/microprofile-config.properties`

2. How to update LOG level?
   Update the configs in `src/main/resources/logging.properties`
```