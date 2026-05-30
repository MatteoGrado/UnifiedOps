# UnifiedOps Development Notes

## Services

| Service | Purpose | Port |
| --- | --- | --- |
| `API-Gateway` | Spring Cloud Gateway and Consul discovery client | `8000` in config, but verify YAML nesting before relying on it |
| `Consul-Service` | Spring Boot service registered with Consul | `8081` |
| `Documentation-Service` | Document storage API, planned S3-backed file handling | `8081` |

`Consul-Service` and `Documentation-Service` currently both use port `8081`, so they cannot run together without changing one port or using profiles.

## Common Commands

Run from each service directory:

```bash
./mvnw test
./mvnw spring-boot:run
```

Example:

```bash
cd Documentation-Service
./mvnw test
```

## Local Configuration

`Documentation-Service` imports `../env.properties` from its `application.yaml`. Create a local `env.properties` at the repository root using `env.properties.example` as a template.

Keep real credentials out of Git.

## Current Documentation-Service Work Items

- Introduce a document service layer between `DocController` and S3.
- Replace `java.io.File` controller return types with HTTP-friendly responses such as `ResponseEntity<Resource>` or metadata DTOs.
- Implement upload, list, download/open, update, and delete operations.
- Add focused tests for S3 key creation, metadata handling, controller status codes, and validation errors.
