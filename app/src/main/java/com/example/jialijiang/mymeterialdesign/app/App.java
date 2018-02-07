package com.example.jialijiang.mymeterialdesign.app;

import android.content.Context;

import com.laulee.baseframe.app.BaseApplication;

/**
 * 全局application
 * Created by laulee on 17/12/5.
 */

public class App extends BaseApplication {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate( );
        //初始化全局appcomponent
        GlobalAppComponent.init( instance );
        mContext = this;
    }
    // 清除缓存
    public void clear() {

    }

    public static Context getContext() {
        return mContext;
    }
}
