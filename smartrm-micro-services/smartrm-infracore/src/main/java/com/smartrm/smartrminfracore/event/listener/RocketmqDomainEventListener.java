package com.smartrm.smartrminfracore.event.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smartrminfracore.event.DomainEventListener;
import com.smartrm.smartrminfracore.event.FailEventManagable;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.time.LocalDateTime;
import java.util.List;

public class RocketmqDomainEventListener extends DomainEventListener {
    
    private static final Integer MAX_RECONSUME_TIMES = 3;
    
    private DefaultMQPushConsumer consumer;
    
    private static final Gson GSON;
    
    static {
        GSON = (new GsonBuilder()).create();
    }
    
    //    @Value("${rocketmq.nameServer}")
    //    private String nameServer;
    
    // private static final String groupId = "consumer-group";
    
    public RocketmqDomainEventListener() {
        super();
    }
    
    public RocketmqDomainEventListener(Class eventType, DomainEventHandler handler, String server,
            FailEventManagable failEventManager) {
        /*this.eventType = eventType;
        this.handler = handler;
        this.server = server;*/
        super(eventType, handler, server, failEventManager);
        consumer = new DefaultMQPushConsumer(groupId);
        consumer.setMaxReconsumeTimes(MAX_RECONSUME_TIMES);
        consumer.setNamesrvAddr(this.server);
    }
    
    @Override
    public void run() {
        try {
            consumer.subscribe(eventType.getSimpleName(), "*");
            // while (!isClose) {
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                        ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    // System.out.println("message count =====> " + list.size());
                    // System.out.println("message=====> " + list);
                    for (MessageExt record : list) {
                        LOGGER.info("listener receive msg:{}", record.getBody());
                        try {
                            DomainEvent event = (DomainEvent) objectMapper.readValue(record.getBody(), eventType);
                            // DomainEvent event = gson.fromJson(new String(record.getBody()), DomainEvent.class);
                            handler.onApplicationEvent(event);
                        } catch (Exception e) {
                            LOGGER.info("time: {}, message reconsume times:{}", LocalDateTime.now(),
                                    record.getReconsumeTimes());
                            /* LOGGER.error("fail to handle event.", e);
                            LOGGER.info("failed event:{}", record.getBody()); */
                            
                            if (record.getReconsumeTimes() >= MAX_RECONSUME_TIMES) {
                                failEventManager.handleConsumeFailEvents(list);
                                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            }
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            // }
        } catch (MQClientException e) {
            LOGGER.error("fail to handle event.", e);
        }
    }
}
