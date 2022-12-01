package com.smartrm.smartrmtradeservice.adapter.remote;

import com.smartrm.smartrmmonolith.payment.domain.AccountInfo;
import com.smartrm.smarttrade.application.dto.PaymentQrCodeDto;
import com.smartrm.smarttrade.domain.Order;
import com.smartrm.smarttrade.domain.PaymentQrCode;
import com.smartrm.smarttrade.domain.service.TradePayService;
import com.smartrm.smarttrade.domain.share.OrderInfo;
import com.smartrm.smarttrade.domain.share.PlatformType;
import com.smartrm.smarttrade.domain.share.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradePayServiceImpl implements TradePayService {

  @Autowired
  com.smartrm.smartrmmonolith.payment.application.service.PayService payService;


  @Override
  public PaymentQrCode startQrCodePayForOrder(PlatformType platformType, Order order) {
    PaymentQrCodeDto dto = payService
        .startQrCodePayForOrder(
            platformType,
            OrderInfo.Builder()
                .machineId(order.getMachineId())
                .orderId(order.getOrderId())
                .type(order.getType())
                .totalAmount(order.totalAmount())
                .build()
        );
//    order.setPaymentId(dto.getPaymentId());
    return new PaymentQrCode(dto.getPaymentId(), dto.getCodeUrl());
  }

  @Override
  public void chargeForOrder(OrderInfo order, UserInfo userInfo) {
    payService
        .chargeForOrder(order, new AccountInfo(userInfo.getAccountId(), userInfo.getContractId()));
  }
}
