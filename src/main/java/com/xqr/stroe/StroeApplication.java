package com.xqr.stroe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描指定包下的mapper接口
@MapperScan("com.xqr.stroe.mapper")
public class StroeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StroeApplication.class, args);
    }

}
