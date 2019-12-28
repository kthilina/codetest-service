package com.noetic.codetest.util;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
public class CodetestResponseMapper<T, M> {
    public static <T, M> void build(final IResponse iResponse, final int code, final T object, final M message) {
        iResponse.setCode(code);
        iResponse.setData(object);
        iResponse.setMessage(message);
    }
}
