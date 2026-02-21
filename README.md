# blackrock-challenge-feb-2026-aniruddha-gayake
# BlackRock Challenge – February 2026
**Author:** Aniruddha Gayake

A Spring Boot REST API built for the BlackRock February 2026 coding challenge.

Docker hub image link - https://hub.docker.com/repository/docker/anigayake96/blk-hacking-ind-aniruddha-gayake/general
Youtube video link - https://youtu.be/mtgQXDZwMcY?si=eKpSIXPoUELRvzSQ
---

## Tech Stack

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Web** – RESTful API
- **Spring Validation** – Request validation
- **Spring Actuator** – Health & metrics endpoints
- **Micrometer** – Application metrics
- **SpringDoc OpenAPI (Swagger UI)** – API documentation
- **Docker** – Containerized deployment

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Docker (optional, for containerized run)

---

## Getting Started

### Build the project
```bash
./mvnw clean package
```

### Run locally
```bash
./mvnw spring-boot:run
```

The application starts on **port 5477** by default.

### Run tests
```bash
./mvnw test
```

---

## Docker

### Build the image
```bash
docker build -t blk-hacking-ind-aniruddha-gayake .
```

### Run the container
```bash
docker run -p 5477:5477 blk-hacking-ind-aniruddha-gayake
```

---

## API Documentation

Once the application is running, Swagger UI is available at:
```
http://localhost:5477/swagger-ui/index.html
```

---

## Actuator / Health Check
```
http://localhost:5477/actuator/health
```

---

## Project Structure
```
src/
├── main/
│   ├── java/com/blackrock/challenge/   # Application source code
│   └── resources/                       # Configuration files
└── test/                                # Unit & integration tests
```

---

## Notes

- Please refer Blackrock-Challenge.postman_collection.json, import it in your postman/insomnia and try the requests.
- The Docker image runs as a non-root user (`appuser`) for security.
- JVM is configured with container-aware memory settings (`-XX:+UseContainerSupport`, `-XX:MaxRAMPercentage=75.0`).