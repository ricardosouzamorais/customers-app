# Customer Application

## Database

A refactoring was done on the provided SQLite database file for adding a column to represent the country and make easier to do the filter.

The script is located at: [microservice/src/main/resources/database/db-refactor.sql](microservice/src/main/resources/database/db-refactor.sql)

### For Testing

The H2 is used for testing purposes and is automatically loaded due to Spring `@DataJpaTest` annotation.

<br/><hr/>

## The Customer SPA

### Building

```bash
cd spa
docker build -t ricardosouzamorais/customers-spa:0.0.1 .
```

### Running

```bash
docker run -p 3000:80 ricardosouzamorais/customers-spa:0.0.1
```

<br/><hr/>

## The Customer Microservice
The API can be viewed at: http://localhost:8080/customers-api/swagger-ui.html

### Building

*  Ajustar para buildar o docker
mvn clean package docker:build

### Usage

In case want to use just the microservice endpoint directly can go this on the following way:

```bash
docker run -p 8080:8080 \
		ricardosouzamorais/customers-microservice:0.0.1
```

### Examples of end-point calls
*  Return all customers
   *  http://localhost:8080/customers-api/v1/customers
*  Return a customer by ID
   *  http://localhost:8080/customers-api/v1/customers/{id}
*  Return all customers filtering by country's name
   *  http://localhost:8080/customers-api/v1/customers/search/findByCountry?name=${COUNTRY_NAME}
*  Return all customers filtering by customer's phone number state (VALID or INVALID)
   *  http://localhost:8080/customers-api/v1/customers/search/findByPhoneState?state=${PHONE_NUMBER_STATE}
