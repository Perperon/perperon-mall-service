package com.perperon.mall.mapper;

import com.perperon.mall.pojo.Order;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-08-06
 * @apiNote
 */
public interface OrderMapper extends Mapper<Order> {

    List<Order> listOrderByPage(Map<String,Object> params);
}
