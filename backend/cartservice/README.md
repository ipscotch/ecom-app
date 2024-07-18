# Cart Service

The Cart Service is responsible for managing user shopping carts within the e-commerce platform. It uses Spring Boot and Redis to handle cart-related operations efficiently.

## Tech Stack

- **Backend**: Spring Boot
- **Database**: Redis
- **Tools**: Maven, Docker

## Features

- Add items to cart.
- Remove items from cart.
- Retrieve current cart.
- Handle cart expiration using Redis TTL.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- Redis

### Installation

1. **Clone the main repository**:

    ```bash
    git clone https://github.com/ipscotch/ecom-app.git
    cd ecom-app/backend/cartservice
    ```

2. **Set up Redis**:

    Ensure Redis is running. You can use Docker to run Redis:

    ```bash
    docker run --name redis -p 6379:6379 -d redis
    ```

3. **Configure application properties**:

    Update the `src/main/resources/application.properties` file with your Redis configurations:

    ```properties
    spring.redis.host=localhost
    spring.redis.port=6379
    ```

4. **Build the project**:

    ```bash
    mvn clean install
    ```

5. **Run the service**:

    ```bash
    mvn spring-boot:run
    ```

6. **Dockerize the service** (optional):

    If you prefer to run the service in a Docker container, you can use the provided `Dockerfile`.

    ```bash
    docker build -t cartservice .
    docker run -p 8080:8080 --env-file .env cartservice
    ```

