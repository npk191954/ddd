package com.smartrm.smartrmtrade.trade.domain;

import com.smartrm.smartrminfracore.event.DomainEventBus;
import com.smartrm.smartrminfracore.exception.DomainException;
import com.smartrm.smartrminfracore.idgenerator.UniqueIdGeneratorUtil;
import com.smartrm.smartrmtrade.trade.domain.event.OrderCreatedEvent;
import com.smartrm.smartrmtrade.trade.domain.service.TradePayService;
import com.smartrm.smartrmtrade.trade.adapter.repository.impl.TradeVendingMachineRepositoryImpl;
import com.smartrm.smartrmtrade.trade.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmtrade.trade.domain.service.TradeDeviceService;
import com.smartrm.smartrmtrade.trade.domain.share.InventoryInfo;
import com.smartrm.smartrmtrade.trade.domain.share.PlatformType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * SlotVendingMachine的私有方法测试类，与非私有方法独立出来
 *
 * @author dailj
 * @date 2022/11/30 22:37
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SlotVendingMachine.class})
public class SlotVendingMachinePrivateTest {
    
    @Mock
    Order curOrder;
    
    @Mock
    DomainEventBus eventBus;
    
    @Mock
    TradeDeviceService deviceService;
    
    @Mock
    TradePayService payService;
    
    @Mock
    VendingMachineRepository vendingMachineRepository;
    
    private SlotVendingMachine vendingMachine;
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        FieldHelper.setStaticFinalField(TradeVendingMachineRepositoryImpl.TradeVendingMachineRepositoryAspect.class,
                "repository", vendingMachineRepository);
        doNothing().when(vendingMachineRepository).updateSlotVendingMachine(any());
    }
    
    @Test(expected = DomainException.class)
    public void testSelectCommodityNoInventory() throws Exception {
        /// 每个函数内进行独立性初始化
        /**
         * DomainEventBus eventBus = Mockito.mock(DomainEventBus.class);
         * TradeDeviceService deviceService = Mockito.mock(TradeDeviceService.class);
         * TradePayService payService = Mockito.mock(TradePayService.class);
         * VendingMachineRepository vendingMachineRepository = Mockito.mock(VendingMachineRepository.class);
         * FieldHelper.setStaticFinalField(TradeVendingMachineRepositoryImpl.TradeVendingMachineRepositoryAspect.class,
         *      "repository", vendingMachineRepository);
         * doNothing().when(vendingMachineRepository).updateSlotVendingMachine(any());
         */
        
        vendingMachine = PowerMockito.spy(SlotVendingMachine.Builder().eventBus(eventBus).build());
        Whitebox.setInternalState(vendingMachine, "state", SlotVendingMachineState.Ready);
        // Whitebox.setInternalState(vendingMachine, "eventBus", eventBus);
        
        PowerMockito.doReturn(false)
                .when(vendingMachine, "checkInventory", Mockito.<java.util.Collection<StockedCommodity>>any(),
                        Mockito.<TradeDeviceService>any());
        
        vendingMachine.selectCommodity(Arrays.asList(new StockedCommodity("1", null, null, null, 0)), deviceService,
                payService, PlatformType.Wechat);
    }
    
    @Test
    public void testSelectCommodity() throws Exception {
        /// 每个函数内进行独立性初始化
        /**
         * Order curOrder = Mockito.mock(Order.class);
         * DomainEventBus eventBus = Mockito.mock(DomainEventBus.class);
         * TradeDeviceService deviceService = Mockito.mock(TradeDeviceService.class);
         * TradePayService payService = Mockito.mock(TradePayService.class);
         * VendingMachineRepository vendingMachineRepository = Mockito.mock(VendingMachineRepository.class);
         * FieldHelper.setStaticFinalField(TradeVendingMachineRepositoryImpl.TradeVendingMachineRepositoryAspect.class,
         *        "repository", vendingMachineRepository);
         * doNothing().when(vendingMachineRepository).updateSlotVendingMachine(any());
         */
        doReturn(new PaymentQrCode(1L, "url")).when(payService).startQrCodePayForOrder(any(), any());
        
        vendingMachine = PowerMockito.spy(SlotVendingMachine.Builder().eventBus(eventBus).build());
        Whitebox.setInternalState(vendingMachine, "state", SlotVendingMachineState.Ready);
        
        PowerMockito.doReturn(true)
                .when(vendingMachine, "checkInventory", Mockito.<java.util.Collection<StockedCommodity>>any(),
                        Mockito.<TradeDeviceService>any());
        PowerMockito.doReturn(curOrder).when(vendingMachine, "generateOrder", any());
        /** PowerMockito.doNothing().when(vendingMachine, "emitEvent", any());
         * eventBus.post(new OrderCreatedEvent(this.machineId, curOrder));
         */
        doNothing().when(eventBus).post(any());
        
        PaymentQrCode paymentQrCode = vendingMachine
                .selectCommodity(Arrays.asList(new StockedCommodity("1", null, null, null, 0)), deviceService,
                        payService, PlatformType.Wechat);
        Assert.assertTrue(paymentQrCode.getCodeUrl().length() > 0);
        verify(eventBus).post(any());
    }
    
    @Test
    public void testGenerateOrder() throws Exception {
        UniqueIdGeneratorUtil uniqueIdGeneratorUtil = Mockito.mock(UniqueIdGeneratorUtil.class);
        FieldHelper.setStaticFinalField(UniqueIdGeneratorUtil.class, "instance", uniqueIdGeneratorUtil);
        PowerMockito.doReturn(1L).when(uniqueIdGeneratorUtil, "nextId");
        vendingMachine = PowerMockito.spy(SlotVendingMachine.Builder().eventBus(eventBus).build());
        Whitebox.setInternalState(vendingMachine, "machineId", 1L);
        
        Order order = Whitebox.invokeMethod(vendingMachine, "generateOrder",
                Arrays.asList(new StockedCommodity("commodityId", null, null, null, 1)));
        Assert.assertTrue(order.getCommodities().size() > 0);
        Mockito.verify(uniqueIdGeneratorUtil).nextId();
    }
    
    @Test
    public void testCheckInventoryRight() throws Exception {
        ArrayList<InventoryInfo> inventoryInfoList = new ArrayList();
        InventoryInfo inventoryInfo = new InventoryInfo("1", 1);
        inventoryInfoList.add(inventoryInfo);
        doReturn(inventoryInfoList).when(deviceService).getInventory(anyLong());
        vendingMachine = PowerMockito.spy(SlotVendingMachine.Builder().eventBus(eventBus).build());
        
        boolean result = Whitebox.invokeMethod(vendingMachine, "checkInventory",
                Arrays.asList(new StockedCommodity("1", null, null, null, 1)), deviceService);
        Assert.assertTrue(result);
        Mockito.verify(deviceService).getInventory(anyLong());
    }
    
    @Test
    public void testCheckInventoryWrong() throws Exception {
        ArrayList<InventoryInfo> inventoryInfoList = new ArrayList();
        InventoryInfo inventoryInfo = new InventoryInfo("commodityId", 1);
        inventoryInfoList.add(inventoryInfo);
        doReturn(inventoryInfoList).when(deviceService).getInventory(anyLong());
        vendingMachine = PowerMockito.spy(SlotVendingMachine.Builder().eventBus(eventBus).build());
        
        boolean result = Whitebox.invokeMethod(vendingMachine, "checkInventory",
                Arrays.asList(new StockedCommodity("1", null, null, null, 1)), deviceService);
        Assert.assertFalse(result);
        Mockito.verify(deviceService).getInventory(anyLong());
    }
    
}


