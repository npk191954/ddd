package com.smartrm.smartrminfracore.dataobject;

import java.util.Date;

/**
 * 消息超过最大次数消费失败记录
 *
 * @author dailj
 * @date 2022/12/6 10:50
 */
public class MsgConsumeFailRecordDO {
    
    private Long id;
    
    private String topic;
    
    private String tags;
    
    private String keys;
    
    private String body;
    
    /**
     * 状态，0 未消费，1 重新消费成功
     */
    private Integer state;
    
    private Date createTime;
    
    private Date updateTime;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public String getKeys() {
        return keys;
    }
    
    public void setKeys(String keys) {
        this.keys = keys;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
