package com.smartrm.smartrminfracore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author dailj
 * @date 2022/12/1 10:35
 */
@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrminfracore", "com.smartrm.smartrminfracore.event.impl"})
@MapperScan({"com.smartrm.smartrminfracore.mapper", "com.smartrm.smartrminfracore.idgenerator.impl.mapper"})
public class SmartrmInfracoreApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmInfracoreApplication.class, args);
    }
    
}
