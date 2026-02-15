
package com.example.event.processor;

import com.example.event.model.Event;
import com.example.event.model.EventStatus;
import com.example.event.queue.EventQueueManager;
import com.example.event.service.CallbackService;

import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class EventProcessor implements Runnable {

 private final BlockingQueue<Event> queue;
 private final CallbackService callback;
 private final EventQueueManager manager;
 private final int delay;
 private volatile boolean running=true;

 public EventProcessor(BlockingQueue<Event> q,CallbackService c,EventQueueManager m,int delay){
  this.queue=q; this.callback=c; this.manager=m; this.delay=delay;
 }

 public void stop(){ running=false; }

 @Override
 public void run(){

  while(running || !queue.isEmpty()){

   try{

    Event e=queue.poll(1,TimeUnit.SECONDS);

    if(e==null) continue;

    e.setStatus(EventStatus.PROCESSING);

    Thread.sleep(delay*1000);

    boolean fail=Math.random()<0.1;

    if(fail && e.getRetryCount().incrementAndGet()<=e.getMaxRetries()){

     manager.enqueue(e);
     continue;

    }

    if(fail){

     e.setStatus(EventStatus.FAILED);
     e.setProcessedAt(Instant.now());
     callback.send(e,"Simulated failure");

    }else{

     e.setStatus(EventStatus.COMPLETED);
     e.setProcessedAt(Instant.now());
     callback.send(e,null);

    }

   }catch(Exception ex){
    ex.printStackTrace();
   }
  }
 }
}
