# Customer Application

## Requirements

The following list of tools are needed to build and run this application:

*  Docker 19.03.4+
*  git 2.17.2+
*  Maven 3.2.5+
*  OpenJDK 11.0.5+

<br/><hr/>

## Database

A refactoring was done on the provided SQLite database file for adding a column to represent the country and make easier to do the filter through country name.<br/>
The same was not done regarding the phone number sate (VALID or INVALID) because it was better to implement at service layer and make it possible to reuse it in a CRUD scenario.

The script is located at: [microservice/src/main/resources/database/db-refactor.sql](microservice/src/main/resources/database/db-refactor.sql)

### For Testing

The H2 is used for testing purposes and is automatically loaded due to Spring `@DataJpaTest` annotation.

### Database location

In case want to override the database file, please, copy the new file to [microservice/src/main/resources/database/db-refactor.sql](microservice/src/main/resources/database), beforing [running the build for the backend](#building-1), but make sure to keep its name, ***customers.db***.

<br/><hr/>

## The Customer SPA

Both dev (`environment.ts`) and prod (`environment.prod.ts`) files are pointing to the url `http://localhost:8080/customers-api/v1/customers` as the configuration for accessing the back-end.
In case another IP or port is needed, these files have to be change and a new build process has to be done again. 

### Building

For generating the image (also on Docker Hub):

```bash
cd spa
docker build -t ricardosouzamorais/customers-spa:0.0.1 .
```

### Running

For running the image:

```bash
docker run -p 3000:80 ricardosouzamorais/customers-spa:0.0.1
```

To run it as detached (without hanging the terminal):

```bash
docker run -d -p 3000:80 ricardosouzamorais/customers-spa:0.0.1
```

For accessing the SPA: http://localhost:3000

<br/><hr/>

## The Customer Microservice
The API can be viewed at: http://localhost:8080/customers-api/swagger-ui.html

### Building

For generating the image (also on Docker Hub):

```bash
mvn clean package
```

### Usage

For running the image:

```bash
docker run -p 8080:8080 \
		ricardosouzamorais/customers-microservice:0.0.1
```

To run it as detached (without hanging the terminal):
```bash
docker run -d -p 8080:8080 \
		ricardosouzamorais/customers-microservice:0.0.1
```

### Custom database file

In case you want to provide an external SQLite database file and independently of name, you can do this way (not detached):

```bash
docker run \
		-e url="jdbc:sqlite:/app/input/YOUR-DATABASE-FILE-NAME" \
		-v /path/to/your/external-database-file:/app/input \
		-p 8080:8080 \
		ricardosouzamorais/customers-microservice:0.0.1
```

Where: 
*  **/path/to/your/external-database-file** - is the path were your database file resides, which is mapped to a volume of the container in `/app/input`
*  **url** - as environment variable, it is read through `DBConfiguration` to laod the database. Its value has to have the value from the previous example cal, which you can change only **YOUR-DATABASE-FILE-NAME**, like **external.db**

As an example with values filled in:

```bash
docker run \
		-e url="jdbc:sqlite:/app/input/external.db" \
		-v /Users/ricardosm/Downloads/customers:/app/input \
		-p 8080:8080 \
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


##