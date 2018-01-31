package com.example.jialijiang.mymeterialdesign.app;

import com.laulee.baseframe.app.BaseApplication;

/**
 * 全局application
 * Created by laulee on 17/12/5.
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate( );
        //初始化全局appcomponent
        GlobalAppComponent.init( instance );
    }
}
