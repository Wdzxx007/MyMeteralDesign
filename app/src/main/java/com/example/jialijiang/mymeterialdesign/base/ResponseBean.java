package com.example.jialijiang.mymeterialdesign.base;

/**
 * Created by zhuxingxing on 17/9/28.
 */

public class ResponseBean<T> {

    private T value;
    private int code;
    private String message;

    public T getValue() {
        return value;
    }
}
