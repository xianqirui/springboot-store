package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.Cart;
import com.xqr.stroe.mapper.CartMapper;
import com.xqr.stroe.mapper.ProductMapper;
import com.xqr.stroe.service.ICartService;
import com.xqr.stroe.service.exception.AccessDeniedException;
import com.xqr.stroe.service.exception.CartNotFindException;
import com.xqr.stroe.service.exception.InsertException;
import com.xqr.stroe.service.exception.UpdateException;
import com.xqr.stroe.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    /*购物车业务层依赖购物车持久层和商品持久层*/
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid,
                          Integer num, String username) {
            //查询当前要添加的购物车是否在表中已存在
        Cart result = cartMapper.findCartNum(uid, pid);
        Date date = new Date();
        if (result==null){//表示这个商品从来没添加到购物车
            //需要创建cart对象
            Cart cart = new Cart();
            //补全数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);
            //价格：来之商品中的数据
            cart.setPrice(productMapper.findById(pid).getPrice());
            //补全4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            //插入操作
            Integer insert = cartMapper.insert(cart);
            if(insert!=1){
                throw new InsertException("插入数据时产生未知异常");
            }
        }else {//便是当前商品在购物车中已存在，更新数据数量
           Integer amount= result.getNum()+num;
            Integer integer = cartMapper.updateNumByCid(
                    result.getCid(),
                    amount,
                    username,
                    date);
            if (integer!=1){
                throw new UpdateException("更新数据时产生未知异常");
            }
        }
    }

    /**
     * 展示购物车数据
     * @param uid
     * @return
     */
    @Override
    public List<CartVO> getBYOVUid(Integer uid) {

        return cartMapper.findBYUid(uid);
    }
    //增加
    @Override
    public Integer addNum(Integer cid, Integer uid, String userName) {
        Cart result = cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFindException("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num=result.getNum()+1;
        Integer rows = cartMapper.updateNumByCid(cid, num, userName, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的数量
        return num;
    }

    //减少
    @Override
    public Integer reduceNum(Integer cid, Integer uid, String userName) {
        Cart result = cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFindException("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num=result.getNum()-1;
        Integer rows = cartMapper.updateNumByCid(cid, num, userName, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的数量
        return num;
    }

}
