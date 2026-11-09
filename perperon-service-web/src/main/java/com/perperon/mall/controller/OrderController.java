package com.perperon.mall.controller;

import com.perperon.mall.annotation.PreAuthorize;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Order;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */

@RestController
@RequestMapping("/order")
@Api(tags = "订单列表模块")
public class OrderController extends BaseController<Order>{
    @Autowired
    private OrderService orderService;

    @Override
    public BaseService<Order> getService() {
        return orderService;
    }

    @PutMapping("/updateStatus")
    @ApiOperation("更新订单状态")
    @PreAuthorize("@ps.hasPerm('order:update')")
    public CommonResult<Order> updateStatus(@RequestBody Order obj){
        return orderService.updateStatus(obj);
    }
}
