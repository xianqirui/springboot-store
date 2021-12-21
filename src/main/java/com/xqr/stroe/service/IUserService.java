package com.xqr.stroe.service;

import com.xqr.stroe.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/*用户模块业务层接口*/
public interface IUserService {
    //注册方法
    void reg(User user);
    /*
    * 用户登录功能，返回用户数据
    * */
    User login(String username,String password);

    /**
     *
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePasswword(Integer uid,String username,
                         String oldPassword,String newPassword);
    /*查询用户对象*/
   User getByUid(Integer uid);

   /*更新用户数据*/
    /**
     *
     * @param uid
     * @param username
     * @param user
     */
   void changeInfo(Integer uid,String username,User user);
   //更新用户头像
    /**
     * 修改用户头像
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param username 修改者名称
     * @return
     */
   void updateAvatarByUid(Integer uid,
                             String avatar,
                             String username);

}
