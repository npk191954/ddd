package com.smartrm.smartrminfracore.event.listener;

import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smartrminfracore.event.DomainEventListener;

import java.lang.reflect.Constructor;

/**
 * @author dailj
 * @date 2022/12/2 15:17
 */
public class DomainEventListenerFactory {
    
    public static DomainEventListener constructDomainEventListener(DomainEventListener domainEventListenerImpl,
            DomainEventHandler handler, Class eventType, String server)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Constructor domainEventListenerConstroctor = domainEventListenerImpl.getClass()
                .getConstructor(Class.class, DomainEventHandler.class, String.class);
        DomainEventListener listener = (DomainEventListener) domainEventListenerConstroctor
                .newInstance(eventType, handler, server);
        return listener;
    }
}
