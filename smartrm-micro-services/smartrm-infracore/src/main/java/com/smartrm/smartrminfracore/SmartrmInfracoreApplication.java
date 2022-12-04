package com.smartrm.smartrminfracore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author dailj
 * @date 2022/12/1 10:35
 */
@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrminfracore", "com.smartrm.smartrminfracore.event.impl"})
public class SmartrmInfracoreApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmInfracoreApplication.class, args);
    }
    
}
