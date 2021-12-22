package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Address;

/*收货地址持久层接口*/
public interface AddressMapper {
    /*插入用户收货地址*/
    Integer insert(Address address);

    /*统计行数*/
    Integer countByUid(Integer uid);
}
