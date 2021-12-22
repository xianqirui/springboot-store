package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.District;
import com.xqr.stroe.mapper.DistrictMapper;
import com.xqr.stroe.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*实现类*/
@Service
public class IDistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        /*
        * 进行网络数据传输时为了尽量避免无效数据的传递，
        * 可以将无效数据设置为null，节省流量,提升性能
        * */
        for (District d : list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }
}
