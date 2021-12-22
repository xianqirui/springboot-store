package com.xqr.stroe.controller;

import com.xqr.stroe.entity.District;
import com.xqr.stroe.service.IDistrictService;
import com.xqr.stroe.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/districts")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
