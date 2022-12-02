package com.smartrm.smartrmtrade.trade.domain;

import com.smartrm.smartrminfracore.event.DomainEventBus;
import com.smartrm.smartrminfracore.exception.DomainException;
import com.smartrm.smartrmtrade.trade.domain.service.TradeCommodityService;
import com.smartrm.smartrmtrade.trade.adapter.repository.impl.TradeVendingMachineRepositoryImpl;
import com.smartrm.smartrmtrade.trade.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmtrade.trade.domain.service.TradeDeviceService;
import com.smartrm.smartrmtrade.trade.domain.service.TradePayService;
import com.smartrm.smartrmtrade.trade.domain.share.InventoryInfo;
import com.smartrm.smartrmtrade.trade.domain.share.PlatformType;
import com.smartrm.smartrmtrade.trade.domain.share.event.PopSuccessEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * SlotVendingMachine类的公有方法测试类
 *
 * @author dailj
 * @date 2022/11/30 22:37
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SlotVendingMachine.class})
public class SlotVendingMachineTest {
    
    @Mock
    Logger LOGGER;
    
    //Field state of type SlotVendingMachineState - was not mocked since Mockito doesn't mock enums
    @Mock
    Order curOrder;
    
    @Mock
    DomainEventBus eventBus;
    
    @Mock
    TradeDeviceService deviceService;
    
    @Mock
    TradeCommodityService commodityService;
    
    @Mock
    TradePayService payService;
    
    @Mock
    VendingMachineRepository vendingMachineRepository;
    
    @InjectMocks
    SlotVendingMachine slotVendingMachine;
    
    private SlotVendingMachine vendingMachine;
    
    /*@Spy
    SlotVendingMachine vendingMachine;*/ TradeVendingMachineRepositoryImpl.TradeVendingMachineRepositoryAspect tradeVendingMachineRepositoryAspect;
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // MockitoAnnotations.openMocks(this);
        Whitebox.setInternalState(slotVendingMachine, "curOrder", curOrder);
        Whitebox.setInternalState(slotVendingMachine, "eventBus", eventBus);
        FieldHelper.setStaticFinalField(TradeVendingMachineRepositoryImpl.TradeVendingMachineRepositoryAspect.class,
                "repository", vendingMachineRepository);
    }
    
    @Test
    public void testFinishOrder() throws Exception {
        when(curOrder.getCommodities())
                .thenReturn(Arrays.<StockedCommodity>asList(new StockedCommodity("commodityId", null, null, null, 0)));
        when(curOrder.getOrderId()).thenReturn(0L);
        when(curOrder.getMachineId()).thenReturn(0L);
        doNothing().when(curOrder).succeed();
        ArrayList<InventoryInfo> inventoryInfoList = new ArrayList();
        inventoryInfoList.add(new InventoryInfo());
        when(deviceService.getInventory(anyLong())).thenReturn(inventoryInfoList);
        doNothing().when(deviceService).popCommodity(anyLong(), anyString(), anyLong());
        doNothing().when(vendingMachineRepository).updateSlotVendingMachine(any());
        
        slotVendingMachine.finishOrder(0L, deviceService);
    }
    
    @Test
    public void testGetCommodityList() throws Exception {
        ArrayList<InventoryInfo> inventoryInfoList = new ArrayList();
        inventoryInfoList.add(new InventoryInfo());
        when(deviceService.getInventory(anyLong())).thenReturn(inventoryInfoList);
        Whitebox.setInternalState(slotVendingMachine, "machineId", 1L);
        
        VendingMachineCommodityList result = slotVendingMachine.getCommodityList(deviceService, commodityService);
        Assert.assertTrue(Integer.valueOf(result.commodities().size()).equals(0));
    }
    
    @Test(expected = DomainException.class)
    public void testSelectCommodityNotReady() {
        Whitebox.setInternalState(slotVendingMachine, "state", SlotVendingMachineState.Popping);
        doReturn(new PaymentQrCode(1L, "url")).when(payService).startQrCodePayForOrder(any(), any());
        
        slotVendingMachine
                .selectCommodity(Arrays.asList(new StockedCommodity("commodityId", null, null, null, 0)), deviceService,
                        payService, PlatformType.Wechat);
    }
    
    @Test
    public void testCancelOrderWrong() {
        when(curOrder.getState()).thenReturn(OrderState.Canceled);
        slotVendingMachine.cancelOrder();
    }
    
    @Test
    public void testCancelOrderRight() {
        doNothing().when(eventBus).post(any());
        slotVendingMachine.cancelOrder();
    }
    
    
    @Test
    public void testOnPopSuccess() throws Exception {
        when(curOrder.getOrderId()).thenReturn(0L);
        Whitebox.setInternalState(slotVendingMachine, "state", SlotVendingMachineState.Popping);
        slotVendingMachine.onPopSuccess(new PopSuccessEvent());
    }
    
    @Test
    public void testReady() throws Exception {
        Whitebox.setInternalState(slotVendingMachine, "state", SlotVendingMachineState.Ready);
        slotVendingMachine.ready();
    }
    
    @Test
    public void testNotReady() throws Exception {
        Whitebox.setInternalState(slotVendingMachine, "state", SlotVendingMachineState.Popping);
        slotVendingMachine.ready();
    }
    
    @Test
    public void testBuilder() throws Exception {
        SlotVendingMachine.Builder result = SlotVendingMachine.Builder();
        Assert.assertNotNull(result);
    }
}


