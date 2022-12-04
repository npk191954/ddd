package com.smartrm.smartrminfracore.event;

import com.smartrm.smartrminfracore.event.listener.RocketmqDomainEventListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/12/4 12:38
 */
public class DomainEventListenerAppRunnerTest {
    
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
    public void testRun() throws Exception {
        MockEventHandler mockEventHandler = new MockEventHandler();
        listeners = Mockito.spy(new HashMap<>());
        doReturn(new HashMap() {
            {
                put("1", mockEventHandler);
            }
        }).when(applicationContext).getBeansOfType(any());
        doNothing().when(executorService).execute(any());
        Whitebox.setInternalState(domainEventListenerAppRunner, "server", "localhost:9876");
        Whitebox.setInternalState(domainEventListenerAppRunner, "listeners", listeners);
        ApplicationArguments arguments = Mockito.mock(ApplicationArguments.class);
        
        domainEventListenerAppRunner.run(arguments);
        Assert.assertTrue(listeners.containsKey(MockEvent.class));
        Assert.assertTrue(listeners.get(MockEvent.class) instanceof RocketmqDomainEventListener);
        verify(applicationContext).getBeansOfType(any());
        verify(listeners).put(any(), any());
        verify(executorService).execute(any());
        
    }
    
    private class MockEventHandler extends DomainEventHandler<MockEvent> {
        
        @Override
        public void onApplicationEvent(MockEvent event) {
            
        }
    }
    
    private class MockEvent extends DomainEvent {
        
        public MockEvent(Object source) {
            super(source);
        }
        
        @Override
        public String key() {
            return "1";
        }
    }
}
