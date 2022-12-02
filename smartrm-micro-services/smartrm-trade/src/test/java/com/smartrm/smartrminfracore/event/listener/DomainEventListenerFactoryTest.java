package com.smartrm.smartrminfracore.event.listener;

import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smartrminfracore.event.DomainEventListener;
import com.smartrm.smartrmtrade.trade.adapter.eventhandler.CabinetLockedEventHandler;
import com.smartrm.smartrmtrade.trade.domain.SlotVendingMachine;
import com.smartrm.smartrmtrade.trade.domain.share.event.CabinetVendingMachineLockedEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author dailj
 * @date 2022/12/2 16:30
 */
@RunWith(MockitoJUnitRunner.class)
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({DomainEventListenerFactory.class})
public class DomainEventListenerFactoryTest {
    
    @Mock
    DomainEventHandler domainEventHandler;
    
    @Mock
    CabinetVendingMachineLockedEvent cabinetVendingMachineLockedEvent;
    
    @Mock
    RocketmqDomainEventListener rocketmqDomainEventListener;
    
    @Mock
    KafkaDomainEventListener kafkaDomainEventListener;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testConstructRocketmqDomainEventListener() throws Exception {
        DomainEventListener result = DomainEventListenerFactory
                .constructDomainEventListener(rocketmqDomainEventListener, domainEventHandler,
                        cabinetVendingMachineLockedEvent.getClass(), "localhost:100");
        Assert.assertTrue(result instanceof RocketmqDomainEventListener);
    }
    
    @Test
    public void testConstructKafkaDomainEventListener() throws Exception {
        DomainEventListener result = DomainEventListenerFactory
                .constructDomainEventListener(kafkaDomainEventListener, domainEventHandler,
                        cabinetVendingMachineLockedEvent.getClass(), "localhost:100");
        Assert.assertTrue(result instanceof KafkaDomainEventListener);
    }
}