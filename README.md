# Product-Service

A simple reactive api using Java Spring-boot, spring-cloud, springdoc-open-api and mongodb.

## Tech Stack

**Built-in:**

* [Spring](https://spring.io/)
* [MongoDB](https://www.mongodb.com/)
* [Docker](https://www.docker.com/)
* [Maven](https://maven.apache.org/)
* [SpringDoc-OpenApi](https://springdoc.org/)

## Run Locally

Clone the project

```bash
  git clone https://github.com/leoyassuda/product-service.git
```

Go to the project directory

```bash
  cd product-service
```

Install dependencies

```bash
  mvn clean package -D skipTests
```

Start mongo and registry-server

```bash
  docker-compose up
```

Start the server

```bash
  mvn spring-boot:run
```

Default port is 8181

## Run Tests

## API Documentation

* Open in browser - [swagger-ui](http://localhosr:8181/swagger-ui.html)


### Authors

* **Leo Yassuda** - *Initial work* - Portfolio [leoyas.com](https://leoyas.com)
