package com.noetic.codetest.util;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
public interface IResponse<T, M> {
    int getCode();

    void setCode(int code);

    M getMessage();

    void setMessage(M message);

    T getData();

    void setData(T data);
}
