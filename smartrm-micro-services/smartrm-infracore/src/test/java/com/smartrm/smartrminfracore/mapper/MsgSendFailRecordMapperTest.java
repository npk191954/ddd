package com.smartrm.smartrminfracore.mapper;

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
public class MsgSendFailRecordMapperTest {
    
    @Resource
    private MsgSendFailRecordMapper msgSendFailRecordMapper;
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testSelectOne() {
        MsgSendFailRecordDO msgSendFailRecordDO = msgSendFailRecordMapper.selectOne(1L);
        Assert.assertTrue(msgSendFailRecordDO.getId() > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testAdd() {
        MsgSendFailRecordDO msgSendFailRecordDO = new MsgSendFailRecordDO();
        msgSendFailRecordDO.setTopic("CabinetVendingMachineLockedEvent");
        msgSendFailRecordDO.setTags("1");
        msgSendFailRecordDO.setKeys("1");
        msgSendFailRecordDO.setState(0);
        msgSendFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setBody("msg str");
        Integer result = msgSendFailRecordMapper.add(msgSendFailRecordDO);
        Assert.assertTrue(result > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testAddList() {
        ArrayList<MsgSendFailRecordDO> list = new ArrayList();
        MsgSendFailRecordDO msgSendFailRecordDO = new MsgSendFailRecordDO();
        msgSendFailRecordDO.setTopic("CabinetVendingMachineLockedEvent");
        msgSendFailRecordDO.setTags("1");
        msgSendFailRecordDO.setKeys("1");
        msgSendFailRecordDO.setState(0);
        msgSendFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setBody("msg str");
        list.add(msgSendFailRecordDO);
        
        msgSendFailRecordDO = new MsgSendFailRecordDO();
        msgSendFailRecordDO.setId(1L);
        msgSendFailRecordDO.setTopic("DeviceFailureEvent");
        msgSendFailRecordDO.setTags("1");
        msgSendFailRecordDO.setKeys("1");
        msgSendFailRecordDO.setState(0);
        msgSendFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setBody("msg str");
        list.add(msgSendFailRecordDO);
        
        Integer result = msgSendFailRecordMapper.addList(list);
        Assert.assertTrue(result > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testAddOrUpdate() {
        MsgSendFailRecordDO msgSendFailRecordDO = new MsgSendFailRecordDO();
        msgSendFailRecordDO.setId(1L);
        msgSendFailRecordDO.setTopic("DeviceFailureEvent");
        msgSendFailRecordDO.setTags("1");
        msgSendFailRecordDO.setKeys("1");
        msgSendFailRecordDO.setBody("msg str");
        msgSendFailRecordDO.setState(1);
        msgSendFailRecordDO.setCreateTime(new Date(System.currentTimeMillis()));
        msgSendFailRecordDO.setUpdateTime(new Date(System.currentTimeMillis()));
        Integer result = msgSendFailRecordMapper.addOrUpdate(msgSendFailRecordDO);
        Assert.assertTrue(result > 0);
    }
    
}
    
