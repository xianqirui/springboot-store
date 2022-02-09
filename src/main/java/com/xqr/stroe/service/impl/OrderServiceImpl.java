package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.entity.Order;
import com.xqr.stroe.entity.OrderItem;
import com.xqr.stroe.mapper.OrderMapper;
import com.xqr.stroe.service.IAddressService;
import com.xqr.stroe.service.ICartService;
import com.xqr.stroe.service.IOrderService;
import com.xqr.stroe.service.exception.InsertException;
import com.xqr.stroe.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;


    @Override
    public Order create(Integer aid, Integer uid, String userName, Integer[] cids) {
        List<CartVO> list = cartService.getBYVOcid(cids, uid);
        //计算商品总价
        Long totalPrice=0L;

        for (CartVO cartVO:list){
            totalPrice+=cartVO.getRealPrice() *cartVO.getNum();

        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);
        //收货地址数据
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        //支付，总价
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        //日志
        order.setCreatedUser(userName);
        order.setCreatedTime(new Date());
        order.setModifiedUser(userName);
        order.setModifiedTime(new Date());

        //插入数据
        Integer integer = orderMapper.insertOrder(order);
        if (integer!=1){
            throw new InsertException("插入时异常");
        }
        //创建订单详细项
        for (CartVO cartVO:list){
            //创建订单项对象
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getUid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            orderItem.setCreatedUser(userName);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(userName);
            orderItem.setModifiedTime(new Date());
            //插入数据
            Integer integer1 = orderMapper.insertOrderItem(orderItem);
            if(integer1!=1){
                throw new InsertException("插入数据异常");
            }
        }


        return order;
    }
}
