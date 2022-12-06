package com.smartrm.smartrminfracore.event.impl;

import com.smartrm.smartrminfracore.dataobject.MsgConsumeFailRecordDO;
import com.smartrm.smartrminfracore.dataobject.MsgSendFailRecordDO;
import com.smartrm.smartrminfracore.event.FailEventManagable;
import com.smartrm.smartrminfracore.mapper.MsgConsumeFailRecordMapper;
import com.smartrm.smartrminfracore.mapper.MsgSendFailRecordMapper;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 业务消息发送到broker失败时、broker发送到consumer超过最大重试次数消费失败时的管理器
 *
 * @author dailj
 * @date 2022/12/5 13:10
 */
@Component
public class FailEventManager implements FailEventManagable {
    
    
    @Autowired
    MsgSendFailRecordMapper msgSendFailRecordMapper;
    
    @Autowired
    MsgConsumeFailRecordMapper msgConsumeFailRecordMapper;
    
    
    @Override
    public void handleSendFailEvent(Message message) {
        MsgSendFailRecordDO msgSendFailRecordDO = new MsgSendFailRecordDO();
        msgSendFailRecordDO.setTopic(message.getTopic());
        msgSendFailRecordDO.setTags(message.getTags());
        msgSendFailRecordDO.setKeys(message.getKeys());
        msgSendFailRecordDO.setBody(new String(message.getBody()));
        msgSendFailRecordDO.setState(0);
        msgSendFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        
        msgSendFailRecordMapper.add(msgSendFailRecordDO);
    }
    
    @Override
    public void handleConsumeFailEvents(List<? extends Message> messageList) {
        ArrayList<MsgConsumeFailRecordDO> list = new ArrayList();
        for (Message message : messageList) {
            MsgConsumeFailRecordDO msgConsumeFailRecordDO = new MsgConsumeFailRecordDO();
            msgConsumeFailRecordDO.setTopic(message.getTopic());
            msgConsumeFailRecordDO.setTags(message.getTags());
            msgConsumeFailRecordDO.setKeys(message.getKeys());
            msgConsumeFailRecordDO.setBody(new String(message.getBody()));
            msgConsumeFailRecordDO.setState(0);
            msgConsumeFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
            msgConsumeFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
            
            list.add(msgConsumeFailRecordDO);
        }
        msgConsumeFailRecordMapper.addList(list);
    }
    
}
