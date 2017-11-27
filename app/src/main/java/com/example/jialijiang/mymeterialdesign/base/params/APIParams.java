package com.example.jialijiang.mymeterialdesign.base.params;

import com.example.jialijiang.mymeterialdesign.base.IBaseParams;

import org.json.JSONObject;


/**
 * Created by jialijiang on 17/5/15.
 */

public class APIParams implements IBaseParams{

    public String method = "config";
    public String STRING = "java.lang.String";
    public String INT = "int";
    public String source = "201";
    public String TIME = "time";
    public String LANG = "lang";
    public String lang = "zh_CN";
    public String PARAMS = "params";
    public String SIGN = "sign";
    public String SOURCE = "source";

    public String VERSION = "version";
    public String DEVICE_UDID = "device-udid";
    public String app_key = "95cd002b3b4df2e4d3fc085301847ae0";

    @Override
    public JSONObject generateParams() {
        return null;
    }

    @Override
    public String generateSign(JSONObject json) {
        return null;
    }

}
