package com.smartrm.smartrminfracore.event.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smartrminfracore.event.DomainEventBus;
import com.smartrm.smartrminfracore.event.DomainEventHandler;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: yoda
 * @description: 事件总线的简单实现，只能在单体架构中应用
 */
@Component
public class RocketmqEventBusImpl implements DomainEventBus {
    
    @Value("${rocketmq.nameServer}")
    private String nameServer;
    
    @Value("${kafka.retries}")
    private Integer retries;
    
    @Value("${kafka.batch.size}")
    private Integer batchSize;
    
    @Value("${kafka.linger.ms}")
    private Integer lingerMs;
    
    @Value("${kafka.buffer.memory}")
    private Integer bufferMemory;
    
    private DefaultMQProducer producer;
    
    @PostConstruct
    private void init() throws MQClientException {
        String groupId = "producer-group";
        producer = new DefaultMQProducer(groupId);
        // 2.为生产者设置NameServer的地址
        producer.setNamesrvAddr(nameServer);
        // 3.启动生产者
        producer.start();
    }
    
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    
    private static Logger LOGGER = LoggerFactory.getLogger(RocketmqEventBusImpl.class);
    
    @Override
    public void post(DomainEvent event) {
        try {
            String messageStr = objectMapper.writeValueAsString(event);
            Message message = new Message(event.getEventName(), event.key(), messageStr.getBytes());
            // Message message = new Message(event.getEventName(), event.key(), "1".getBytes());
            SendResult result = producer.send(message, 10000);
        } catch (Exception e) {
            LOGGER.error("error when store event", e);
        }
    }
    
    @Override
    public void register(DomainEventHandler handler) {
        // applicationContext.addApplicationListener(handler);
    }
}
