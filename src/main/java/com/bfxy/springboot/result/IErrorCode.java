package com.bfxy.springboot.result;

/**
 * 封装API的错误码
 * Created by lipf on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
