package com.smartrm.smartrminfracore.event.listener;

import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smartrminfracore.event.DomainEventListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class RocketmqDomainEventListener extends DomainEventListener {

    private DefaultMQPushConsumer consumer;

//    @Value("${rocketmq.nameServer}")
//    private String nameServer;

    // private static final String groupId = "consumer-group";

    public RocketmqDomainEventListener() {
        super();
    }

    public RocketmqDomainEventListener(Class eventType, DomainEventHandler handler, String server) {
        /*this.eventType = eventType;
        this.handler = handler;
        this.server = server;*/
        super(eventType, handler, server);
        consumer = new DefaultMQPushConsumer(groupId);
        consumer.setNamesrvAddr(this.server);
    }

    @Override
    public void run() {
        try {
            consumer.subscribe(eventType.getSimpleName(), "*");
            // while (!isClose) {
                consumer.registerMessageListener(new MessageListenerConcurrently() {
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                        System.out.println("message count =====> " + list.size());
//                        System.out.println("message=====> " + list);
                        for (MessageExt record : list) {
                            LOGGER.info("listener receive msg:{}", record.getBody());
                            try {
                                DomainEvent event = (DomainEvent) objectMapper.readValue(record.getBody(), eventType);
                                handler.onApplicationEvent(event);
                            } catch (Exception e) {
                                LOGGER.error("fail to handle event.", e);
                                LOGGER.info("failed event:{}", record.getBody());
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
