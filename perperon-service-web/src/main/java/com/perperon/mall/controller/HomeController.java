package com.perperon.mall.controller;

import com.perperon.mall.annotation.PreAuthorize;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.AccountMapper;
import com.perperon.mall.mapper.OrderMapper;
import com.perperon.mall.mapper.ProductMapper;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.pojo.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-08-06
 * @apiNote 首页数据看板
 */
@RestController
@RequestMapping("/home")
@Api(tags = "首页数据看板模块")
public class HomeController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/dashboard")
    @ApiOperation("首页看板统计数据")
    @PreAuthorize("@ps.hasPerm('*:list')")
    public CommonResult<Map<String, Object>> dashboard() {
        Map<String, Object> data = new HashMap<>();
        data.put("orderCount", orderMapper.countOrder());
        data.put("todayOrderCount", orderMapper.countTodayOrder());
        data.put("orderAmount", orderMapper.sumOrderPrice());
        data.put("productCount", productMapper.selectCount(new Product()));
        data.put("accountCount", accountMapper.selectCount(new Account()));
        List<Map<String, Object>> orderTrend = orderMapper.orderTrend();
        data.put("orderTrend", orderTrend);
        return CommonResult.success(data);
    }
}