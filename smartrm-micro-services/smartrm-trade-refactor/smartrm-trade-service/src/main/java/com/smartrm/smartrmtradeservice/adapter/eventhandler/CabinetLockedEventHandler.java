package com.smartrm.smartrmtradeservice.adapter.eventhandler;

import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smarttrade.application.AppTradeService;
import com.smartrm.smarttrade.domain.share.event.CabinetVendingMachineLockedEvent;
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
