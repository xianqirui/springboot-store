package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private UserMapper userMapper;
    /*
      1.必须被@Test注解修饰
      2.返回值类型不许是void
      3.方法的参数列表不指定任何类型
      4.方法的访问修饰符必须是public
    * */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }
    @Test
    public void find(){
        User tim = userMapper.findByUsername("tim");
        System.out.println(tim);
    }
}
