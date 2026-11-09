package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.OrderMapper;
import com.perperon.mall.pojo.Order;
import com.perperon.mall.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-08-06
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public Mapper<Order> getMapper() {
        return orderMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(orderMapper.listOrderByPage(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResult<Order> updateStatus(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
        return CommonResult.success(order, "更新成功");
    }
}
