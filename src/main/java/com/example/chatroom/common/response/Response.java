package com.example.chatroom.common.response;

public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法创建常用响应
    public static <T> Response<T> success(String message, T data) {
        return new Response<>(200, message, data);  // 修改了返回的状态码为 200
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(500, message, null);  // 错误响应，状态码为 500
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    // Getters and setters
    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
