package com.smartrm.smartrminfracore.event.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartrm.smartrminfracore.event.DomainEvent;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/12/3 18:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketmqEventBusImplTest {
    
    @SpyBean
    FailEventManager failEventManager;
    
    @Mock
    DefaultMQProducer producer;
    
    @SpyBean
    RocketmqEventBusImpl rocketmqEventBusImpl;
    
    @Mock
    ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testPostRight() throws Exception {
        Whitebox.setInternalState(rocketmqEventBusImpl, "failEventManager", failEventManager);
        Whitebox.setInternalState(rocketmqEventBusImpl, "objectMapper", objectMapper);
        DomainEvent domainEvent = Mockito.mock(DomainEvent.class);
        // doReturn("DomainEvent").when(domainEvent).getEventName();
        doReturn("DeviceFailureEvent").when(domainEvent).getEventName();
        doReturn("1").when(domainEvent).key();
        doReturn("2").when(domainEvent).getEventId();
        doReturn("message str").when(objectMapper).writeValueAsString(any());
        doNothing().when(failEventManager).handleSendFailEvent(any());
        /*DeviceFailureEvent deviceFailureEvent = new DeviceFailureEvent();
        deviceFailureEvent.setMachineId(1L);*/
        
        rocketmqEventBusImpl.post(domainEvent);
        verify(domainEvent).getEventName();
        verify(domainEvent).key();
        verify(domainEvent).getEventId();
        verify(failEventManager, times(0)).handleSendFailEvent(any());
    }
    
    @Test
    public void testPostWrong() throws Exception {
        Whitebox.setInternalState(rocketmqEventBusImpl, "failEventManager", failEventManager);
        Whitebox.setInternalState(rocketmqEventBusImpl, "producer", producer);
        Whitebox.setInternalState(rocketmqEventBusImpl, "objectMapper", objectMapper);
        DomainEvent domainEvent = Mockito.mock(DomainEvent.class);
        // doReturn("DomainEvent").when(domainEvent).getEventName();
        doReturn("DeviceFailureEvent").when(domainEvent).getEventName();
        doReturn("1").when(domainEvent).key();
        doReturn("2").when(domainEvent).getEventId();
        doReturn("message str").when(objectMapper).writeValueAsString(any());
        doThrow(MQClientException.class).when(producer).send(any(Message.class));
        doNothing().when(failEventManager).handleSendFailEvent(any());
        
        rocketmqEventBusImpl.post(domainEvent);
        verify(domainEvent).getEventName();
        verify(domainEvent).key();
        verify(domainEvent).getEventId();
        verify(failEventManager).handleSendFailEvent(any());
    }
}
