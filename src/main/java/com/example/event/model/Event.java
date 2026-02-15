
package com.example.event.model;

import lombok.Data;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Event {

 private String eventId;
 private EventType eventType;
 private String payload;
 private String callbackUrl;
 private EventStatus status;
 private Instant createdAt=Instant.now();
 private Instant processedAt;

 private AtomicInteger retryCount=new AtomicInteger(0);
 private int maxRetries=3;
}
