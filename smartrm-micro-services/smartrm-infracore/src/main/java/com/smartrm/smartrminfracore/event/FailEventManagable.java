package com.smartrm.smartrminfracore.event;

import org.apache.rocketmq.common.message.Message;

import java.util.List;

/**
 * @author dailj
 * @date 2022/12/5 13:13
 */
public interface FailEventManagable {
    
    void handleSendFailEvent(Message message);
    
    void handleConsumeFailEvents(List<? extends Message> message);
}
