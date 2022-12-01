package com.smartrm.smartrminfracore.event.listener;

import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smartrminfracore.event.DomainEventListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaDomainEventListener extends DomainEventListener {

    private KafkaConsumer<String, String> kafkaConsumer;

//    @Value("${kafka.server}")
    private static String groupId = "smartrm";

    private Properties props;

    public KafkaDomainEventListener() {

    }

    public KafkaDomainEventListener(Class eventType, DomainEventHandler handler, String server) {
        this.eventType = eventType;
        this.handler = handler;
        this.server = server;
        props = new Properties();
        props.setProperty("bootstrap.servers", this.server);
        props.setProperty("group.id", groupId);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<String, String>(props);
    }

    @Override
    public void run() {
        kafkaConsumer.subscribe(Arrays.asList(eventType.getSimpleName()));
        while (!isClose) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                LOGGER.info("listener receive msg:{}", record.value());
                try {
                    DomainEvent event = (DomainEvent) objectMapper.readValue(record.value(), eventType);
                    handler.onApplicationEvent(event);
                } catch (Exception e) {
                    LOGGER.error("fail to handle event.", e);
                    LOGGER.info("failed event:{}", record.value());
                }
            }
            kafkaConsumer.commitAsync();
        }
    }
}
