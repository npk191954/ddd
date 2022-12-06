package com.smartrm.smartrminfracore.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dailj
 * @date 2022/12/6 22:52
 */
@Configuration
public class SmartrmConfiguration {
    
    public static final int CORE_POOL_SIZE = 5;
    
    public static final int MAXIMUM_POOL_SIZE = 200;
    
    public static final long KEEP_ALIVE_TIME = 0L;
    
    public static final int BLOCKING_QUEUE_CAPACITY = 1024;
    
    @Bean
    public ExecutorService executorService() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(BLOCKING_QUEUE_CAPACITY), namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        
        return pool;
    }
    
    @Bean
    public Gson gson() {
        Gson gson = (new GsonBuilder()).create();
        return gson;
    }
    
}
