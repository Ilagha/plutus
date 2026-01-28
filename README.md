# Plutus 🪙

Plutus is a simple, clean **Task Management REST API** built with **Spring Boot**, **PostgreSQL**, **JPA/Hibernate**, and **Flyway**.  
It demonstrates a **layered, production-style architecture** with proper separation of concerns.

---

## ✨ Features

- Create tasks
- List all tasks
- Persist data in PostgreSQL
- Database schema versioning with Flyway
- Clean REST API design using DTOs
- JPA/Hibernate entity mapping

---

## 🏗 Architecture Overview

Plutus follows a **layered architecture**:

Client (curl / frontend) --> API Layer (Controllers + DTOs) --> Service Layer (Business Logic) --> Repository Layer (JPA) --> PostgreSQL


Each layer has a single responsibility, making the codebase:
- easy to understand
- easy to test
- easy to extend

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/company/plutus/
│   │       ├── HealthController.java
│   │       └── PlutusApplication.java
│   └── resources/
│       ├── application.properties
│       └── db/migration/
│           └── V1__create_tasks_table.sql
└── test/
    └── java/
        └── com/company/plutus/
            └── PlutusApplicationTests.java
```


---

## 🧠 Design Decisions (Why this structure?)

- **Domain layer**: pure business concepts (`Task`, `TaskStatus`)
- **Repository layer**: database access via Spring Data JPA
- **Service layer**: business logic (no HTTP, no SQL)
- **API layer**: HTTP + JSON only (controllers & DTOs)
- **DTOs**: prevent exposing internal entities directly
- **Flyway**: version-controlled database schema


---

## 🗄 Database

- **PostgreSQL**
- Schema managed by **Flyway**
- Initial migration creates the `tasks` table

### Tasks Table
| Column      | Type        |
|-------------|-------------|
| id          | BIGSERIAL   |
| title       | VARCHAR     |
| status      | VARCHAR     |
| created_at | TIMESTAMP   |

---

## ⚙ Configuration

`application.properties` (example):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/plutus
spring.datasource.username=plutus_user
spring.datasource.password=plutus_pass

spring.jpa.hibernate.ddl-auto=validate

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration


