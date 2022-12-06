package com.smartrm.smartrminfracore.idgenerator.impl;

import com.smartrm.smartrminfracore.idgenerator.UniqueIdGenerator;
import com.smartrm.smartrminfracore.idgenerator.impl.mapper.UniqueIdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class UniqueIdGeneratorImpl implements UniqueIdGenerator {

  @Autowired
  UniqueIdMapper uniqueIdMapper;

  @Override
  public long next() {
    UniqueIdDo id = new UniqueIdDo();
    uniqueIdMapper.nextUniqueId(id);
    return id.getId();
  }
}
