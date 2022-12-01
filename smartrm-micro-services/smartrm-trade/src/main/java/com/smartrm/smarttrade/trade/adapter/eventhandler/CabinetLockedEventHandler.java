package com.smartrm.smarttrade.trade.adapter.eventhandler;

import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smarttrade.trade.application.AppTradeService;
import com.smartrm.smarttrade.trade.domain.share.event.CabinetVendingMachineLockedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class CabinetLockedEventHandler extends
    DomainEventHandler<CabinetVendingMachineLockedEvent> {

  @Autowired
  AppTradeService tradeService;

  @Override
  public void onApplicationEvent(CabinetVendingMachineLockedEvent event) {
    tradeService.onCabinetLocked(event);
  }
}
