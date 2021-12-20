package com.xqr.stroe.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    /*检测全局对象session中是否有uid数据*/
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //HttpServletRequest对象获取session对象
        Object uid = request.getSession().getAttribute("uid");
        if(uid==null){//用户没登陆过,重定向到login.html
            //response.getWriter().write("没有登录");
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //放行
        return true;
    }
}
