package com.smartrm.smarttrade.trade.event;

import com.smartrm.smartrminfracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class TestEvent1 extends DomainEvent {

  public TestEvent1() {
    super("test.event1");
  }

  @Override
  public String key() {
    return null;
  }
}
