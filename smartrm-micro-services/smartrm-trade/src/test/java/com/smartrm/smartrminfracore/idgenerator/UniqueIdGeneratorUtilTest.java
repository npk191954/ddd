package com.smartrm.smartrminfracore.idgenerator;

import com.smartrm.smarttrade.trade.adapter.repository.impl.TradeVendingMachineRepositoryImpl;
import com.smartrm.smarttrade.trade.domain.FieldHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * @author dailj
 * @date 2022/12/1 17:41
 */

@RunWith(PowerMockRunner.class)
public class UniqueIdGeneratorUtilTest {
    @Mock
    UniqueIdGeneratorUtil instance;
    @Mock
    UniqueIdGenerator generator;
    @InjectMocks
    UniqueIdGeneratorUtil uniqueIdGeneratorUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInstance() throws Exception {
        // 设置静态无权限属性instance
        FieldHelper.setStaticFinalField(UniqueIdGeneratorUtil.class, "instance", instance);
        
        UniqueIdGeneratorUtil result = UniqueIdGeneratorUtil.instance();
        Assert.assertTrue(result instanceof UniqueIdGeneratorUtil);
    }

    @Test
    public void testNextId() throws Exception {
        when(generator.next()).thenReturn(0L);

        long result = uniqueIdGeneratorUtil.nextId();
        Assert.assertEquals(0L, result);
    }
}
