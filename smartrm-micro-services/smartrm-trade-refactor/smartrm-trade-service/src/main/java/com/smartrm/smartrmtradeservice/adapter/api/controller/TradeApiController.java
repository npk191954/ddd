package com.smartrm.smartrmtradeservice.adapter.api.controller;

//import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetVendingMachineLockedEvent;
import com.smartrm.smartrminfracore.api.CommonResponse;
import com.smartrm.smarttrade.application.AppTradeService;
import com.smartrm.smarttrade.application.dto.CabinetLockedNotificationDto;
import com.smartrm.smarttrade.application.dto.SelectCommodityCmdDto;
import com.smartrm.smarttrade.application.dto.VendingMachineCommodityListDto;
import com.smartrm.smarttrade.domain.PaymentQrCode;
import com.smartrm.smarttrade.domain.share.event.CabinetVendingMachineLockedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/trade")
public class TradeApiController implements TradeApi {

  @Autowired
  private AppTradeService tradeService;

  public CommonResponse<VendingMachineCommodityListDto> listCommodity(
      @PathVariable Long machineId) {
    return CommonResponse.success(tradeService.queryCommodityList(machineId));
  }


}
