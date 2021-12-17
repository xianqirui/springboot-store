package com.xqr.stroe.controller;

import com.xqr.stroe.service.exception.InsertException;
import com.xqr.stroe.service.exception.ServiceException;
import com.xqr.stroe.service.exception.UserNameException;
import com.xqr.stroe.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }
        return  result;
    }
}
