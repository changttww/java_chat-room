package com.example.demo.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: kevin
 * @date: 2020/9/25
 * @time: 下午10:03
 * @description: Server response code
 **/

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /**
     * server response code
     */
    SUCCESS(200, "Success"),
    ERROR(0, "Error"),
    ACCESS_DENIED(2, "Access denied"),
    ;

    public int code;
    public String msg;
}
