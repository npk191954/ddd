package com.smartrm.smartrminfracore.event;

import com.smartrm.smartrminfracore.event.listener.RocketmqDomainEventListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * @author dailj
 * @date 2022/12/4 12:38
 */
public class DomainEventListenerAppRunnerPrivateTest {
    
    @Mock
    Logger LOGGER;
    
    @Mock
    ApplicationContext applicationContext;
    
    @Mock
    ExecutorService executorService;
    
    Map<Class, DomainEventListener> listeners;
    
    @Mock
    RocketmqDomainEventListener domainEventListenerImpl;
    
    @Mock
    DomainEventHandler domainEventHandler;
    
    @InjectMocks
    DomainEventListenerAppRunner domainEventListenerAppRunner;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testInitDomainEventListener() throws Exception {}
}
