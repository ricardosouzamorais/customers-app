# Customer Application

## Database

A refactoring was done on provided SQLite database file for adding a column to represent the country and make easier to do the filter.

The script is located at: [microservice/src/main/resources/database/db-refactor.sql](microservice/src/main/resources/database/db-refactor.sql)

### For Testing

The H2 is used for testing purposes and is automatically loaded due to Spring `@DataJpaTest` annotation.

## The Customer Microservice
The API can be viewed at: http://localhost:8080/customers-api/swagger-ui.html

### Examples of end-point calls
*  Return all customers
   *  http://localhost:8080/customers-api/v1/customers
*  Return a customer by ID
   *  http://localhost:8080/customers-api/v1/customers/{id}
*  Return all customers filtering by country's name
   *  http://localhost:8080/customers-api/v1/customers/search/findByCountry?name=${COUNTRY_NAME}
*  Return all customers filtering by customer's phone number state (VALID or INVALID)
   *  http://localhost:8080/customers-api/v1/customers/search/findByPhoneState?state=${PHONE_NUMBER_STATE}

### Building

### Usage

In case want to use just the microservice endpoint directly can go this on the following way:

```bash
docker run -p 8000:8080 \
		ricardosouzamorais/customers-microservice:0.0.1-SNAPSHOT
```

mvn clean package 

docker:build -DskipTests

docker run -t -p 8000:8080 ricardosouzamorais/customers-microservice:0.0.1-SNAPSHOT
