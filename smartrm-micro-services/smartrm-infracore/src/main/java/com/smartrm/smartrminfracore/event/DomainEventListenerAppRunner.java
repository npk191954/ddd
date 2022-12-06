package com.smartrm.smartrminfracore.event;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.smartrm.smartrminfracore.event.listener.DomainEventListenerFactory;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class DomainEventListenerAppRunner implements ApplicationRunner {
    
    private static Logger LOGGER = LoggerFactory.getLogger(DomainEventListenerAppRunner.class);
    
    @Resource
    private ApplicationContext applicationContext;
    
    @Value("${mq.server}")
    private String server;
    
    @Resource
    // private ExecutorService executorService = Executors.newCachedThreadPool();
    private ExecutorService executorService;
    
    private Map<Class, DomainEventListener> listeners = new ConcurrentHashMap<>();
    
    private DomainEventListener domainEventListenerImpl;
    
    @Resource
    private FailEventManagable failEventManager;
    
    //  @Value("${kafka.server}")
    //  private String bootstrapServer;
    //
    //  private static String groupId = "smartrm";
    
    
    @PostConstruct
    private void initDomainEventListener() throws ClassNotFoundException {
        ServiceLoader<DomainEventListener> serviceLoads = ServiceLoader.load(DomainEventListener.class);
        Iterator<DomainEventListener> iterator = serviceLoads.iterator();
        DomainEventListener domainEventListenerImpl = null;
        while (iterator.hasNext()) {
            domainEventListenerImpl = iterator.next();
        }
        if (domainEventListenerImpl == null) {
            throw new ClassNotFoundException();
        }
        this.domainEventListenerImpl = domainEventListenerImpl;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //        DomainEventListener domainEventListenerImpl = getDomainEventListener();
        Collection<DomainEventHandler> handlers = applicationContext.getBeansOfType(DomainEventHandler.class).values();
        for (DomainEventHandler handler : handlers) {
            ParameterizedType parameterizedType = (ParameterizedType) handler.getClass().getGenericSuperclass();
            Class eventType = (Class) (parameterizedType.getActualTypeArguments()[0]);
            //      DomainEventListener listener = new DomainEventListener(props, eventType, handler);
            //            DomainEventListener listener = new KafkaDomainEventListener(eventType, handler);
            //            if (true) {
            //                listener = new RocketmqDomainEventListener(eventType, handler);
            //            }
            
            ///
            /**
             *  DomainEventListener listener = domainEventListenerImpl;
             *  domainEventListenerImpl.setEventType(eventType);
             *  domainEventListenerImpl.setHandler(handler);
             *  domainEventListenerImpl.setServer(server);
             */
            DomainEventListener listener = DomainEventListenerFactory
                    .constructDomainEventListener(domainEventListenerImpl, handler, eventType, server,
                            failEventManager);
            
            listeners.put(eventType, listener);
            executorService.execute(listener);
            LOGGER.info("created listener for event:{}", eventType.getSimpleName());
        }
        
    }
}
