
package com.example.event.controller;

import com.example.event.dto.EventRequest;
import com.example.event.dto.EventResponse;
import com.example.event.model.Event;
import com.example.event.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

 private final EventService service;

 public EventController(EventService s){ this.service=s; }

 @PostMapping
 public ResponseEntity<EventResponse> create(@RequestBody @Valid EventRequest req){

  return ResponseEntity.ok(
   new EventResponse(service.create(req),"Event accepted for processing.")
  );
 }

 @GetMapping("/{id}")
 public ResponseEntity<Event> get(@PathVariable String id){

  Event e=service.get(id);

  if(e==null) return ResponseEntity.notFound().build();

  return ResponseEntity.ok(e);
 }
}
