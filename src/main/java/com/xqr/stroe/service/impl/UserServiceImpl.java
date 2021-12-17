package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.User;
import com.xqr.stroe.mapper.UserMapper;
import com.xqr.stroe.service.IUserService;
import com.xqr.stroe.service.exception.InsertException;
import com.xqr.stroe.service.exception.UserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/*用户模块业务层实现类*/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //调用findByUsername判断用户是否已经注册了
        User result = userMapper.findByUsername(user.getUsername());
        //判断结果集是否为null,部位null抛出异常
        if(result!=null){
            //抛异常
            throw new UserNameException("用户名被占用");
        }
        //密码加密：md5
        //盐值+passwor+盐值----》md5加密三次-----》密码
        String oldPassword=user.getPassword();
        //盐值（随机生成）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //记录盐值,方便后面登录
        user.setSalt(salt);
        //将密码和盐值拼接进行加密
        String md5Password = getMDPassword(oldPassword, salt);
        //将加密后的密码不全到user对象中
        user.setPassword(md5Password);
        //数据补全
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(new Date());
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());

        //执行注册功能
        Integer insert = userMapper.insert(user);
        if(insert!=1){
            throw new InsertException("在用户注册过程中产生了未知错误");
        }
    }
    //md5算法加密
    private String getMDPassword(String password,String salt){
        //md5加密算法调用(进行三次加密)
        for (int i = 0; i < 3; i++) {
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        //返回加密后的password
        return password;
    }
}
