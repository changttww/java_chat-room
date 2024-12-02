package com.example.demo.common.response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultBody<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public ResultBody(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultBody<T> success() {
        return new ResultBody<T>(ErrorCode.SUCCESS.getCode(), "successfully");
    }

    public static <T> ResultBody<T> success(T data) {
        return new ResultBody<T>(ErrorCode.SUCCESS.getCode(), "successfully", data);
    }

    public static <T> ResultBody<T> error(int code, String msg) {
        return new ResultBody<>(code, msg);
    }

    public static <T> ResultBody<T> error(String msg) {
        return new ResultBody<>(ErrorCode.ERROR.getCode(), msg);
    }

    public static <T> ResultBody<T> error() {
        return new ResultBody<>(ErrorCode.ERROR.getCode(), ErrorCode.ERROR.getMsg());
    }

}