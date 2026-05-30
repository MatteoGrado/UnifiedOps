# UnifiedOps Agent Notes

## Project Shape

UnifiedOps is currently a multi-service Spring Boot workspace with separate Maven projects:

- `API-Gateway`: Spring Cloud Gateway with Consul discovery.
- `Consul-Service`: Spring Boot service registered with Consul.
- `Documentation-Service`: document/file service with planned S3 storage, MySQL, RabbitMQ, mail, security, and actuator support.

There is no root Maven aggregator at the moment. Run Maven commands from the individual service directories.

## Runtime And Build

- Java versions currently differ by service:
  - `API-Gateway`: Java 21
  - `Consul-Service`: Java 21
  - `Documentation-Service`: Java 17
- Use the checked-in Maven wrappers:
  - `cd API-Gateway && ./mvnw test`
  - `cd Consul-Service && ./mvnw test`
  - `cd Documentation-Service && ./mvnw test`
- Local Consul is expected on `localhost:8500`.
- `Documentation-Service` currently expects MySQL on `localhost:3306`, RabbitMQ on `localhost:5672`, and mail on `localhost:1025`.

## Conventions For Future Changes

- Keep service-specific changes inside the owning service unless shared configuration is introduced deliberately.
- Prefer constructor injection and explicit service classes for business logic. Controllers should stay thin.
- Do not commit real secrets. Use local ignored files such as `.env` or `env.properties`, and keep committed examples sanitized.
- Before modifying files with existing uncommitted changes, inspect them first and preserve user edits.
- For S3 integration, prefer AWS SDK request/response DTOs and stream-based uploads/downloads over `java.io.File` in controller contracts.

## Known Current State

- `Documentation-Service/src/main/java/de/grado/documentationservice/controller/DocController.java` contains placeholder methods without implementations.
- `Documentation-Service` S3 properties are configured under `storage.s3`.
- `Documentation-Service/src/main/resources/application.yaml` imports `optional:file:../env.properties`.
- The root `.env` file exists locally and must be treated as sensitive.
