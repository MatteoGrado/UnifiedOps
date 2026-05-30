# UnifiedOps

UnifiedOps ist eine Spring-Boot basierte Microservice-Anwendung zur Verwaltung zentraler Unternehmensprozesse über eine gemeinsame Plattform.

Das Projekt dient als Lern- und Praxisprojekt, um moderne Backend-Technologien wie Microservices, Event-Driven Architecture, Spring Security und Docker einzusetzen.

---

## 🚀 Features

* Microservice-Architektur
* REST APIs
* Spring Security & JWT Authentication
* Event-Driven Communication
* Stripe API Integration
* AWS S3 Dateispeicherung
* Docker Deployment
* Unit Tests mit JUnit 5

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT

### Datenbank

* MySQL

### Messaging

* RabbitMQ

### Cloud & Storage

* AWS S3

### DevOps

* Docker
* Docker Compose
* Sentry

### Testing

* JUnit 5

---

## 🏗️ Architektur

Die Anwendung basiert auf einer Microservice-Architektur.

Aktuell umfasst das Projekt mehrere voneinander getrennte Services, die über REST APIs und asynchrone Nachrichten kommunizieren.

Die Event-Driven Architecture ermöglicht eine lose Kopplung zwischen den einzelnen Services und verbessert die Skalierbarkeit der Anwendung.

---

## 🎯 Projektziele

Dieses Projekt wurde entwickelt, um praktische Erfahrungen in folgenden Bereichen zu sammeln:

* Aufbau verteilter Systeme
* Entwicklung von REST APIs
* Absicherung von Anwendungen mit JWT
* Asynchrone Kommunikation mit RabbitMQ
* Containerisierung mit Docker
* Cloud Storage mit AWS S3

---

## ⚙️ Installation

### Voraussetzungen

* Java 21
* Docker
* Docker Compose
* MySQL

### Anwendung starten

```bash
docker compose up -d --build
```

Anschließend sind die Services lokal verfügbar.

---

## 🧪 Tests ausführen

```bash
./mvnw test
```

oder

```bash
mvn test
```

---

## 🗺️ Roadmap

* [ ] JWT Authentication
* [x] Docker Deployment
* [x] AWS S3 Integration
* [ ] Stripe Integration
* [ ] Unit Tests
* [x] API Gateway
* [ ] Monitoring
* [ ] CI/CD Pipeline
* [ ] Integration Tests

---

## 📸 Screenshots

Screenshots und Architekturdiagramme werden in Kürze ergänzt.

---

## 👨‍💻 Autor

**Matteo Grado**

* LinkedIn: https://www.linkedin.com/in/matteo-grado-a09481216/
* * GitHub: https://github.com/MatteoGrado
