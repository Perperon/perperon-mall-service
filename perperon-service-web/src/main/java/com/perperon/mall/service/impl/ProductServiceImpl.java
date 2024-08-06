package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.ProductAttachMapper;
import com.perperon.mall.mapper.ProductMapper;
import com.perperon.mall.mapper.ProductParamMapper;
import com.perperon.mall.pojo.Product;
import com.perperon.mall.pojo.ProductAttach;
import com.perperon.mall.pojo.ProductParam;
import com.perperon.mall.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductParamMapper productParamMapper;
    @Resource
    private ProductAttachMapper productAttachMapper;

    @Override
    public Mapper<Product> getMapper() {
        return productMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(productMapper.listByPage(params));
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public CommonResult<Product> create(Product obj) {
        int insertCount = getMapper().insert(obj);
        if (insertCount > 0) {
            //添加商品的附件以及属性参数
            if (obj.getAttachList().size() > 0) {
                List<ProductAttach> attachList = obj.getAttachList().stream()
                        .map(attach -> {
                            attach.setProductId(obj.getId());
                            attach.setUserId(obj.getUserId());
                            return attach;
                        }).collect(Collectors.toList());
                productAttachMapper.saveList(attachList);
            }
        if(obj.getPublicParamList().size() > 0){
            List<ProductParam> paramList = obj.getPublicParamList().stream()
                    .map(param -> {
                        param.setProductId(obj.getId());
                        param.setUserId(obj.getUserId());
                        return param;
                    }).collect(Collectors.toList());
            productParamMapper.saveList(paramList);
        }
        }
        return CommonResult.success(obj);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public CommonResult<Product> update(Product obj){
        //修改商品的附件以及属性参数
        productAttachMapper.deleteByProductId(obj.getId());
        if (obj.getAttachList().size() > 0) {
            List<ProductAttach> attachList = obj.getAttachList().stream()
                    .map(attach -> {
                        attach.setId(null);
                        attach.setProductId(obj.getId());
                        attach.setUserId(obj.getUserId());
                        attach.setUpdatedBy(obj.getUpdatedBy());
                        attach.setUpdated(obj.getUpdated());
                        return attach;
                    }).collect(Collectors.toList());
            productAttachMapper.saveList(attachList);
        }
        productParamMapper.deleteByProductId(obj.getId());
        if(obj.getPublicParamList().size() > 0){
            List<ProductParam> paramList = obj.getPublicParamList().stream()
                    .map(param -> {
                        param.setId(null);
                        param.setProductId(obj.getId());
                        param.setUserId(obj.getUserId());
                        param.setUpdatedBy(obj.getUpdatedBy());
                        param.setUpdated(obj.getUpdated());
                        return param;
                    }).collect(Collectors.toList());
            productParamMapper.saveList(paramList);
        }
        int insertCount = getMapper().updateByPrimaryKeySelective(obj);
        return CommonResult.success(obj,"更新成功");
    }

    public CommonResult<Product> getById(String id){
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        List<Product> products = productMapper.listByPage(params);
        return CommonResult.success(products.get(0),"查询成功");
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public CommonResult<Product> deleteById(String id){
        productAttachMapper.deleteByProductId(id);
        productParamMapper.deleteByProductId(id);
        int insertCount = getMapper().deleteByPrimaryKey(id);
        return CommonResult.success(null,"删除成功");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public CommonResult<Product> updateStatus(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
        return CommonResult.success(product,"更新成功");
    }
}
