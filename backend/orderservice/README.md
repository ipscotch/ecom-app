# Order Service

The Order Service is responsible for handling order placements, tracking, and history within the e-commerce platform. It uses Spring Boot and PostgreSQL to manage order-related operations.

## Tech Stack

- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Tools**: Maven, Docker, RabbitMQ

## Features

- Handle order placements.
- Track order status.
- Maintain order history.
- Asynchronous event handling for order updates.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- PostgreSQL
- RabbitMQ

### Installation

1. **Clone the main repository**:

    ```bash
    git clone https://github.com/ipscotch/ecom-app.git
    cd ecom-app/backend/orderservice
    ```

2. **Set up PostgreSQL**:

    Ensure PostgreSQL is running and create a database for the order service.

3. **Set up RabbitMQ**:

    Ensure RabbitMQ is running for handling asynchronous events.

4. **Configure application properties**:

    Update the `src/main/resources/application.properties` file with your PostgreSQL and RabbitMQ configurations:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/orderdb
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password

    spring.rabbitmq.host=localhost
    spring.rabbitmq.port=5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    ```

5. **Build the project**:

    ```bash
    mvn clean install
    ```

6. **Run the service**:

    ```bash
    mvn spring-boot:run
    ```

7. **Dockerize the service** (optional):

    If you prefer to run the service in a Docker container, you can use the provided `Dockerfile`.

    ```bash
    docker build -t orderservice .
    docker run -p 8080:8080 --env-file .env orderservice
    ```

