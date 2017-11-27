package com.example.jialijiang.mymeterialdesign.base;


import org.json.JSONObject;

/**
 * 基类参数接口
 * Created by sean on 16/11/17.
 */

public interface IBaseParams {

    JSONObject generateParams();

    String generateSign( JSONObject json );
}
