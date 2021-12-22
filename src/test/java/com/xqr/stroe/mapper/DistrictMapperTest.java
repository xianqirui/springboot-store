package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictMapperTest {
    //idea有检测功能,接口实不能直接创建Bean(底层对应动态代理)
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> parent = districtMapper.findByParent("110100");
        System.out.println(parent);

    }
    @Test
    public void findByCode(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }

}
