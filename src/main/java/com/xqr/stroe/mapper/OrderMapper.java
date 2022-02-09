package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Order;
import com.xqr.stroe.entity.OrderItem;

/*订单持久层*/
public interface OrderMapper {
    /*订单*/
    Integer insertOrder(Order order);
    /*订单项*/
    Integer insertOrderItem(OrderItem orderItem);
}
