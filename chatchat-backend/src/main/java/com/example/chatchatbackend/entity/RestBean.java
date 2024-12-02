package com.example.chatchatbackend.entity;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

public record RestBean<T>(int code, T data, String message) {
    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,data,"success");
    }
    public static <T> RestBean<T> success(){
        return success(null);
    }
    public static <T> RestBean<T> failure(int code,String message){
        return new RestBean<>(code,null,message);
    }
    public static <T> RestBean<T> unAuthorized(String message){
        return new RestBean<>(401,null,message);
    }
    //使用response.getWrite().write时返回JSON格式数据，直接返回对象自动转为JSON格式
    public String asJSONString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }

}
