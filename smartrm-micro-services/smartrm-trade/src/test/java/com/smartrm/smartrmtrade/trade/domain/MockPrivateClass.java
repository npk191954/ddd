package com.smartrm.smartrmtrade.trade.domain;

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
