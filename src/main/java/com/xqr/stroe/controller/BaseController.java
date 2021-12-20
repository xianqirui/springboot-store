package com.xqr.stroe.controller;

import com.xqr.stroe.service.exception.*;
import com.xqr.stroe.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/*变数控制层类的基类：处理异常*/
public class BaseController {
    /*声明操作成功状态码*/
    public static  final int OK=200;

    //请求处理方法，这个方法的返回值是需要返回给前端的
    //自动将异常对象窗体给该方法的参数列表
    //项目中产生的异常会被拦截到此方法
    @ExceptionHandler(ServiceException.class) //用于统一处理抛出的异常
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result = new JsonResult<>();
        if(e instanceof UserNameException){
            result.setState(4000);
            result.setMessage("用户名已被占用");
        }else if (e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据没找到");
        }else if (e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户密码错误的异常");
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof UpdateException){
            result.setState(5002);
            result.setMessage("更新数据时产生未知异常");
        }
        return  result;
    }
    /*
    * 获取session对象中的uid
    * */
    protected final Integer getuidFromSession(HttpSession session){

        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    /*获取session中的username
    * 在实现类中重写父类中的toString()，不是句柄信息输出
    * */
    protected final String getUsernameFromSession(HttpSession session)
    {
        return session.getAttribute("username").toString();
    }
}
