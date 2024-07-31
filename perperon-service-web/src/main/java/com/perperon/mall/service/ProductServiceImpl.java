package com.perperon.mall.service;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.ProductAttachMapper;
import com.perperon.mall.mapper.ProductMapper;
import com.perperon.mall.mapper.ProductParamMapper;
import com.perperon.mall.pojo.Product;
import com.perperon.mall.pojo.ProductAttach;
import com.perperon.mall.pojo.ProductParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
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

    @Transactional(propagation = Propagation.REQUIRED)
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

    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResult<Product> deleteById(String id){
        productAttachMapper.deleteByProductId(id);
        productParamMapper.deleteByProductId(id);
        int insertCount = getMapper().deleteByPrimaryKey(id);
        return CommonResult.success(null,"删除成功");
    }
}
