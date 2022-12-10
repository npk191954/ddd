package com.smartrm.smartrminfracore.idgenerator.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

/**
 * @author: yoda
 * @description:
 */
public class UniqueIdDo {
    
    private Long id;
    
    private Date createTime;
    
    // @PostConstruct
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // @PreDestroy
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
