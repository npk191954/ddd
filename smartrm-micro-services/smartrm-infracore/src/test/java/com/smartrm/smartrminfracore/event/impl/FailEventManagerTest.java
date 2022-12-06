package com.smartrm.smartrminfracore.event.impl;

import com.smartrm.smartrminfracore.mapper.MsgSendFailRecordMapper;
import org.apache.rocketmq.common.message.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * @author dailj
 * @date 2022/12/6 11:01
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FailEventManager.class})
public class FailEventManagerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FailEventManagerTest.class);
    
    @Mock
    MsgSendFailRecordMapper msgSendFailRecordMapper;
    
    @InjectMocks
    FailEventManager failEventManager;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testHandleSendFailEvent() throws Exception {
        doReturn(1).when(msgSendFailRecordMapper).add(any());
        
        Message message = new Message();
        message.setTopic("DeviceFailureEvent");
        message.setTags("1");
        message.setKeys("1");
        message.setBody("msg str".getBytes());
        
        failEventManager.handleSendFailEvent(message);
        verify(msgSendFailRecordMapper).add(any());
    }
    
}
