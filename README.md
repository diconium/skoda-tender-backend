# Škoda Connect Services Tender Backend

## Overview
This project is a Spring Boot application that provides various services related to car connectivity. It includes functionalities such as fetching services for a specific VIN, managing user and car information, and more.

## Prerequisites
To set up and run this project locally, you need the following:

- Java 21
- Docker
- Maven
- An IDE (e.g., IntelliJ IDEA)

## Setup Instructions

### Build the Project
Ensure you have Java 21 installed and set up. Then, build the project using Maven:
```sh
mvn clean install
```

### Configure Environment Variables
Copy the `.env.sample` file to `.env` and fill in the appropriate settings for the database:
```sh
cp .env.sample .env
```
Edit the `.env` file to include your database settings:
```dotenv
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=your_database
POSTGRES_USER=your_user
POSTGRES_PASSWORD=your_password
```

### Start Docker Compose
Start the required services using Docker Compose:
```sh
docker-compose up
```

Run the Application
You can run the application with the local profile using Maven:
```
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Access the Application
Once the application is running, you can access it at:
```
http://localhost:8080
```

## API Documentation
The project uses `springdoc-openapi-starter-webmvc-ui` for API documentation. You can access the OpenAPI UI at:
```
http://localhost:8080/swagger-ui.html
```

## Code Formatting with Spotless
This project uses the Spotless plugin for code formatting. To check and apply formatting, use the following Maven commands:

- To check the formatting:
  ```sh
  mvn spotless:check
  ```

- To apply the formatting:
  ```sh
  mvn spotless:apply
  ```

## Configuration
You can customize the application settings in the `application.yml` file located in the `src/main/resources` directory.

## Contributing
Please follow the code formatting guidelines enforced by the Spotless plugin. Ensure all tests pass before submitting a pull request.
