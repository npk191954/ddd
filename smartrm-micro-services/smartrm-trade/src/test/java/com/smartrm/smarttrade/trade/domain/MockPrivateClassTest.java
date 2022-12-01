package com.smartrm.smarttrade.trade.domain;

import com.smartrm.smarttrade.trade.domain.service.TradeDeviceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collection;

/**
 * @author dailj
 * @date 2022/12/1 15:27
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockPrivateClass.class})
public class MockPrivateClassTest {
    private MockPrivateClass mockPrivateClass;

    @Test
    public void testMockPrivateFunc() throws Exception {
        mockPrivateClass = PowerMockito.spy(new MockPrivateClass());

        // PowerMockito.when(mockPrivateClass, "privateFunc").thenReturn("test");
        PowerMockito.doReturn("test").when(mockPrivateClass, "privateFunc");
        

        String realResult = mockPrivateClass.mockPrivateFunc();

        Assert.assertEquals("test", realResult);

        PowerMockito.verifyPrivate(mockPrivateClass, Mockito.times(1)).invoke("privateFunc");

    }
    
    @Test
//    @PrepareForTest({SlotVendingMachine.class})
    public void testCheckInventory() {
        Assert.assertTrue(true);
    }
    
}
