package com.smartrm.smartrmtradeservice.adapter.remote;

import com.smartrm.smartrminfracore.exception.DomainException;
import com.smartrm.smartrmmonolith.device.application.service.VendingMachineDeviceService;
import com.smartrm.smarttrade.application.dto.PopCommodityCmdDto;
import com.smartrm.smarttrade.domain.service.TradeDeviceService;
import com.smartrm.smarttrade.domain.share.InventoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeDeviceServiceImpl implements TradeDeviceService {

  @Autowired
  VendingMachineDeviceService vendingMachineDeviceService;

  @Override
  public List<InventoryInfo> getInventory(long machineId) {
    return vendingMachineDeviceService.getInventory(machineId).stream().map(
        d -> new InventoryInfo(d.getCommodityId(),
            d.getCount())).collect(
        Collectors.toList());
  }

  @Override
  public void popCommodity(long machineId, String commodityId, long orderId)
      throws Exception {
    PopCommodityCmdDto cmd = new PopCommodityCmdDto();
    cmd.setCommodityId(commodityId);
    cmd.setMachineId(machineId);
    cmd.setOrderId(orderId);
    vendingMachineDeviceService.popCommodity(cmd);
  }

  @Override
  public void openCabinetVendingMachine(long machineId) throws DomainException {
    vendingMachineDeviceService.openCabinetVendingMachine(machineId);
  }
}
