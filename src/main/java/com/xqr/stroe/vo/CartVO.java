package com.xqr.stroe.vo;

import lombok.Data;

import java.io.Serializable;
/* 购物车VO类*/
@Data
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String image;
    private String title;
    private Long realPrice;
}
