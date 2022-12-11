package com.smartrm.smartrminfracore.idgenerator;

import com.smartrm.smartrminfracore.FieldHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * 对UniqueIdGeneratorUtil类的静态方法测试
 *
 * @author dailj
 * @date 2022/12/1 17:41
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UniqueIdGeneratorUtil.class})
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
        /**
         * 配合@PrepareForTest注解使用。
         * 模拟静态方法调用，先模拟对应的UniqueIdGeneratorUtil类，再模拟对应的静态方法。
         */
        PowerMockito.mockStatic(UniqueIdGeneratorUtil.class);
        PowerMockito.doReturn(instance).when(UniqueIdGeneratorUtil.class, "instance");
        
        /**
         * 模拟final类，需要配合@PrepareForTest注解使用。
         */
        UniqueIdGeneratorUtil result = UniqueIdGeneratorUtil.instance();
        Assert.assertTrue(result == instance);
    }
    
    @Test
    public void testNextId() throws Exception {
        when(generator.next()).thenReturn(0L);
        
        long result = uniqueIdGeneratorUtil.nextId();
        Assert.assertEquals(0L, result);
    }
}
