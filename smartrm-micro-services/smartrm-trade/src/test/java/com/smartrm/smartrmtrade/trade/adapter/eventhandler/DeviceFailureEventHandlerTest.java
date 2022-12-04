package com.smartrm.smartrmtrade.trade.adapter.eventhandler;

import com.smartrm.smartrmtrade.trade.domain.share.event.DeviceFailureEvent;
import com.smartrm.smartrmtrade.trade.application.AppTradeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/12/4 16:24
 */
public class DeviceFailureEventHandlerTest {
    
    @Mock
    Logger LOGGER;
    
    @Mock
    AppTradeService tradeService;
    
    @InjectMocks
    DeviceFailureEventHandler deviceFailureEventHandler;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testOnApplicationEvent() throws Exception {
        doNothing().when(tradeService).onDeviceFailure(any());
        
        deviceFailureEventHandler.onApplicationEvent(new DeviceFailureEvent());
        verify(tradeService).onDeviceFailure(any());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
