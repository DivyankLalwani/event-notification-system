
package com.example.event.service;

import com.example.event.dto.CallbackRequest;
import com.example.event.model.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallbackService {

 private final RestTemplate restTemplate=new RestTemplate();

 public void send(Event e,String error){

  CallbackRequest req=new CallbackRequest(
   e.getEventId(),
   e.getStatus(),
   e.getEventType(),
   error,
   e.getProcessedAt()
  );

  try{
   restTemplate.postForEntity(e.getCallbackUrl(),req,Void.class);
  }catch(Exception ex){
   System.out.println("Callback failed "+ex.getMessage());
  }
 }
}
