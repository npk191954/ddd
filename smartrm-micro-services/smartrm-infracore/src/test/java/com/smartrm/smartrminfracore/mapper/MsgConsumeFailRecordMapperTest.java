package com.smartrm.smartrminfracore.mapper;

import com.smartrm.smartrminfracore.dataobject.MsgConsumeFailRecordDO;
import com.smartrm.smartrminfracore.dataobject.MsgSendFailRecordDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author dailj
 * @date 2022/12/6 14:28
 */
@SpringBootTest
@ActiveProfiles(value = "test-dao")
@RunWith(SpringRunner.class)
public class MsgConsumeFailRecordMapperTest {
    
    @Resource
    private MsgConsumeFailRecordMapper msgConsumeFailRecordMapper;
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testSelectOne() {
        MsgConsumeFailRecordDO msgConsumeFailRecordDO = msgConsumeFailRecordMapper.selectOne(1L);
        Assert.assertTrue(msgConsumeFailRecordDO.getId() > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testAdd() {
        MsgConsumeFailRecordDO msgConsumeFailRecordDO = new MsgConsumeFailRecordDO();
        msgConsumeFailRecordDO.setTopic("CabinetVendingMachineLockedEvent");
        msgConsumeFailRecordDO.setTags("1");
        msgConsumeFailRecordDO.setKeys("1");
        msgConsumeFailRecordDO.setState(0);
        msgConsumeFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setBody("msg str");
        Integer result = msgConsumeFailRecordMapper.add(msgConsumeFailRecordDO);
        Assert.assertTrue(result > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testAddList() {
        ArrayList<MsgConsumeFailRecordDO> list = new ArrayList();
        MsgConsumeFailRecordDO msgConsumeFailRecordDO = new MsgConsumeFailRecordDO();
        msgConsumeFailRecordDO.setTopic("CabinetVendingMachineLockedEvent");
        msgConsumeFailRecordDO.setTags("1");
        msgConsumeFailRecordDO.setKeys("1");
        msgConsumeFailRecordDO.setState(0);
        msgConsumeFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setBody("msg str");
        list.add(msgConsumeFailRecordDO);
    
        msgConsumeFailRecordDO = new MsgConsumeFailRecordDO();
        msgConsumeFailRecordDO.setId(1L);
        msgConsumeFailRecordDO.setTopic("DeviceFailureEvent");
        msgConsumeFailRecordDO.setTags("1");
        msgConsumeFailRecordDO.setKeys("1");
        msgConsumeFailRecordDO.setState(0);
        msgConsumeFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setBody("msg str");
        list.add(msgConsumeFailRecordDO);
        
        Integer result = msgConsumeFailRecordMapper.addList(list);
        Assert.assertTrue(result > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testAddOrUpdate() {
        MsgConsumeFailRecordDO msgConsumeFailRecordDO = new MsgConsumeFailRecordDO();
        msgConsumeFailRecordDO.setId(1L);
        msgConsumeFailRecordDO.setTopic("DeviceFailureEvent");
        msgConsumeFailRecordDO.setTags("1");
        msgConsumeFailRecordDO.setKeys("1");
        msgConsumeFailRecordDO.setBody("msg str");
        msgConsumeFailRecordDO.setState(1);
        msgConsumeFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgConsumeFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        Integer result = msgConsumeFailRecordMapper.addOrUpdate(msgConsumeFailRecordDO);
        Assert.assertTrue(result > 0);
    }
    
}
    
