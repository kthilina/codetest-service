package com.noetic.codetest.util;

import lombok.Data;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@Data
public class CodetestResponse<T, M> implements IResponse<T, M> {
    private int code;
    private M message;
    private T data;
}
