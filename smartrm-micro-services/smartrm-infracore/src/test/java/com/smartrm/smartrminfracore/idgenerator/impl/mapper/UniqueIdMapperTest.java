package com.smartrm.smartrminfracore.idgenerator.impl.mapper;

import com.smartrm.smartrminfracore.idgenerator.impl.UniqueIdDo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author dailj
 * @date 2022/12/6 15:33
 */
@SpringBootTest
@ActiveProfiles(value = "test-dao")
@RunWith(SpringRunner.class)
public class UniqueIdMapperTest {
    
    @Resource
    private UniqueIdMapper uniqueIdMapper;
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(true)
    public void testNextUniqueId() {
        UniqueIdDo uniqueIdDo = new UniqueIdDo();
        uniqueIdDo.setCreateTime(new Date(System.currentTimeMillis()));
        int result = uniqueIdMapper.nextUniqueId(uniqueIdDo);
        Assert.assertTrue(result > 0);
    
        uniqueIdDo = new UniqueIdDo();
        uniqueIdDo.setCreateTime(new Date(System.currentTimeMillis()));
        result = uniqueIdMapper.nextUniqueId(uniqueIdDo);
        Assert.assertTrue(uniqueIdDo.getId() == 2);
    
        uniqueIdDo = new UniqueIdDo();
        uniqueIdDo.setCreateTime(new Date(System.currentTimeMillis()));
        result = uniqueIdMapper.nextUniqueId(uniqueIdDo);
        Assert.assertTrue(uniqueIdDo.getId() == 3);
    }
}
