package com.smartrm.smarttrade.trade.adapter.remote;

import com.smartrm.smartrminfracore.api.CommonError;
import com.smartrm.smartrminfracore.api.CommonResponse;
import com.smartrm.smartrminfracore.exception.DomainException;
import com.smartrm.smarttrade.trade.application.dto.UserInfoDto;
import com.smartrm.smarttrade.trade.application.dto.UserInfoQueryDto;
import com.smartrm.smarttrade.trade.domain.service.TradeUserService;
import com.smartrm.smarttrade.trade.domain.share.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeUserServiceImpl implements TradeUserService {

  @Autowired
  RestTemplate userRestTemplate;

  @Override
  public UserInfo getUserInfo(String openId) {
    UserInfoQueryDto query = new UserInfoQueryDto();
    query.setOpenId(openId);
    ParameterizedTypeReference<CommonResponse<UserInfoDto>> reference = new ParameterizedTypeReference<CommonResponse<UserInfoDto>>() {
    };
    ResponseEntity<CommonResponse<UserInfoDto>> response = userRestTemplate
        .exchange("userInfo", HttpMethod.GET, null, reference, openId);
    if (!response.getStatusCode().is2xxSuccessful()) {
      throw new DomainException(CommonError.UnExpected)
          .withMsg(response.getStatusCode().toString());
    } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
      throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
    }
    UserInfoDto dto = response.getBody().getData();
    return UserInfo.Builder().accountId(dto.getAccountId())
        .contractId(dto.getContractId())
        .wxOpenId(dto.getWxOpenId())
        .wxUnionId(dto.getWxUnionId()).build();
  }
}
