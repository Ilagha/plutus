# Plutus AI Coding Guidelines

## Architecture Overview
Plutus is a Spring Boot 4.x monolith for internal finance tooling. It uses PostgreSQL with Flyway migrations for schema management. JPA/Hibernate validates DDL against migrations (no auto-creation).

Key components:
- REST API via Spring Web
- Data access via Spring Data JPA
- Schema migrations in `src/main/resources/db/migration/` (Flyway V{num}__{desc}.sql format)
- Health check at `/health`

## Build & Run
- **Local DB**: `docker-compose up plutus-db` (starts Postgres 16 on port 5432)
- **Build**: `./mvnw clean compile` (uses Maven wrapper)
- **Test**: `./mvnw test` (JUnit 5 + Spring Boot Test)
- **Run**: `./mvnw spring-boot:run` (auto-runs Flyway migrations)
- **Package**: `./mvnw clean package -DskipTests` (creates JAR in target/)

## Database Patterns
- Migrations: Place SQL files in `src/main/resources/db/migration/` with naming `V{number}__{description}.sql`
- Example: `V1__create_tasks_table.sql` creates `tasks` table with `id` (BIGSERIAL), `title`, `status`, `created_at`
- JPA: `ddl-auto=validate` ensures schema matches migrations
- Config: `application.properties` sets Postgres connection (localhost:5432/plutus)

## Testing
- Tests in `src/test/java/` using `@SpringBootTest`
- Current: `PlutusApplicationTests.contextLoads()` verifies app starts
- Run via Maven Surefire; reports in `target/surefire-reports/`

## CI/CD
- Jenkins pipeline: Builds with JDK 21 + Maven 3, runs tests, packages JAR
- Archives artifacts from `target/*.jar`

## Conventions
- Package: `com.company.plutus`
- Config: `application.properties` (SQL logging enabled for dev)
- Actuator: Exposes `/actuator/health` and `/actuator/info`
- Logging: Hibernate SQL at DEBUG level for troubleshooting

Reference: `pom.xml` for dependencies, `docker-compose.yml` for local setup.