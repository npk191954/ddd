package com.smartrm.smartrmtradeservice.adapter.eventhandler;

import com.smartrm.smartrminfracore.event.DomainEventHandler;
import com.smartrm.smarttrade.domain.SlotVendingMachine;
import com.smartrm.smarttrade.domain.repository.VendingMachineRepository;
import com.smartrm.smarttrade.domain.share.event.PopSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description: 商品弹出成功事件处理
 */
@Component
public class PopSuccessEventHandler extends DomainEventHandler<PopSuccessEvent> {

  @Autowired
  private VendingMachineRepository machineRepository;

  @Override
  public void onApplicationEvent(PopSuccessEvent event) {
    long machineId = event.getMachineId();
    SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(machineId);
    if (machine != null) {
      machine.onPopSuccess(event);
    }
  }
}
