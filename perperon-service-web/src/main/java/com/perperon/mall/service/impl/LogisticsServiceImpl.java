package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.LogisticsMapper;
import com.perperon.mall.pojo.Logistics;
import com.perperon.mall.service.LogisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-08-14
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class LogisticsServiceImpl implements LogisticsService {

    @Resource
    private LogisticsMapper logisticsMapper;

    @Override
    public Mapper<Logistics> getMapper() {
        return logisticsMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(logisticsMapper.listLogisticsByPage(params));
    }

    @Override
    public CommonResult<List<Logistics>> getByLogisticsNo(String logisticsNo) {
        return CommonResult.success(logisticsMapper.getByLogisticsNo(logisticsNo));
    }
}
