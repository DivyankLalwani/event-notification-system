
package com.example.event.dto;

import com.example.event.model.EventType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventRequest {

 @NotNull
 private EventType eventType;

 @NotNull
 private String payload;

 @NotNull
 private String callbackUrl;
}
