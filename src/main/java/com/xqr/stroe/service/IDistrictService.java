package com.xqr.stroe.service;

import com.xqr.stroe.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父区域对应的代号查询信息
     * @param Parent
     * @return
     */
    List<District> getByParent(String Parent);

    String getNameByCode(String code);
}
