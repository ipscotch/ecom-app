# User Service

This is a microservice for user registration, login, and profile management, built using Spring Boot and PostgreSQL.

## Tech Stack

- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Authentication**: Spring Security, JWT
- **Tools**: Maven, Docker

## Features

- User registration
- User login
- User profile management

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- PostgreSQL

### Installation

1. **Clone the main repository**:

    ```bash
    git clone https://github.com/ipscotch/ecom-app.git
    cd ecom-app/backend/userservice
   ```

2. **Set up PostgreSQL**:

   Ensure PostgreSQL is running and create a database for the service. Update the `application.properties` file with your PostgreSQL credentials.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/user_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
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
   docker build -t userservice .
   docker run -p 8080:8080 userservice
   ```
