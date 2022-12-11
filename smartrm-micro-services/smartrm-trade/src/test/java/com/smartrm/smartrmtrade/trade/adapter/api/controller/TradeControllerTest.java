package com.smartrm.smartrmtrade.trade.adapter.api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smartrm.smartrminfracore.api.CommonResponse;
import com.smartrm.smartrmtrade.trade.application.AppTradeService;
import com.smartrm.smartrmtrade.trade.application.dto.CabinetLockedNotificationDto;
import com.smartrm.smartrmtrade.trade.application.dto.PaymentStateChangeDto;
import com.smartrm.smartrmtrade.trade.application.dto.SelectCommodityCmdDto;
import com.smartrm.smartrmtrade.trade.application.dto.VendingMachineCommodityListDto;
import com.smartrm.smartrmtrade.trade.domain.PaymentQrCode;
import com.smartrm.smartrmtrade.trade.domain.StockedCommodity;
import com.smartrm.smartrmtrade.trade.domain.VendingMachineCommodityList;
import com.smartrm.smartrmtrade.trade.domain.share.PlatformType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author dailj
 * @date 2022/12/9 15:39
 */
// @RunWith(SpringJUnit4ClassRunner.class) 或 @RunWith(MockitoJUnitRunner.class) 或 @RunWith(PowerMockRunner.class)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class TradeControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private ServletContext servletContext;
    
    @Mock
    private AppTradeService tradeService;
    
    @InjectMocks
    TradeController tradeController;
    
    private Gson gson;
    
    @Before
    public void init() {
        /**
         * EnvUtil.setEnvironment(new StandardEnvironment());
         * when(servletContext.getContextPath()).thenReturn("/trade");
         * ReflectionTestUtils.setField(tradeController, "tradeService", tradeService);
         */
        Whitebox.setInternalState(tradeController, "tradeService", tradeService);
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
        gson = (new GsonBuilder()).create();
    }
    
    @Test
    public void testListCommodity() throws Exception {
        doReturn(new VendingMachineCommodityListDto(new VendingMachineCommodityList(0L,
                Arrays.asList(new StockedCommodity("commodityId", "name", "imageUrl", new BigDecimal(0), 0))), null))
                .when(tradeService).queryCommodityList(anyLong());
        
        /**
         * mockMvc.perform(MockMvcRequestBuilders.get("/trade/slot/listCommodity/1")).andExpect(status().isOk())
         *       .andExpect(view().name("")).andExpect(model().attributeExists("data"))
         *       .andExpect(model().attributeExists("code")).andExpect(model().attributeExists("msg"));
         */
        
        MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders.get("/trade/slot/listCommodity/1"))
                .andReturn().getResponse();
        Assert.assertEquals(200, result.getStatus());
        Assert.assertTrue(result.getContentAsString().length() > 0);
        verify(tradeService).queryCommodityList(anyLong());
    }
    
    @Test
    public void testSelectCommodity() throws Exception {
        doReturn(new PaymentQrCode(1L, "url")).when(tradeService)
                .selectCommodity(Mockito.any(SelectCommodityCmdDto.class));
        
        /**
         * mockMvc.perform(MockMvcRequestBuilders.get("/trade/slot/listCommodity/1")).andExpect(status().isOk())
         *       .andExpect(view().name("")).andExpect(model().attributeExists("data"))
         *       .andExpect(model().attributeExists("code")).andExpect(model().attributeExists("msg"));
         */
        SelectCommodityCmdDto selectCommodityCmdDto = new SelectCommodityCmdDto();
        selectCommodityCmdDto.setMachineId(1L);
        selectCommodityCmdDto.setCommodityId("1");
        selectCommodityCmdDto.setPlatformType(null);
        String requestParams = gson.toJson(selectCommodityCmdDto);
        
        MockHttpServletResponse result = mockMvc.perform(
                MockMvcRequestBuilders.post("/trade/slot/select").contentType(MediaType.APPLICATION_JSON)
                        .content(requestParams)).andReturn().getResponse();
        Assert.assertEquals(200, result.getStatus());
        Assert.assertTrue(result.getContentAsString().length() > 0);
        verify(tradeService).selectCommodity(Mockito.any(SelectCommodityCmdDto.class));
    }
    
}
