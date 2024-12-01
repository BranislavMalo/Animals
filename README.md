# Animals

This project is a Spring Boot application for managing animals objects.

## Prerequisites

- Java 21 or later
- Maven
- Docker
- Postgres SQL Database

> NOTE !!!
> Animals application requires a standalone Postgres DB.
> Make sure to run a postgres (container) with database application properties values when running Animals application via Intellij Idea or using script.
> 
> e. g. using default application.properties
> 
> spring.datasource.url=jdbc:postgresql://localhost:5432/postgres \
> spring.datasource.username=jump \
> spring.datasource.password=soft \
> spring.datasource.driver-class-name=org.postgresql.Driver \
> spring.sql.init.platform=postgres

#### Download postgres latest image from DockerHub
```
docker pull postgres
```

#### Run command
```
docker run --name postgres-container \
-e POSTGRES_USER=jump \
-e POSTGRES_PASSWORD=soft \
-e POSTGRES_DB=postgres \
-p 5432:5432 \
-d postgres
```

## Build project using Maven
```bash
mvn clean install
```

## Running in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Click on `File` -> `Open` and select the project folder.
3. Wait for IntelliJ to import the project.
4. Navigate to AnimalsApplication class in `Animals/src/main/java/org/jump/soft/animal/core`.
5. Right-click on the class and choose `AnimalsApplication`.

## Running via Script

1. Open a terminal.
2. Navigate to the `Animals` directory in your project.
   ```bash
   cd /path/to/Animals/scripts
   ```
3. Parameter -p stands for path to the project
   ```bash
   ./run_animals_app.sh -p <path/to/Animals>
   ```

   example: ./run_animals_app.sh -p /home/branislavmalo

## Dockerize the Application

When you run your application with the docker-compose.yaml file, Docker will automatically pull the PostgreSQL image, set up the database, and configure everything for you, ensuring a seamless setup.

### Build the Docker Image

Ensure you are in the project's **composefiles** directory where the docker-compose.yml and Dockerfile are located.
```bash
docker-compose up --build
```

Access the Application: Once the containers are up and running, **Animals** Spring Boot application will be accessible at:
```
http://localhost:8080
```

Stop the Containers: To stop the running containers, use:
```bash
docker-compose down
```

## Endpoints

### Attached Postman Collection
Template HTTP requests for the endpoints are located in the postman_collection folder.

### Endpoint 1: add-animal

Creates the animal entity

```bash
curl --location --request POST 'http://localhost:8080/api/animals/add-animal' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Mufasa",
    "age": 15,
    "breedId": 1,
    "gender": "MALE"
}'
```

### Endpoint 2: remove-animal

Removes the animal entity

```bash
curl --location --request DELETE 'http://localhost:8080/api/animals/remove-animal/{id}'
```

### Endpoint 3: get-animal

Read the animal entity

```bash
curl --location --request GET 'http://localhost:8080/api/animals/get-animal/{id}'
```

### Endpoint 4: update-animal

Update the animal entity

```bash
curl --location --request PUT 'http://localhost:8080/api/animals/update-animal/{id}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "name": "Ashara",
    "age": 16,
    "breedId": 3,
    "gender": "FEMALE"
}'
```

### Endpoint 5: get-animals

Read the animal entities

```bash
curl --location --request PUT 'http://localhost:8080/api/animals/get-animals' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "name": "Ashara",
    "age": 16,
    "breedId": 3,
    "gender": "FEMALE"
}'
```

### Endpoint 6: get-animals-with-details

Read the animal entities with details

```bash
curl --location --request PUT 'http://localhost:8080/api/animals/get-animals-with-details' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "name": "Ashara",
    "age": 16,
    "breedId": 3,
    "gender": "FEMALE"
}'
```