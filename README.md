# Student Registry Service (App 1)

[![Java CI with Gradle](https://github.com/DhroovSankla/Student_Registry_Service/actions/workflows/ci.yml/badge.svg)](https://github.com/DhroovSankla/Student_Registry_Service/actions/workflows/ci.yml)

An enterprise-patterned transaction ingestion microservice built with **Spring Boot** and **Java 21**. This service serves as the entrance gate for student registration logs, performing strict JSR-380 inputs validation, persisting data to MySQL, and publishing serialized message payloads to Apache Kafka.

This microservice is part of a 2-part event-driven system. The companion consumer repository is the **[Notification Hub & Operations Cockpit (App 2 + React UI)](https://github.com/DhroovSankla/Notification_Hub_Service)**.

---

## ⚙️ Core Architecture & Features
* **JSR-380 Input Validation**: Enforces structural constraints on names, roll numbers, departments, and emails.
* **Persistent MySQL Layer**: Saves candidate registrations using Spring Data JPA. Database version control is managed automatically via **Flyway Migrations**.
* **Kafka Event Bus**: Automatically publishes serialized payloads (`eventId`, `recipient`, `channel`, `templateType`, student details) to the `notification-hub-topic`.
* **JWT Authorization Guard**: Restricts access to key registry endpoints using cryptographically signed JSON Web Tokens.
* **Automated CI/CD**: Wired with a **GitHub Actions CI Pipeline** validating compilation and testing.
* **Containerized Build**: Multi-stage **Dockerfile** compile to a minimal JRE alpine runtime.

---

## 🛠️ Tech Stack
* **Runtime**: Java 21 (Eclipse Temurin)
* **Framework**: Spring Boot 3.3 (Spring MVC, Spring Security, Spring Data JPA, Spring Kafka)
* **Database**: MySQL 8.0
* **Migrations**: Flyway Migration Engine
* **Messaging**: Apache Kafka Broker
* **Build System**: Gradle (Kotlin DSL)

---

## 🚀 Local Setup & Installation

### Prerequisites
* JDK 21 installed.
* Docker running (for MySQL & Kafka brokers. Set up via the Compose file in App 2).

### 1. Configure Local Environment Variables
Create a `.env` file inside the `registry_service/` folder:
```env
JWT_SECRET=EnterpriseGradeSecretKeyThatIsAtLeast32BytesLong123!@#
DB_URL=jdbc:mysql://localhost:3306/student_registry_db?allowPublicKeyRetrieval=true&useSSL=false
DB_USERNAME=root
DB_PASSWORD=root
KAFKA_BROKERS=localhost:9092
```

### 2. Launch the Application
Compile and run the Spring Boot service:
```bash
cd registry_service
./gradlew bootRun
```
The server will boot and start listening on port `8080`.

---

## 🐳 Docker Deployment

To build a standalone production container:
```bash
cd registry_service
docker build -t registry-service:latest .
docker run -d --name registry-app -p 8080:8080 --network="host" registry-service:latest
```

---

## 🔗 Related Repository
* **[Notification Hub & React Cockpit UI (App 2)](https://github.com/DhroovSankla/Notification_Hub_Service)**: Consumes registration streams, logs transactions to an audit ledger, supports Redis cache routing, hosts the Outage DLQ Simulator, and broadcasts real-time dashboards over WebSockets.
