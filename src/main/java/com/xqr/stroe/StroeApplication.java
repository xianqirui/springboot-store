package com.xqr.stroe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@Configuration//配置类
@SpringBootApplication
//扫描指定包下的mapper接口
@MapperScan("com.xqr.stroe.mapper")
public class StroeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StroeApplication.class, args);
    }
    @Bean
    public MultipartConfigElement getMultipart(){
        //创建一个配置的工厂类对象
        MultipartConfigFactory factory=new MultipartConfigFactory();
        //设置需要创建的对象的相关信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));
        //创建MultipartConfigElement对象
        return factory.createMultipartConfig();
    }

}
