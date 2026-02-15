
package com.example.event.dto;

import com.example.event.model.EventStatus;
import com.example.event.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class CallbackRequest {

 private String eventId;
 private EventStatus status;
 private EventType eventType;
 private String errorMessage;
 private Instant processedAt;
}
