package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.mapper.AddressMapper;
import com.xqr.stroe.service.IAddressService;
import com.xqr.stroe.service.exception.AdressCountLimtException;
import com.xqr.stroe.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/*新增收货地址实现类*/
@Service
public class IAddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;
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
