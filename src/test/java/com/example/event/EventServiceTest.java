
package com.example.event;

import com.example.event.dto.EventRequest;
import com.example.event.model.EventType;
import com.example.event.service.EventService;
import com.example.event.queue.EventQueueManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

 @Test
 void testEventCreation(){

  EventQueueManager manager=new EventQueueManager();

  EventService service=new EventService(manager);

  EventRequest req=new EventRequest();

  req.setEventType(EventType.EMAIL);
  req.setPayload("hello");
  req.setCallbackUrl("http://localhost");

  String id=service.create(req);

  assertNotNull(id);

  assertNotNull(service.get(id));
 }
}
