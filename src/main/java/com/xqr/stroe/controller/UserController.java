package com.xqr.stroe.controller;

import com.xqr.stroe.entity.User;
import com.xqr.stroe.service.IUserService;
import com.xqr.stroe.service.exception.InsertException;
import com.xqr.stroe.service.exception.UserNameException;
import com.xqr.stroe.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
//    @ResponseBody //响应json
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
      userService.reg(user);

        return new JsonResult<>(OK);
    }

   /* @RequestMapping("/reg")
//    @ResponseBody //响应json
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UserNameException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e){
            result.setState(1000);
            result.setMessage("注册时产生未知错误");
        }
        return result;
    }*/
    /*约定大于配置：省略大量的配置注解的编写
    * 2。接收数据方式：请求处理方法的参数列表设置为非pojo类型，
    * soringboot直接将参数名和方法的参数名进行比较
    * 相同则自动注入
    * */
    /*用户登录*/
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        //向session对象中绑定数据
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session中绑定的数据
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }
}
