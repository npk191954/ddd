package com.smartrm.smarttrade.trade;

import com.smartrm.smartrminfracore.event.DomainEventBus;
import com.smartrm.smarttrade.trade.application.AppTradeService;
import com.smartrm.smarttrade.trade.domain.share.VendingMachineType;
import com.smartrm.smarttrade.trade.domain.share.event.DeviceFailureEvent;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartrmTradeApplicationTests {

  Logger LOGGER = LoggerFactory.getLogger(SmartrmTradeApplicationTests.class);

  @Autowired
  @Qualifier("simpleEventBusImpl")
  private DomainEventBus domainEventBus;

  @Test
  void contextLoads() {
  }

  @Autowired
  AppTradeService tradeService;

  @Test
  public void testScheduler() {
    DeviceFailureEvent event = new DeviceFailureEvent();
    event.setMachineType(VendingMachineType.SLOT);
    event.setMachineId(1L);
    event.setOrderId(25L);
    tradeService.onDeviceFailure(event);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
