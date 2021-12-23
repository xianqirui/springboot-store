package com.xqr.stroe.service;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.entity.User;
import com.xqr.stroe.service.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private IAddressService addressService;

    /*
      1.必须被@Test注解修饰
      2.返回值类型不许是void
      3.方法的参数列表不指定任何类型
      4.方法的访问修饰符必须是public
    * */
    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("9991211");
        address.setName("女朋友");
        addressService.addNewAddress(9,"管理员",address);
    }
    @Test
    public  void setDefault(){
        addressService.setDefault(5,9,"鲜啥啥");
    }
}