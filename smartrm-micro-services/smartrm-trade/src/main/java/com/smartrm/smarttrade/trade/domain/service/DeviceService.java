package com.smartrm.smarttrade.trade.domain.service;

import com.smartrm.smartrminfracore.exception.DomainException;
import com.smartrm.smarttrade.trade.domain.share.InventoryInfo;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public interface DeviceService {

  /**
   * 获取售卖机库存信息
   *
   * @param machineId
   * @return
   */
  List<InventoryInfo> getInventory(Long machineId);

  /**
   * 弹出商品
   */
  void popCommodity(long machineId, String commodityId, long orderId) throws Exception;

  /**
   * 打开货柜机
   *
   * @param machineId 售卖机id
   */
  void openCabinetVendingMachine(long machineId) throws DomainException;

}
