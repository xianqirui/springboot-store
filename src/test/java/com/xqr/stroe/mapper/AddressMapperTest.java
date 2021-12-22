package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AddressMapperTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private AddressMapper addressMapper;

    /*
      1.必须被@Test注解修饰
      2.返回值类型不许是void
      3.方法的参数列表不指定任何类型
      4.方法的访问修饰符必须是public
    * */
    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(9);
        address.setPhone("1211");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer integer = addressMapper.countByUid(9);
        System.out.println(integer);
    }
}
