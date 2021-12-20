package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.User;

import java.util.Date;

/*用户模块持久层接口*/
public interface UserMapper{
    /*插入用户数据，返回受影响的函数
    * @Parm user 用户数据
    * @Return 受影响的函数*/
    Integer insert(User user);
    /*
    * 根据用户名返回用户数据
    * */
    User findByUsername(String username);
    //根据用户uid修改用户密码
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);
    //根据用户的id查询用户的数据，如果找到返回对象
    User findByUid(Integer uid);
    /*更新用户数据*/
    Integer updateInfoByUid(User user);
}
