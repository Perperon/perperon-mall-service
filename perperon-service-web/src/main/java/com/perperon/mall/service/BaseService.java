package com.perperon.mall.service;


import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.common.utils.Help;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2023/5/15
 * @apiNote
 */
public interface BaseService<T> {

     Mapper<T> getMapper();

     PageInfo listByPage(Map<String,Object> params);

    @Transactional(propagation = Propagation.REQUIRED)
    default CommonResult<T> create(T obj){
        int insertCount = getMapper().insert(obj);
        return CommonResult.success(obj);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default CommonResult<T> update(T obj){
        int insertCount = getMapper().updateByPrimaryKeySelective(obj);
        return CommonResult.success(obj,"更新成功");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    default CommonResult<T> delete(T obj){
        int insertCount = getMapper().delete(obj);
        return CommonResult.success(obj,"删除成功");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default CommonResult<T> deleteBatches(String[] ids){
        for (String id:ids){
            int insertCount = getMapper().deleteByPrimaryKey(id);
        }
        return CommonResult.success(null,"全部删除成功");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    default CommonResult<T> deleteById(String id){
        int insertCount = getMapper().deleteByPrimaryKey(id);
        return CommonResult.success(null,"删除成功");
    }

    default List<T> getListByPage(Map<String,Object> params){
        List<T> data;
        if (ObjectUtil.isEmpty(params)){
            Help.startPage(params);
            data = getMapper().selectAll();
        }else{
            data = new ArrayList<>();
        }
        return data;
    }

    default List<T> listByObj(T t) {
        return getMapper().select(t);
    }

    default CommonResult<T> getById(String id){
        return CommonResult.success(getMapper().selectByPrimaryKey(id),"查询成功");
    }
}
