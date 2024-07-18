package com.perperon.mall.controller;

import com.perperon.mall.pojo.DictionaryType;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.DictionaryTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 字典数据模块
 */
@RestController
@RequestMapping("/dictionarytype")
@Api(tags = "字典数据模块")
public class DictionaryTypeController extends BaseController<DictionaryType>{

    @Autowired
    private DictionaryTypeService dictionaryTypeService;


    @Override
    public BaseService<DictionaryType> getService() {
        return dictionaryTypeService;
    }

}
