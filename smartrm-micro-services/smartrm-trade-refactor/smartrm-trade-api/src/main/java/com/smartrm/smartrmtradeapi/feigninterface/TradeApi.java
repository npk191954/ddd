package com.smartrm.smartrmtradeapi.feigninterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ***********************************************************
 * Copyright  2019 八维通科技有限公司 Inc.All rights reserved.  *
 * ***********************************************************
 *
 * description:
 * @author zdf
 * @date 2019-9-24 21:18:36
 */
@FeignClient(value = "bas-mc", fallbackFactory = McMsgApiFallbackFactory.class)
@Api()
public interface TradeApi {

    /**
     * 分页获取推送消息
     * @param mcMsgPageQueryDTO {@link McMsgPageQueryDTO}
     * @return
     */
    @PostMapping(value = "/bas/mc/v1/msg/page-list", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "queryForPage",notes = "分页获取推送消息")
    Result<Page<McMsgResponseDTO>> queryForPage(@RequestBody McMsgPageQueryDTO mcMsgPageQueryDTO);

    /**
     * 获取用户 限定时间内未失效的集合
     * @param mcMsgQueryDTO
     * @return
     */
    @PostMapping(value = "/bas/mc/v1/msg/limit-time-list", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "queryListWithLimitTime",notes = "获取用户 限定时间内未失效的集合")
    Result<List<McMsgResponseDTO>> queryListWithLimitTime(@RequestBody McMsgQueryDTO mcMsgQueryDTO);

}
