package com.example.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class R {
    private static final Integer SUCCESS = 1;
    private static final Integer ERROR = 0;

    private Integer code;
    private String message;
    private HashMap<String, Object> data = new HashMap<>();

    public static R ok(){
        R r = new R();
        r.setCode(SUCCESS);
        r.setMessage("请求成功！！！");
        return r;
    }

    public static R fail(){
        R r = new R();
        r.setCode(ERROR);
        r.setMessage("请求失败！！！");
        return r;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R data(String key,Object value){
        this.getData().put(key,value);
        return this;
    }
}
