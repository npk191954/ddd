package com.smartrm.smartrmtradeservice.adapter.remote;

import com.smartrm.smarttrade.application.dto.CommodityInfoDto;
import com.smartrm.smarttrade.domain.service.TradeCommodityService;
import com.smartrm.smarttrade.domain.share.CommodityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeCommodityServiceImpl implements TradeCommodityService {

  @Autowired
  com.smartrm.smartrmmonolith.commodity.application.service.CommodityService remoteService;

  @Override
  public CommodityInfo getCommodityDetail(String commodityId) {
    CommodityInfoDto dto = remoteService.getCommodityDetail(commodityId);
    return new CommodityInfo(dto.getCommodityId(), dto.getCommodityName(), dto.getImageUrl(),
        dto.getPrice());
  }

  @Override
  public List<CommodityInfo> getCommodityList(List<String> commodityIds) {
    List<CommodityInfoDto> dtos = remoteService.getCommodityList(commodityIds);
    return dtos.stream().map(
        dto -> new CommodityInfo(dto.getCommodityId(), dto.getCommodityName(), dto.getImageUrl(),
            dto.getPrice())).collect(Collectors.toList());
  }
}
