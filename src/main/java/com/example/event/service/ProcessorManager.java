
package com.example.event.service;

import com.example.event.processor.EventProcessor;
import com.example.event.queue.EventQueueManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProcessorManager {

 private final ExecutorService executor=Executors.newFixedThreadPool(3);

 private final EventProcessor emailProcessor;
 private final EventProcessor smsProcessor;
 private final EventProcessor pushProcessor;

 public ProcessorManager(EventQueueManager manager,CallbackService callback){

  emailProcessor=new EventProcessor(manager.getQueue(com.example.event.model.EventType.EMAIL),callback,manager,5);
  smsProcessor=new EventProcessor(manager.getQueue(com.example.event.model.EventType.SMS),callback,manager,3);
  pushProcessor=new EventProcessor(manager.getQueue(com.example.event.model.EventType.PUSH),callback,manager,2);
 }

 @PostConstruct
 public void start(){

  executor.submit(emailProcessor);
  executor.submit(smsProcessor);
  executor.submit(pushProcessor);
 }

 @PreDestroy
 public void stop(){

  emailProcessor.stop();
  smsProcessor.stop();
  pushProcessor.stop();

  executor.shutdown();
 }
}
