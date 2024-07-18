package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.DictionaryData;

/**
 * @author dupengcheng
 * @date 2024-07-18
 * @apiNote
 */
public interface DictionaryDataService extends BaseService<DictionaryData>{

    CommonResult<DictionaryData> getDictionaryByCode(String code);
}
