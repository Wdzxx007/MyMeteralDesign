package com.laulee.baseframe.app;

import android.app.Application;

import com.laulee.baseframe.BuildConfig;
import com.laulee.baseframe.utils.LogUtil;
import com.laulee.baseframe.utils.SharedPreferencesUtil;
import com.laulee.baseframe.utils.ToastUtil;

/**
 * 基类application
 * Created by laulee on 17/11/9.
 */

public class BaseApplication extends Application {

    protected static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate( );
        instance = this;
        //log
        LogUtil.initLog( BuildConfig.DEBUG, this );
        //初始化Toast
        ToastUtil.getInstance( this );
        //初始化SharedPreferencesUtil
        SharedPreferencesUtil.init( instance );
    }
}
