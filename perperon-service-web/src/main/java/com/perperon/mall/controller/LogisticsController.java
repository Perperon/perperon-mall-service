package com.perperon.mall.controller;

import com.perperon.mall.pojo.Logistics;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.LogisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-08-14
 * @apiNote
 */
@RestController
@RequestMapping("/logistics")
@Api(tags = "物流管理模块")
public class LogisticsController extends BaseController<Logistics>{

    @Autowired
    private LogisticsService logisticsService;

    @Override
    public BaseService<Logistics> getService() {
        return logisticsService;
    }

    @GetMapping("/getByLogisticsNo/{logisticsNo}")
    @ApiOperation(value = "根据物流单号查询物流详情", notes = "根据物流单号查询物流详情")
    public Object getByLogisticsNo(@PathVariable("logisticsNo") String logisticsNo){
        return logisticsService.getByLogisticsNo(logisticsNo);
    }
}
