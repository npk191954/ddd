package com.smartrm.smartrmtrade.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 启动类
 *
 * @author dailj
 * @date 2022/12/1 10:35
 */
@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrmtrade.trade", "com.smartrm.smartrminfracore",
        "com.smartrm.smartrminfracore.event.impl"}, exclude = {SecurityAutoConfiguration.class})
@MapperScan({"com.smartrm.smartrmtrade.trade.infrastructure.mapper", "com.smartrm.infracore.idgenerator.impl.mapper",
        "com.smartrm.smartrminfracore.idgenerator.impl.mapper"})
/** @EnableGlobalMethodSecurity(prePostEnabled = true)
 * @EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
 */ public class SmartrmTradeApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmTradeApplication.class, args);
    }
    
}
