package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.mapper.AddressMapper;
import com.xqr.stroe.service.IAddressService;
import com.xqr.stroe.service.IDistrictService;
import com.xqr.stroe.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Address> getByuid(Integer uid) {
        List<Address> result = addressMapper.findByUid(uid);
        for (Address address : result) {
            //address.setAid(null);
            //address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
            address.setTel(null);
        }
        return result;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的地址数据归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        //先将所有收货地址设置为非默认
        Integer integer = addressMapper.updateNonDefault(uid);
        if (integer<1){
            throw new UpdateException("更新时发生未知异常1");
        }
        Integer integer1 = addressMapper.updateDefault(aid, username, new Date());
        if (integer1!=1){
            throw new UpdateException("更新时发生未知异常");
        }
    }

    /**
     * 删除操作
     * @param aid
     * @param uid
     * @param username
     */
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的地址数据归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer integer = addressMapper.deleteByAid(aid);
        if (integer!=1){
            throw new DeleteException("删除时未知异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if (count==0){
            //终止程序
            return;
        }

        if (result.getIsDefault()==1){
            //将这个数据设为默认地址
            Address address = addressMapper.findLastModified(uid);
            Integer integer1 = addressMapper.updateDefault(address.getAid(), username, new Date());
            if (integer1!=1){
                throw new UpdateException("更新数据时产生未知异常");
            }
        }


    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("地址没找到");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        address.setCreatedTime(null);
        return address;
    }

}
