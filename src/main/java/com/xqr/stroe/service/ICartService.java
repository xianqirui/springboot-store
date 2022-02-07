package com.xqr.stroe.service;

import com.xqr.stroe.vo.CartVO;

import java.util.List;

/*购物车*/
public interface ICartService {

    /**
     * 添加商品到购物车
     * @param uid
     * @param pid
     * @param num
     * @param username
     */
    void addToCart(Integer uid,Integer pid,Integer num,String username);

    List<CartVO> getBYOVUid(Integer uid);

    /**
     * 更新用户购物车数据的数量
     * @param cid
     * @param uid
     * @param userName
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid,Integer uid,String userName);
    Integer reduceNum(Integer cid,Integer uid,String userName);
}
