package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Logistics;

import java.util.List;

/**
 * @author dupengcheng
 * @date 2024-08-14
 * @apiNote
 */
public interface LogisticsService extends BaseService<Logistics>{

    CommonResult<List<Logistics>> getByLogisticsNo(String logisticsNo);
}
