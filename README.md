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

---

## Callback Notification

System sends HTTP POST request to:

