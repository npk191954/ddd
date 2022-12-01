package com.smartrm.smartrmtradeservice.domain.service;

import com.smartrm.smarttrade.domain.share.UserInfo;

/**
 * @author: yoda
 * @description:
 */
public interface TradeUserService {

  /**
   * 获取用户信息
   *
   * @param openId
   * @return
   */
  UserInfo getUserInfo(String openId);


}
