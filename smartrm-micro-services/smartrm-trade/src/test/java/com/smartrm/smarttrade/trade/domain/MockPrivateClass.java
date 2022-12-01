package com.smartrm.smarttrade.trade.domain;

import com.google.common.collect.Maps;
import com.smartrm.smarttrade.trade.domain.service.TradeDeviceService;
import com.smartrm.smarttrade.trade.domain.share.InventoryInfo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author dailj
 * @date 2022/12/1 15:26
 */
public class MockPrivateClass {
    public String mockPrivateFunc() {
        return  privateFunc();
    }

    private String privateFunc() {
        Integer count = 1;
        return "private func";
    }

    /*private boolean checkInventory(Collection<StockedCommodity> commodities, TradeDeviceService deviceService) {
        return true;
    }*/
}
