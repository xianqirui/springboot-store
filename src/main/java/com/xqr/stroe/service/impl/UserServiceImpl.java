package com.xqr.stroe.service.impl;

import com.xqr.stroe.entity.User;
import com.xqr.stroe.mapper.UserMapper;
import com.xqr.stroe.service.IUserService;
import com.xqr.stroe.service.exception.*;
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
    /*用户登录*/
    @Override
    public User login(String username, String password) {
        //依据用户名查询用户是否存在
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户密码是否匹配
        //1.先获取数据库中加密后的密码
        String oldPassword = result.getPassword();
        //2和用户传递过来的密码进行比较
        //2.1先获取盐值
        String salt = result.getSalt();
        //2.2将用户密码按照相同MD5算法加密
        String newMd5password=getMDPassword(password,salt);
        //3.将密码进行比较
        if (!newMd5password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断is_delete是否为1表示被标记删除
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //调用mapper层方法查询用户数据，（减少数据传输）提升了系统性能。
        User user =new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        //返回的user对象，是为了辅助其他页面展示用的
        return user;
    }

    @Override
    public void changePasswword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据未找到");
        }
        //原始密码和数据库密码比较
        String oldMd5password = getMDPassword(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5password)){
            throw new PasswordNotMatchException("密码错误");
        }
        //将新的密码设置到数据库中,将新的密码进行加密再去更新
        String newmd5Password = getMDPassword(newPassword, result.getSalt());
        //更新
        Integer rows = userMapper.updatePasswordByUid(uid, newmd5Password, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据产生未知异常");
        }
    }
    //查询数据
    @Override
    public User getByUid(Integer uid) {
        User byUid = userMapper.findByUid(uid);
        if(byUid==null||byUid.getIsDelete()==1){
            throw new UserNotFoundException("用户数据未找到");
        }
        User user = new User();
        user.setUsername(byUid.getUsername());
        user.setPhone(byUid.getPhone());
        user.setEmail(byUid.getEmail());
        user.setGender(byUid.getGender());
        return user;
    }
    //更新数据
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User byUid = userMapper.findByUid(uid);
        if(byUid==null||byUid.getIsDelete()==1){
            throw new UserNotFoundException("用户数据未找到");
        }
        user.setUid(uid);
        //user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows!=1){
            throw new UpdateException("更新数据产生未知异常");
        }
    }

    /**
     * 修改用户头像
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param username 修改者名称
     */
    @Override
    public void updateAvatarByUid(Integer uid, String avatar, String username) {
        //查询当前用户数据是否存在
        User byUid = userMapper.findByUid(uid);
        if(byUid==null||byUid.getIsDelete()==1){
            throw new UserNotFoundException("用户数据未找到");
        }
        Integer integer = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (integer!=1){
            throw new UpdateException("更新数据产生未知异常");
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
