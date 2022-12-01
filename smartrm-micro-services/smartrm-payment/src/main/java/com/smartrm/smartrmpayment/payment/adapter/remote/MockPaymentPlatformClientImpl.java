package com.smartrm.smartrmpayment.payment.adapter.remote;

import com.smartrm.smartrmpayment.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmpayment.payment.application.dto.PlatformPaymentResultDto;
import com.smartrm.smartrmpayment.payment.application.dto.PlatformResultCode;
import com.smartrm.smartrmpayment.payment.application.remote.PaymentPlatformClient;
import com.smartrm.smartrmpayment.payment.application.service.PayService;
import com.smartrm.smartrmpayment.payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class MockPaymentPlatformClientImpl implements PaymentPlatformClient {

  @Autowired
  private PayService payService;

  @Override
  public PaymentQrCodeDto queryQrCode(Payment payment) {
    return new PaymentQrCodeDto(
        payment.getPaymentId(),
        "orderId:" + payment.getOrder().getOrderId() + ";" + "paymentId:" + payment.getPaymentId()
    );
  }

  @Override
  public void requestForDeduction(Payment payment) {
    //直接返回支付成功
    PlatformPaymentResultDto resultDto = new PlatformPaymentResultDto();
    resultDto.setPlatformType(payment.getPlatformType());
    resultDto.setOrderId(payment.getOrder().getOrderId());
    resultDto.setResultCode(PlatformResultCode.Success);
    payService.onPaymentResult(resultDto);
  }

  @Override
  public void requestForRefund(Payment payment) {
    PlatformPaymentResultDto resultDto = new PlatformPaymentResultDto();
    resultDto.setPlatformType(payment.getPlatformType());
    resultDto.setOrderId(payment.getOrder().getOrderId());
    resultDto.setResultCode(PlatformResultCode.Success);
    payService.onRefundResult(resultDto);
  }
}
