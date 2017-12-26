package com.laulee.baseframe.bean;

/**
 * 返回数据的父类
 * Created by laulee on 17/6/7.
 */

public class ResponseBean<T> {

    private int res;
    private T obj;
    private String resMsg;

    //http 响应码 code定义成功失败
    public int getCode() {
        return res;
    }

    //http 部分是传递boolean类型定义成功失败
    public boolean isError() {
        return res != 1000000;
    }

    public String getMessage() {
        return resMsg;
    }

    public T getValues() {
        return obj;
    }
}
