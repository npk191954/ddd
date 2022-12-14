package com.smartrm.smartrmtradeservice.application.executor;

import com.smartrm.smartrminfracore.scheduler.RetryExecutorBase;
import com.smartrm.smarttrade.domain.SlotVendingMachine;
import com.smartrm.smarttrade.domain.SlotVendingMachineState;
import com.smartrm.smarttrade.domain.repository.VendingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
public class PoppingExpireExecutor extends RetryExecutorBase {

  @Autowired
  private VendingMachineRepository machineRepository;

  @Override
  public void run(Map<String, Object> params) {
    Long orderId = (Long) params.get("orderId");
    Long machineId = (Long) params.get("machineId");
    SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(machineId);
    if (machine != null && machine.getState() == SlotVendingMachineState.Popping
        && machine.getCurOrder() != null && machine.getCurOrder().getOrderId() == orderId) {
      //TODO: 此时更完备的做法是请求一次IOT设备状态
      machine.ready();
    }
  }
}
