# Product Service

This microservice is responsible for managing products, categories, and inventory in an e-commerce platform. It is built using Spring Boot and MongoDB.

## Tech Stack

- **Backend**: Spring Boot
- **Database**: MongoDB
- **Tools**: Maven, Docker

## Features

- Manage products
- Manage categories
- Manage inventory

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- MongoDB (optional)

### Installation

1. **Clone the main repository**:

    ```bash
    git clone https://github.com/ipscotch/ecom-app.git
    cd ecom-app/backend/productservice
   ```

2. **Set up MongoDB (optional)**:

   Ensure MongoDB is running and update the `application.properties` file with your MongoDB connection details.

   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/productdb
   ```

3. **Build the project**:

   ```bash
   mvn clean install
   ```

4. **Run the service**:

   ```bash
   mvn spring-boot:run
   ```

5. **Dockerize the service (optional)**:

   If you prefer to run the service in a Docker container, you can use the provided Dockerfile.

   ```bash
   docker build -t productservice .
   docker run -p 8080:8080 productservice
   ```