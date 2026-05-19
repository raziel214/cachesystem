# Cachesystem

[![CI](https://github.com/raziel214/cachesystem/actions/workflows/ci.yml/badge.svg)](https://github.com/raziel214/cachesystem/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Redis](https://img.shields.io/badge/Redis-7.4-DC382D?logo=redis&logoColor=white)](https://redis.io/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Distributed cache REST API built with **Spring Boot 3**, **Redis 7** and **Hexagonal Architecture** (Ports & Adapters). It exposes a small, well-typed HTTP API with TTL support, OpenAPI documentation, Problem Details (RFC 7807) error handling and Testcontainers-backed integration tests.

The project is intentionally small but production-shaped: clean layering, no leaky abstractions, modern Spring idioms, and a CI pipeline that builds the Docker image on every push.

## Table of contents

- [Architecture](#architecture)
- [Tech stack](#tech-stack)
- [Quick start](#quick-start)
- [API](#api)
- [Configuration](#configuration)
- [Testing](#testing)
- [Project layout](#project-layout)
- [Roadmap](#roadmap)
- [License](#license)

## Architecture

The codebase follows Hexagonal Architecture (a.k.a. Ports & Adapters):

- **Domain** — pure Java, framework-free. Owns the `CacheEntry` record, the inbound use-case interfaces (`*UseCase`) and the outbound port (`CacheRepositoryPort`).
- **Application** — Spring `@Service`s that implement the use cases and orchestrate them against the outbound port. No Redis types here.
- **Infrastructure** — the only place where Spring Web, Redis, Jackson and OpenAPI live. Contains the REST controller, DTOs, the Redis adapter and the exception handler.

```
HTTP  ──►  Controller (DTO)  ──►  UseCase (domain port-in)
                                      │
                                      ▼
                              CacheRepositoryPort (domain port-out)
                                      │
                                      ▼
                          RedisCacheRepositoryAdapter ──► Redis
```

C4 diagrams (Context / Containers / Components) are available in [img/](img/).

## Tech stack

| Layer | Choice |
|---|---|
| Language | Java 21 (records, pattern matching, text blocks) |
| Framework | Spring Boot 3.3.5 |
| Cache backend | Redis 7.4 (`SCAN`-based pagination, native TTL) |
| HTTP & validation | Spring Web + Jakarta Validation |
| Docs | springdoc-openapi 2.6 (Swagger UI) |
| Error contract | RFC 7807 Problem Details (`ProblemDetail`) |
| Observability | Spring Actuator (`/actuator/health`, metrics, Prometheus) |
| Tests | JUnit 5, Mockito, AssertJ, Testcontainers, REST Assured |
| Build | Maven 3.9 (wrapper) |
| Runtime image | `eclipse-temurin:21-jre-alpine`, multistage layered jar |
| CI | GitHub Actions (build + tests + Docker) |

## Quick start

### Option A — Docker Compose (recommended)

Spins up Redis and the app together:

```bash
docker compose up --build
```

Then open:

- Swagger UI → http://localhost:8080/swagger-ui.html
- Health → http://localhost:8080/actuator/health

### Option B — Local Maven + Redis container

```bash
# 1. Start Redis
docker run --rm -d --name cachesystem-redis -p 6379:6379 redis:7.4-alpine

# 2. Run the app
./mvnw spring-boot:run
```

### Prerequisites

- JDK 21
- Docker (for Redis and integration tests)

## API

Base path: `/api/v1/cache`

| Method | Path | Description | Status codes |
|---|---|---|---|
| `POST` | `/api/v1/cache` | Create an entry | `201`, `400` |
| `GET` | `/api/v1/cache/{key}` | Fetch by key | `200`, `404` |
| `GET` | `/api/v1/cache` | List all entries | `200` |
| `PUT` | `/api/v1/cache/{key}` | Update an existing entry | `200`, `404` |
| `DELETE` | `/api/v1/cache/{key}` | Remove an entry | `204` |

### Examples

```bash
# Create with a 15-minute TTL
curl -X POST http://localhost:8080/api/v1/cache \
  -H 'Content-Type: application/json' \
  -d '{"key":"user:42","value":{"name":"Alice","role":"admin"},"ttl":"PT15M"}'

# Fetch
curl http://localhost:8080/api/v1/cache/user:42

# Delete
curl -X DELETE http://localhost:8080/api/v1/cache/user:42
```

Error responses follow RFC 7807:

```json
{
  "type": "https://cachesystem.jikkosoft.com/errors/not-found",
  "title": "Cache entry not found",
  "status": 404,
  "detail": "Cache entry not found for key: user:42",
  "instance": "/api/v1/cache/user:42",
  "timestamp": "2026-05-19T14:00:00Z"
}
```

## Configuration

All settings can be overridden via environment variables:

| Variable | Default | Description |
|---|---|---|
| `REDIS_HOST` | `localhost` | Redis hostname |
| `REDIS_PORT` | `6379` | Redis port |
| `SERVER_PORT` | `8080` | HTTP port |
| `CACHE_DEFAULT_TTL` | `PT1H` | Default TTL applied when none is provided (ISO-8601) |

See [`src/main/resources/application.yml`](src/main/resources/application.yml).

## Testing

```bash
./mvnw test          # unit + integration (requires Docker for Testcontainers)
./mvnw verify        # full build
```

Test layers:

- **Unit** — services tested with Mockito (`*Test.java`).
- **Domain** — `CacheEntry` invariants (`CacheEntryTest.java`).
- **Integration** — adapter and controller against a real Redis container (`*IT.java`), wired with `DynamicPropertySource`.

## Project layout

```
src/main/java/com/jikkosoft/cachesystem/
├── CachesystemApplication.java
├── domain/
│   ├── exception/CacheEntryNotFoundException.java
│   ├── model/CacheEntry.java
│   └── port/
│       ├── in/                              ← use-case interfaces
│       └── out/CacheRepositoryPort.java     ← outbound port
├── application/
│   └── usecase/                             ← @Service implementations
└── infrastructure/
    ├── config/                              ← Redis + OpenAPI beans
    ├── persistence/RedisCacheRepositoryAdapter.java
    └── rest/
        ├── CacheController.java
        ├── GlobalExceptionHandler.java
        └── dto/                             ← request/response records
```

## Roadmap

- [ ] Cache invalidation events (Redis pub/sub).
- [ ] Pluggable backends (in-memory adapter for tests, Caffeine for L1 cache).
- [ ] OpenTelemetry traces and Micrometer histograms.
- [ ] Helm chart and Kubernetes manifests.
- [ ] gRPC adapter on top of the same use cases.

## License

[MIT](LICENSE) © John Fredy Quimbaya Orozco
