package com.smartrm.smartrminfracore.event;

/**
 * @author: yoda
 * @description:事件总线
 */
public interface DomainEventBus {

  void post(DomainEvent event);

  void register(DomainEventHandler handler);

}
