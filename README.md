# Product-Service

Product-Service is a basic API, which is able to do the CRUD operations on a Product resource using Image as a sub-resource of Product.  

## Getting Started

Cloning the repo
```
git clone https://bitbucket.org/leo-yassuda/product-service.git
```

### Prerequisites

What things you need to install the software and how to install them

> - Java 1.8
> - Maven 3


### Installing

In the folder where repo was cloned, run the following commands

```
mvn clean install -DskipTests
```

## Running the tests

```
mvn test
```

### Running the application

```
mvn spring-boot:run
```

### API

> / = return only a 'OK' message
> 
> /api/image = GET, POST, PUT operations - (GET) return Images without relationships, only the basic properties
> 
> /api/image/{id} = DELETE a specific Image
> 
> /api/image/{id} = get a specific Image
> 
> /api/image/product/{id} = get all Images of a specific Product
> 
> /api/product = GET, POST, PUT operations - (GET) return products without relationships, only the basic properties
> 
> /api/product/{id} = DELETE a specific Product
> 
> /api/product/{id}/childrenBasic = GET - return all Products children of a specific Product parent
> 
> /api/product/{id} = GET a specific Product
> 
> /api/product/listWithAllProperties = (GET) return products with all properties and relationships but only the basic properties for the children and Images
> 
> /api/product/{id}/childrenAllProperties = (GET) return products with all properties and relationships


## Built With

* [Spring-boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2-Database](http://www.h2database.com/html/main.html) - Database

## Authors

* **Leo Yassuda** - *Initial work* - [Product-Service](https://bitbucket.org/leo-yassuda/product-service)

