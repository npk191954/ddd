package com.smartrm.smartrmtradeservice.domain.repository;

import com.smartrm.smarttrade.domain.CabinetVendingMachine;
import com.smartrm.smarttrade.domain.SlotVendingMachine;

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
