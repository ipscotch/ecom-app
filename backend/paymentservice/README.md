# Payment Service

The Payment Service is responsible for handling payment transactions within the e-commerce platform. It integrates with payment gateways like Stripe to process payments securely.

## Tech Stack

- **Backend**: Spring Boot
- **Payment Gateway**: Stripe
- **Tools**: Maven, Docker

## Features

- Integrate with Stripe for payment processing.
- Handle payment transactions securely.
- Asynchronous event handling for payment status updates.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- Stripe account and API keys

### Installation

1. **Clone the main repository**:

    ```bash
    git clone https://github.com/ipscotch/ecom-app.git
    cd ecom-app/backend/paymentservice
    ```

2. **Set up Stripe**:

    To configure Stripe API keys for the project, update the [`application.properties`]file located in the `src/main/resources` directory:

    ```properties
    Stripe.apikey=your_stripe_api_key
    ```

    Replace `your_stripe_api_key` with your actual Stripe secret key.

3. **Build the project**:

    ```bash
    mvn clean install
    ```

4. **Run the service**:

    ```bash
    mvn spring-boot:run
    ```

5. **Dockerize the service** (optional):

    If you prefer to run the service in a Docker container, you can use the provided `Dockerfile`.

    ```bash
    docker build -t paymentservice .
    docker run -p 8080:8080 --env-file .env paymentservice
    ```

