package com.smartrm.smartrmtradeservice.domain.service;

import com.smartrm.smarttrade.domain.Order;
import com.smartrm.smarttrade.domain.PaymentQrCode;
import com.smartrm.smarttrade.domain.share.OrderInfo;
import com.smartrm.smarttrade.domain.share.PlatformType;
import com.smartrm.smarttrade.domain.share.UserInfo;

/**
 * @author: yoda
 * @description:
 */
public interface TradePayService {

  /**
   * 开始扫码支付，获取支付二维码
   *
   * @param platformType 平台类型，目前固定为微信
   * @param order        订单
   * @return 支付二维码
   */
  PaymentQrCode startQrCodePayForOrder(PlatformType platformType, Order order);

  /**
   * 免密扣款
   *
   * @param order    订单信息
   * @param userInfo 用户信息
   */
  void chargeForOrder(OrderInfo order, UserInfo userInfo);

}
