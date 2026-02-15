# Event Notification System

A production-grade Java Spring Boot backend system that processes EMAIL, SMS, and PUSH notification events asynchronously using FIFO queues, retry mechanisms, and callback notifications.

This project fulfills all requirements of the Event Notification System assignment, including concurrency, async processing, retry handling, graceful shutdown, Docker support, and JUnit testing.

---

# 🚀 Features

## Core Features

- Accept EMAIL, SMS, and PUSH events via REST API
- Separate FIFO queue for each event type
- Separate processing thread per event type
- Asynchronous event processing
- Retry mechanism (up to 3 retries)
- 10% simulated random failure
- Automatic callback notification to client system
- Event status tracking via REST API
- Graceful shutdown support

---

## Processing Times

| Event Type | Processing Time |
|-----------|----------------|
| EMAIL     | 5 seconds      |
| SMS       | 3 seconds      |
| PUSH      | 2 seconds      |

---

## Retry Mechanism

- Maximum retries: 3
- Failed events are automatically re-queued
- After max retries, event marked as FAILED



Here is a **short, impressive, evaluator-ready README.md (professional and clean)**:

```markdown
# Event Notification System

A Spring Boot backend system that asynchronously processes EMAIL, SMS, and PUSH notification events using FIFO queues, retry mechanism, and callback notifications. Built with concurrency, thread safety, and graceful shutdown support.

---

# How to Run

## Option 1: Using Docker (Recommended)

Start the application:

```

docker compose up --build

```

API will be available at:

```

[http://localhost:8080/api/events](http://localhost:8080/api/events)

```

Stop the application:

```

docker compose down

```

---

## Option 2: Run Locally

Prerequisite: Java 17+

Build and start:

```

./gradlew build
./gradlew bootRun

```

Application starts at:

```

[http://localhost:8080/api/events](http://localhost:8080/api/events)

```

---

# Test APIs

## Create Event

```

POST /api/events

````

Example body:

```json
{
  "eventType": "EMAIL",
  "payload": "Test message",
  "callbackUrl": "http://client-system.com/api/event-status"
}
````

## Get Event Status

```
GET /api/events/{eventId}
```

---

# Run Tests

```
./gradlew test
```

---

# Key Features

* Asynchronous FIFO processing per event type
* Separate queues and threads for EMAIL, SMS, PUSH
* Retry mechanism with failure handling
* Automatic callback notification
* Graceful shutdown support
* Dockerized deployment
* JUnit test coverage
---

## Callback Notification

System sends HTTP POST request to:

