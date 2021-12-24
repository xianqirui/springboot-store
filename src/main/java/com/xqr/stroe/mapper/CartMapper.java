package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Cart;
import lombok.Data;

import java.util.Date;

public interface CartMapper {
    /**
     * 购物车插入数据
     * @param cart
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid 购物车数据id
     * @param num 更新的数量
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNumByCid(Integer cid, Integer num,
                           String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id，商品id查询购物车中的数据
     * @param uid
     * @param pid
     * @return
     */
    Cart findCartNum(Integer uid,Integer pid);
}
