package com.smartrm.smartrmtradeservice.application.dto;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public class ListCommodityByIdQueryDto {

  private List<String> ids;

  public List<String> getIds() {
    return ids;
  }

  public void setIds(List<String> ids) {
    this.ids = ids;
  }
}
