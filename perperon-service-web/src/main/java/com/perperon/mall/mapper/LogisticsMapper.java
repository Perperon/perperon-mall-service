package com.perperon.mall.mapper;

import com.perperon.mall.pojo.Logistics;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-08-14
 * @apiNote
 */
public interface LogisticsMapper extends Mapper<Logistics> {

    List<Logistics> listLogisticsByPage(Map<String,Object> params);


    List<Logistics> getByLogisticsNo(String  logisticsNo);

}
