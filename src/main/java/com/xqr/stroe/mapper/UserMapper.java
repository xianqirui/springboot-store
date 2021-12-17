package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.User;

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
}
