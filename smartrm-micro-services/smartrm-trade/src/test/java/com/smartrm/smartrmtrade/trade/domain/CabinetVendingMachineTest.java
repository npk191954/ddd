package com.smartrm.smartrmtrade.trade.domain;

import com.smartrm.smartrminfracore.event.DomainEventBus;
import com.smartrm.smartrminfracore.exception.DomainException;
import com.smartrm.smartrminfracore.idgenerator.UniqueIdGeneratorUtil;
import com.smartrm.smartrmtrade.trade.adapter.remote.TradeCommodityServiceImpl;
import com.smartrm.smartrmtrade.trade.adapter.remote.TradeDeviceServiceImpl;
import com.smartrm.smartrmtrade.trade.adapter.repository.impl.TradeVendingMachineRepositoryImpl;
import com.smartrm.smartrmtrade.trade.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmtrade.trade.domain.service.TradeCommodityService;
import com.smartrm.smartrmtrade.trade.domain.service.TradeDeviceService;
import com.smartrm.smartrmtrade.trade.domain.share.CabinetDoorState;
import com.smartrm.smartrmtrade.trade.domain.share.CommodityInfo;
import com.smartrm.smartrmtrade.trade.domain.share.InventoryInfo;
import com.smartrm.smartrmtrade.trade.domain.share.event.CabinetVendingMachineLockedEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author dailj
 * @date 2022/12/1 21:22
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SlotVendingMachine.class})
public class CabinetVendingMachineTest {
    
    @Mock
    VendingMachineRepository vendingMachineRepository;
    
    @Mock
    TradeDeviceService deviceService;
    
    @Mock
    DomainEventBus eventBus;
    
    @InjectMocks
    CabinetVendingMachine cabinetVendingMachine;
    
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        FieldHelper.setStaticFinalField(TradeVendingMachineRepositoryImpl.TradeVendingMachineRepositoryAspect.class,
                "repository", vendingMachineRepository);
        doNothing().when(vendingMachineRepository).updateCabinetVendingMachine(any());
    }
    
    @Test(expected = DomainException.class)
    public void testOpenWrong() {
        Whitebox.setInternalState(cabinetVendingMachine, "state", CabinetDoorState.Open);
        
        cabinetVendingMachine.open("openId", deviceService);
        verify(cabinetVendingMachine).open("openId", deviceService);
    }
    
    @Test
    public void testOpenRight() throws Exception {
        doNothing().when(deviceService).openCabinetVendingMachine(anyLong());
        Whitebox.setInternalState(cabinetVendingMachine, "state", CabinetDoorState.Locked);
        
        cabinetVendingMachine.open("1", deviceService);
        Assert.assertEquals(cabinetVendingMachine.getCurUserOpenId(), "1");
        Assert.assertEquals(cabinetVendingMachine.getState(), CabinetDoorState.Open);
        verify(deviceService).openCabinetVendingMachine(anyLong());
    }
    
    @Test(expected = DomainException.class)
    public void testOnLockedWrong() {
        TradeCommodityService commodityService = spy(TradeCommodityService.class);
        Whitebox.setInternalState(cabinetVendingMachine, "state", CabinetDoorState.Locked);
        
        cabinetVendingMachine.onLocked(new CabinetVendingMachineLockedEvent(), commodityService);
    }
    
    @Test
    public void testOnLockedRight() throws Exception {
        TradeCommodityService commodityService = Mockito.spy(TradeCommodityService.class);
        doReturn(Arrays.asList(new CommodityInfo("1", null, null, new BigDecimal("1.5")))).when(commodityService)
                .getCommodityList(any());
        UniqueIdGeneratorUtil uniqueIdGeneratorUtil = Mockito.mock(UniqueIdGeneratorUtil.class);
        FieldHelper.setStaticFinalField(UniqueIdGeneratorUtil.class, "instance", uniqueIdGeneratorUtil);
        PowerMockito.doReturn(100L).when(uniqueIdGeneratorUtil, "nextId");
        Whitebox.setInternalState(cabinetVendingMachine, "state", CabinetDoorState.Open);
        Whitebox.setInternalState(cabinetVendingMachine, "machineId", 1L);
        Whitebox.setInternalState(cabinetVendingMachine, "eventBus", eventBus);
        
        CabinetVendingMachineLockedEvent cabinetVendingMachineLockedEvent = new CabinetVendingMachineLockedEvent();
        cabinetVendingMachineLockedEvent.setInventorySnapshotWhenOpen(Arrays.asList(new InventoryInfo("1", 10)));
        cabinetVendingMachineLockedEvent.setInventoryWhenLock(Arrays.asList(new InventoryInfo("1", 9)));
        
        Order result = cabinetVendingMachine.onLocked(cabinetVendingMachineLockedEvent, commodityService);
        Assert.assertEquals(result.getOrderId(), 100);
        verify(commodityService).getCommodityList(any());
        verify(uniqueIdGeneratorUtil).nextId();
    }
    
    @Test
    public void testBuilder() throws Exception {
        CabinetVendingMachine.Builder result = CabinetVendingMachine.Builder();
        Assert.assertNotNull(result);
    }
}
