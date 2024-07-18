package com.perperon.mall.controller;

import com.perperon.mall.pojo.DictionaryData;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.DictionaryDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 字典数据模块
 */
@RestController
@RequestMapping("/dictionary")
@Api(tags = "字典数据模块")
public class DictionaryDataController extends BaseController<DictionaryData>{

    @Autowired
    private DictionaryDataService dictionaryDataService;


    @Override
    public BaseService<DictionaryData> getService() {
        return dictionaryDataService;
    }

    @GetMapping("/getDictionaryByCode/{code}")
    @ApiOperation("根据code查询字典")
    public Object getDictionaryByCode(@PathVariable("code") String code){
       return dictionaryDataService.getDictionaryByCode(code);
    }
}
