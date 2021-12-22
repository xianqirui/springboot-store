package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent
     * @return List<District>
     */
    List<District> findByParent(String parent);
}
