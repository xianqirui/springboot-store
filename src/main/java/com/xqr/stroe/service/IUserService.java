package com.xqr.stroe.service;

import com.xqr.stroe.entity.User;

/*用户模块业务层接口*/
public interface IUserService {
    //注册方法
    void reg(User user);
    /*
    * 用户登录功能，返回用户数据
    * */
    User login(String username,String password);
    void changePasswword(Integer uid,String username,
                         String oldPassword,String newPassword);
    /*查询用户对象*/
   User getByUid(Integer uid);
   /*更新用户数据*/
   void changeInfo(Integer uid,String username,User user);
}
