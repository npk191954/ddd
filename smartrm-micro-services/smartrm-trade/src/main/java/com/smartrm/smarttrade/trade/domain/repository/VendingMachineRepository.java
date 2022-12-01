package com.smartrm.smarttrade.trade.domain.repository;

import com.smartrm.smarttrade.trade.domain.CabinetVendingMachine;
import com.smartrm.smarttrade.trade.domain.SlotVendingMachine;

/**
 * @author: yoda
 * @description:
 */
public interface VendingMachineRepository {

  SlotVendingMachine getSlotVendingMachineById(long id);

  CabinetVendingMachine getCabinetVendingMachineById(long id);

  void updateSlotVendingMachine(SlotVendingMachine machine);

  void updateCabinetVendingMachine(CabinetVendingMachine machine);

}
