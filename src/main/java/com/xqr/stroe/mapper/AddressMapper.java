package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Address;

import java.util.Date;
import java.util.List;

/*收货地址持久层接口*/
public interface AddressMapper {
    /*插入用户收货地址*/
    Integer insert(Address address);

    /*统计行数*/
    Integer countByUid(Integer uid);

    /**
     * 根据用户id查询用户收货地址数据
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    /**
     * 查询收货地址数据
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    Integer updateNonDefault(Integer uid);
    Integer updateDefault(Integer aid, String modifiedUser, Date modifiedTime);

    /*删除操作*/
    Integer deleteByAid(Integer aid);
    Address findLastModified(Integer uid);

}
