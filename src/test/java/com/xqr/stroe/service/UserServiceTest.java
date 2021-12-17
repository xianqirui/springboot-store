package com.xqr.stroe.service;

import com.xqr.stroe.entity.User;
import com.xqr.stroe.mapper.UserMapper;
import com.xqr.stroe.service.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.rowset.serial.SerialException;

@SpringBootTest
public class UserServiceTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private IUserService userService;
    /*
      1.必须被@Test注解修饰
      2.返回值类型不许是void
      3.方法的参数列表不指定任何类型
      4.方法的访问修饰符必须是public
    * */
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("xianqirui");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            //获取类的对象，获取类名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的描述信息
            System.out.println(e.getMessage());
        }
    }
}
