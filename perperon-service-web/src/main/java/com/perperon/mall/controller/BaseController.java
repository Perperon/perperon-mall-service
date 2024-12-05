package com.perperon.mall.controller;

import com.perperon.mall.annotation.PreAuthorize;
import com.perperon.mall.common.response.CommonPage;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author perperon
 * @date 2023/5/19
 * @apiNote
 */
public abstract class BaseController<T> {

    @Value(value = "${serverConfig.uploadPath}")
    private String uploadPath;
    @Value(value = "${serverConfig.attach}")
    private String attach;


    public abstract BaseService<T> getService();

    @GetMapping(value = "/listByPage")
    @ApiOperation("分页查询列表")
    @ResponseBody
    @PreAuthorize("@ps.hasPerm('*:list')")
    public CommonResult<CommonPage<T>> listByPage(@RequestParam Map<String,Object> params){
        return CommonResult.success(CommonPage.restPage(getService().listByPage(params)));
    }

    @PostMapping("/create")
    @ApiOperation("添加")
    @PreAuthorize("@ps.hasPerm('*:add')")
    public CommonResult<T> create(@RequestBody T obj){
        return getService().create(obj);
    }

    @PutMapping("/update")
    @ApiOperation("更新")
    @PreAuthorize("@ps.hasPerm('*:update')")
    public CommonResult<T> update(@RequestBody T obj){
        return getService().update(obj);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @PreAuthorize("@ps.hasPerm('*:delete')")
    public CommonResult<T> delete(@RequestBody T obj){
        return getService().delete(obj);
    }

    @DeleteMapping("/deleteBatches")
    @ApiOperation("批量删除")
    @PreAuthorize("@ps.hasPerm('*:delete')")
    public CommonResult<T> deleteBatches(@RequestBody String[] ids){
        return getService().deleteBatches(ids);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("按主键删除")
    @PreAuthorize("@ps.hasPerm('*:delete')")
    public CommonResult<T> delete(@PathVariable("id") String id){
        return getService().deleteById(id);
    }

    @RequestMapping("/getListByPage")
    @ApiOperation("分页查询列表（@RequestParam）")
    @ResponseBody
    @PreAuthorize("@ps.hasPerm('*:list')")
    public CommonResult<CommonPage<T>> getListByPage(@RequestParam Map<String,Object> params){
        return CommonResult.success(CommonPage.restPage(getService().getListByPage(params)));
    }

    @PostMapping("/listByObj")
    @ResponseBody
    @ApiOperation("查询列表（T）")
    @PreAuthorize("@ps.hasPerm('*:list')")
    public CommonResult<CommonPage<T>> listByObj(@RequestBody T params){
        return CommonResult.success(CommonPage.restPage(getService().listByObj(params)));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @PreAuthorize("@ps.hasPerm('*:list')")
    public Object getById(@PathVariable("id") String  id) {
        CommonResult<T> obj = getService().getById(id);
        return obj;
    }

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PreAuthorize("@ps.hasPerm('*:upload')")
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        Map<String,Object> resultMap = new HashMap<>();
        if (!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = DateUtils.getCurrenTime() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath + fileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String,Object> data = new HashMap<>();
            data.put("title", fileName);
            data.put("src", attach+"/"+ fileName);
            resultMap.put("data", data);
        }
        return resultMap;
    }
}
