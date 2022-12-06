package com.smartrm.smartrminfracore.event.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smartrminfracore.event.FailEventManagable;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/12/6 19:30
 */
public class RocketmqDomainEventListenerTest {
    
    @Mock
    DefaultMQPushConsumer consumer;
    
    @Mock
    Logger LOGGER;
    
    @Mock
    DomainEventHandler handler;
    
    @Mock
    FailEventManagable failEventManager;
    
    @Mock
    ObjectMapper objectMapper;
    
    RocketmqDomainEventListener rocketmqDomainEventListener;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
}

