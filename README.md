# Looqbox-challenge

## Overview
This project is a Spring Boot application built with Java 17, Spring Boot 3.4.1, and Gradle. It provides a REST API to consume the PokéAPI and exposes two endpoints for sorting Pokémon names, based on either alphabetical order or length.

## Prerequisites
To run the project locally, you'll need to have the following installed:
- **Java 17+** (JDK)
- **Gradle** (or use the Gradle wrapper)
- **Docker** (if you want to run the application in a container)

## Running the Application Locally

### 1. Using Gradle
To run the application locally using Gradle, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/victortmoura/looqbox-challenge.git
   cd <project-directory>
   ```
2. **Build the project**: Gradle will download the necessary dependencies and compile the project.
```bash
   ./gradlew build
   ```
3. **Run the application**: After the build is successful, you can run the application using Gradle:
   ```bash
   ./gradlew bootRun
   ```
   
The application will start on the default port, which is typically 8080. You can access the API at http://localhost:8080.

## Using Docker
If you prefer to run the application in a Docker container, follow these steps:

1. **Build the Docker image**:
```bash
   docker build -t pokem-api-sorting .
   ```
2. **Run the Docker container**: After the image is built, run the container with the following command:
   ```bash
   docker run -p 8080:8080 pokem-api-sorting
   ```
   
The application will now be running in Docker, and you can access it at http://localhost:8080.


