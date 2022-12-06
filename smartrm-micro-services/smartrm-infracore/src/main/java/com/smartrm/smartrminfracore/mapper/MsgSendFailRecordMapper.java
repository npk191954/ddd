package com.smartrm.smartrminfracore.mapper;

import com.smartrm.smartrminfracore.dataobject.MsgConsumeFailRecordDO;
import com.smartrm.smartrminfracore.dataobject.MsgSendFailRecordDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author dailj
 * @date 2022/12/6 10:45
 */

@Mapper
public interface MsgSendFailRecordMapper {
    
    @Select({"SELECT * from msg_send_fail_record where id=#{id}"})
    MsgSendFailRecordDO selectOne(Long id);
    
    @Insert({
            "insert into `msg_send_fail_record` ( "
                    + "topic, "
                    + "tags, "
                    + "keys, "
                    + "body, "
                    + "`state`, "
                    + "create_time, "
                    + "update_time) values ("
                    + "#{topic}, "
                    + "#{tags}, "
                    + "#{keys}, "
                    + "#{body}, "
                    + "#{state}, "
                    + "#{createTime}, "
                    + "#{updateTime})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(MsgSendFailRecordDO msgSendFailRecordDO);
    
    @Insert({
            "<script> insert into `msg_send_fail_record` ( "
                    + "id, "
                    + "topic, "
                    + "tags, "
                    + "keys, "
                    + "body, "
                    + "`state`, "
                    + "create_time, "
                    + "update_time) values "
                    + "<foreach collection='list' item='item' index='index' separator=','>"
                    + "(#{item.id}, #{item.topic}, #{item.tags}, #{item.keys}, #{item.body}, #{item.state}, "
                    + "#{item.createTime}, #{item.updateTime})"
                    + "</foreach>"
                    + "ON DUPLICATE KEY "
                    + "UPDATE update_time = VALUES(update_time)"
                    + "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addList(@Param(value="list") List<MsgSendFailRecordDO> list);
    
    @Update({
            "update `msg_send_fail_record` set "
                    + "`state` = #{state}, "
                    + "update_time = #{updateTime} where id=#{id}"})
    int updateMsgSendFailRecord(MsgSendFailRecordDO msgSendFailRecordDO);
    
    @Insert({
            "insert into `msg_send_fail_record` ( "
                    + "id, "
                    + "topic, "
                    + "tags, "
                    + "keys, "
                    + "body, "
                    + "`state`, "
                    + "create_time, "
                    + "update_time) values ("
                    + "#{id}, "
                    + "#{topic}, "
                    + "#{tags}, "
                    + "#{keys}, "
                    + "#{body}, "
                    + "#{state}, "
                    + "#{createTime}, "
                    + "#{updateTime}) on duplicate key update `state` = #{state}, update_time = #{updateTime}"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addOrUpdate(MsgSendFailRecordDO msgSendFailRecordDO);
    
}
