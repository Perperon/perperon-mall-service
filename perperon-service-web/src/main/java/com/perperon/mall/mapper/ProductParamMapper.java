package com.perperon.mall.mapper;

import com.perperon.mall.pojo.ProductParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-31
 * @apiNote
 */
public interface ProductParamMapper extends Mapper<ProductParam> {

    int saveList(List<ProductParam> list);

    @Delete("delete from pp_product_param where product_id = #{productId}")
    int  deleteByProductId(@Param("productId") String productId);

    List<ProductParam> getListByProductId(String productId);

    List<ProductParam> listProductParamByPage(Map<String,Object> params);
}
