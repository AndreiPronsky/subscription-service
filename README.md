# User Subscription Service

A Spring Boot application providing RESTful APIs for user management and subscription functionality, featuring validation, logging, exception handling, and Swagger documentation. It runs in a Dockerized environment with PostgreSQL as the database.

---

## Features

- User registration, update, deletion, and retrieval
- Subscription and unsubscription for users
- Fetch top 3 subscriptions
- Bean validation via `@Valid`
- Centralized exception handling with HTTP status mapping
- Swagger UI for API documentation
- Docker support with PostgreSQL

---

## Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Hibernate Validator
- Spring Security (PasswordEncoder)
- SpringDoc OpenAPI (Swagger)
- Docker & Docker Compose
- Lombok
- JUnit 5, Mockito

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### Run Locally

```bash
git clone https://github.com/your-org/user-subscription-service.git
cd user-subscription-service
mvn spring-boot:run
```

### Run with docker
docker-compose up --build

### Swagger UI would be available at
http://localhost:8080/swagger-ui.html

### Endpoints
| Method | Path                                             | Description              |
| ------ | ------------------------------------------------ | ------------------------ |
| POST   | `/users`                                         | Register a new user      |
| GET    | `/users/{id}`                                    | Get user by ID           |
| PUT    | `/users`                                         | Update user              |
| DELETE | `/users/{id}`                                    | Delete user              |
| POST   | `/users/{userId}/subscriptions/{subscriptionId}` | Subscribe a user         |
| DELETE | `/users/{userId}/subscriptions/{subscriptionId}` | Unsubscribe a user       |
| GET    | `/users/{id}/subscriptions`                      | Get user's subscriptions |
