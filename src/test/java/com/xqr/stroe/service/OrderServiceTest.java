package com.xqr.stroe.service;

import com.xqr.stroe.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create(){
        Integer [] cids={3,4,5};
        Order order = orderService.create(17, 9, "小王", cids);
        System.out.println(order);
    }
}
