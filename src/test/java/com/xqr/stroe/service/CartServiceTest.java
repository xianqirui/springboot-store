package com.xqr.stroe.service;

import com.xqr.stroe.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private ICartService cartService;

    @Test
    public void addNewAddress() {
        cartService.addToCart(9,10000005,10,"鲜其芮");
    }
}