package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Order;
import com.xqr.stroe.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order = new Order();
        order.setUid(9);
        order.setRecvName("小王");
        order.setRecvPhone("157615646");
        orderMapper.insertOrder(order);
    }
    @Test
    public void insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000001);
        orderItem.setTitle("广博(GuangBo)10本装40张A5牛皮纸记事本子日记本办公软抄本GBR0731");
        orderMapper.insertOrderItem(orderItem);
    }

}
