package com.xqr.stroe.config;

import com.xqr.stroe.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/*拦截器的注册*/
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    /*配置拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();
        //配置白名单：存放在List集合中
        List<String> patterns =new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/upload/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/products/**");
        //完成拦截器注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的uirl
                .excludePathPatterns(patterns);

    }
}
