package com.xqr.stroe.util;

import lombok.Data;

import java.io.Serializable;

@Data
/*json格式响应给前端*/
public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    /*数据类型不确定*/
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    /*public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }*/

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(String message, E data) {
        this.message = message;
        this.data = data;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
}
