
package com.example.event.service;

import com.example.event.dto.EventRequest;
import com.example.event.model.Event;
import com.example.event.model.EventStatus;
import com.example.event.queue.EventQueueManager;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventService {

 private final EventQueueManager queueManager;
 private final Map<String,Event> store=new ConcurrentHashMap<>();

 public EventService(EventQueueManager manager){
  this.queueManager=manager;
 }

 public String create(EventRequest req){

  Event e=new Event();

  e.setEventId(UUID.randomUUID().toString());
  e.setEventType(req.getEventType());
  e.setPayload(req.getPayload());
  e.setCallbackUrl(req.getCallbackUrl());
  e.setStatus(EventStatus.PENDING);

  store.put(e.getEventId(),e);

  queueManager.enqueue(e);

  return e.getEventId();
 }

 public Event get(String id){ return store.get(id); }
}
