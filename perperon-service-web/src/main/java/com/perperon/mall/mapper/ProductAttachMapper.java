package com.perperon.mall.mapper;

import com.perperon.mall.pojo.ProductAttach;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author dupengcheng
 * @date 2024-07-31
 * @apiNote
 */
public interface ProductAttachMapper extends Mapper<ProductAttach> {

    int saveList(List<ProductAttach> list);

    @Delete("delete from pp_product_attach where product_id = #{productId}")
    int  deleteByProductId(@Param("productId") String productId);

    List<ProductAttach> getListByProductId(String productId);
}
