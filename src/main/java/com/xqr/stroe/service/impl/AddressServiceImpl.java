package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.mapper.AddressMapper;
import com.xqr.stroe.service.IAddressService;
import com.xqr.stroe.service.IDistrictService;
import com.xqr.stroe.service.exception.AdressCountLimtException;
import com.xqr.stroe.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/*新增收货地址实现类*/
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    //在添加用户收货地址业务层依赖于IDistrictService接口。
    @Autowired
    private IDistrictService districtService;
    /**
     *
     * @param uid
     * @param username
     * @param address
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收货地址统计
        Integer count = addressMapper.countByUid(uid);
        if (count>=maxCount){
            throw new AdressCountLimtException("用户收货地址已经达到上线");
        }

        //对address对象中的数据进行补全：省市区
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //uid.isDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1:0; //1表示默认
        address.setIsDefault(isDefault);
        //补全四项日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        //插入收货地址方法
        Integer rows = addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户收货地址时出现未知异常");
        }
    }
}
