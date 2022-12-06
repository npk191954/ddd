package com.smartrm.smartrminfracore.idgenerator.impl.mapper;

import com.smartrm.smartrminfracore.idgenerator.impl.UniqueIdDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface UniqueIdMapper {

  @Insert({"insert into `unique_id_generator` (`create_time`) values (#{createTime})"})
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int nextUniqueId(UniqueIdDo uniqueIdDo);
}
