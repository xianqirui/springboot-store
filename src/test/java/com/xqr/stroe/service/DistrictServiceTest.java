package com.xqr.stroe.service;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictServiceTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent() {
        //86表示中国
        List<District> parent = districtService.getByParent("86");
        for (District d : parent) {
            System.err.println(d);
        }
    };
}