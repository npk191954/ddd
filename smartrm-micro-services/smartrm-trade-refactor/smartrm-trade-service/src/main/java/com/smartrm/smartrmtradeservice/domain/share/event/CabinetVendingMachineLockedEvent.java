package com.smartrm.smartrmtradeservice.domain.share.event;

import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smarttrade.domain.share.InventoryInfo;

import java.util.List;

/**
 * @author: yoda
 * @description: 货柜机柜门锁定事件
 */
public class CabinetVendingMachineLockedEvent extends DomainEvent {

  private Long machineId;
  //  private String userOpenId;
  private List<InventoryInfo> inventorySnapshotWhenOpen;
  private List<InventoryInfo> inventoryWhenLock;

  public CabinetVendingMachineLockedEvent() {
    super("CabinetVendingMachineLockedEvent");
  }

  public Long getMachineId() {
    return machineId;
  }

  public void setMachineId(Long machineId) {
    this.machineId = machineId;
  }

  public List<InventoryInfo> getInventorySnapshotWhenOpen() {
    return inventorySnapshotWhenOpen;
  }

  public void setInventorySnapshotWhenOpen(
      List<InventoryInfo> inventorySnapshotWhenOpen) {
    this.inventorySnapshotWhenOpen = inventorySnapshotWhenOpen;
  }

  public List<InventoryInfo> getInventoryWhenLock() {
    return inventoryWhenLock;
  }

  public void setInventoryWhenLock(
      List<InventoryInfo> inventoryWhenLock) {
    this.inventoryWhenLock = inventoryWhenLock;
  }

  @Override
  public String key() {
    return machineId.toString();
  }

}
