package com.smartrm.smartrmtrade.trade.infrastructure.mapper;

import com.smartrm.smartrmtrade.trade.infrastructure.dataobject.TradeSlotVendingMachineDo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author dailj
 * @date 2022/12/2 11:45
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test-dao")
@SpringBootTest(/*webEnvironment = SpringBootTest.WebEnvironment.NONE*/)
public class TradeSlotVendingMachineMapperTest {
    
    @Resource
    private TradeSlotVendingMachineMapper tradeSlotVendingMachineMapper;
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testSelectOne() {
        TradeSlotVendingMachineDo tradeSlotVendingMachineDo = tradeSlotVendingMachineMapper.selectOne(1L);
        Assert.assertTrue(tradeSlotVendingMachineDo.getCurOrderId() > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testUpdateRight() {
        TradeSlotVendingMachineDo tradeSlotVendingMachineDo = new TradeSlotVendingMachineDo();
        tradeSlotVendingMachineDo.setMachineId(1L);
        tradeSlotVendingMachineDo.setCurOrderId(10L);
        tradeSlotVendingMachineDo.setState(2);
        tradeSlotVendingMachineDo.setVersion(2);
        int result = tradeSlotVendingMachineMapper.update(tradeSlotVendingMachineDo);
        Assert.assertTrue(result > 0);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testUpdateWrong() {
        TradeSlotVendingMachineDo tradeSlotVendingMachineDo = new TradeSlotVendingMachineDo();
        tradeSlotVendingMachineDo.setMachineId(1L);
        tradeSlotVendingMachineDo.setCurOrderId(10L);
        tradeSlotVendingMachineDo.setState(2);
        tradeSlotVendingMachineDo.setVersion(1);
        int result = tradeSlotVendingMachineMapper.update(tradeSlotVendingMachineDo);
        Assert.assertFalse(result > 0);
    }
}

