package com.smartrm.smarttrade.trade.event;

import com.smartrm.smartrminfracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class TestEvent extends DomainEvent {


  public TestEvent() {
    super("test.event");
  }

  @Override
  public String key() {
    return null;
  }
}
