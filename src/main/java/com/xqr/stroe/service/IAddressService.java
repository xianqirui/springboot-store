package com.xqr.stroe.service;

import com.xqr.stroe.entity.Address;

/*收货地址接口业务层*/
public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);
}
