package com.smartrm.smartrminfracore.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: yoda
 * @description:
 */
public abstract class DomainEventListener implements Runnable {
    
    protected static Logger LOGGER = LoggerFactory.getLogger(DomainEventListener.class);
    
    //  private KafkaConsumer<String, String> kafkaConsumer;
    protected DomainEventHandler handler;
    
    protected String server;
    
    protected Class eventType;
    
    protected boolean isClose;
    
    protected String groupId = "consumer-group-";
    
    
    public DomainEventListener() {
    }
    
    public DomainEventListener(Class eventType, DomainEventHandler handler, String server) {
        this.eventType = eventType;
        this.handler = handler;
        this.server = server;
        this.groupId = this.groupId + eventType.getSimpleName();
    }
    
    protected ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    
    
    public void close() {
        isClose = true;
    }
    
    //  public DomainEventListener(Properties props, Class eventType, DomainEventHandler handler) {
    ////    kafkaConsumer = new KafkaConsumer<String, String>(props);
    //    this.eventType = eventType;
    //    this.handler = handler;
    //  }
    
    public void setHandler(DomainEventHandler handler) {
        this.handler = handler;
    }
    
    public void setServer(String server) {
        this.server = server;
    }
    
    public void setEventType(Class eventType) {
        this.eventType = eventType;
    }
    
    @Override
    public abstract void run();
}
