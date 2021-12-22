package com.xqr.stroe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*省市区列表实体类*/
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class District extends BaseEntity{
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
