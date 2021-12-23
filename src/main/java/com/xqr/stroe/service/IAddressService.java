package com.xqr.stroe.service;

import com.xqr.stroe.entity.Address;

import java.util.List;

/*收货地址接口业务层*/
public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);

    List<Address> getByuid(Integer uid);

    /**
     * 设置默认收货地址
     * @param aid
     * @param uid
     * @param username
     */
    void setDefault(Integer aid,Integer uid,String username);
}
