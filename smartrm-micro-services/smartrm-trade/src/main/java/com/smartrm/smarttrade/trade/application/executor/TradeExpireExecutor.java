package com.smartrm.smarttrade.trade.application.executor;

import com.smartrm.smartrminfracore.scheduler.RetryExecutorBase;
import com.smartrm.smarttrade.trade.domain.SlotVendingMachine;
import com.smartrm.smarttrade.trade.domain.repository.VendingMachineRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description: 货道售卖机交易过期任务执行器
 */
@Component
public class TradeExpireExecutor extends RetryExecutorBase {

  @Autowired
  private VendingMachineRepository machineRepository;

  @Override
  public void run(Map<String, Object> params) {
    Long orderId = (Long) params.get("orderId");
    Long machineId = (Long) params.get("machineId");
    SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(machineId);
    if (machine != null && machine.getCurOrder() != null
        && machine.getCurOrder().getOrderId() == orderId) {
      machine.cancelOrder();
    }
  }
}
