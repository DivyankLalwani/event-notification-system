
package com.example.event.queue;

import com.example.event.model.Event;
import com.example.event.model.EventType;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class EventQueueManager {

 private final BlockingQueue<Event> emailQueue=new LinkedBlockingQueue<>();
 private final BlockingQueue<Event> smsQueue=new LinkedBlockingQueue<>();
 private final BlockingQueue<Event> pushQueue=new LinkedBlockingQueue<>();

 public void enqueue(Event e){
  switch(e.getEventType()){
   case EMAIL -> emailQueue.add(e);
   case SMS -> smsQueue.add(e);
   case PUSH -> pushQueue.add(e);
  }
 }

 public BlockingQueue<Event> getQueue(EventType t){
  return switch(t){
   case EMAIL -> emailQueue;
   case SMS -> smsQueue;
   case PUSH -> pushQueue;
  };
 }
}
