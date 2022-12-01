package com.smartrm.smartrmtradeservice.adapter.remote;

import com.smartrm.smartrmmonolith.user.application.service.AppUserService;
import com.smartrm.smarttrade.application.dto.UserInfoDto;
import com.smartrm.smarttrade.application.dto.UserInfoQueryDto;
import com.smartrm.smarttrade.domain.service.TradeUserService;
import com.smartrm.smarttrade.domain.share.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeUserServiceImpl implements TradeUserService {

  @Autowired
  AppUserService userService;

  @Override
  public UserInfo getUserInfo(String openId) {
    UserInfoQueryDto query = new UserInfoQueryDto();
    query.setOpenId(openId);
    UserInfoDto dto = userService.getUserInfo(query);
    return UserInfo.Builder().accountId(dto.getAccountId())
        .contractId(dto.getContractId())
        .wxOpenId(dto.getWxOpenId())
        .wxUnionId(dto.getWxUnionId()).build();
  }
}
