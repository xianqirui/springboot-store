package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.entity.Cart;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CartressMapperTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(9);
        cart.setPid(10000005);
        cart.setNum(2);
        cart.setPrice(1000l);
        cartMapper.insert(cart);
    }
    @Test
    public void changeNUm(){
        cartMapper.updateNumByCid(1,4,"管理员",new Date());
    }
    @Test
    public void find(){
        System.out.println(cartMapper.findCartNum(9,10000005));
    }


}

