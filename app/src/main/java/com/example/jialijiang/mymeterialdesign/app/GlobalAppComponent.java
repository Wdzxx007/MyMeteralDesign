package com.example.jialijiang.mymeterialdesign.app;

import android.content.Context;

import com.example.jialijiang.mymeterialdesign.di.AppModule;
import com.example.jialijiang.mymeterialdesign.di.component.AppComponent;
import com.example.jialijiang.mymeterialdesign.di.component.DaggerAppComponent;

/**
 * 全局appComponent
 * Created by laulee on 17/11/22.
 */

public class GlobalAppComponent {

    private volatile static AppComponent appComponent;

    /**
     * 初始化全局AppComponent
     *
     * @param context
     */
    public static void init( Context context ) {
        if( appComponent == null ) {
            synchronized(GlobalAppComponent.class) {
                if( appComponent == null ) {
                    appComponent = DaggerAppComponent.builder( )
                            .appModule( new AppModule( context ) ).build( );
                }
            }
        }
    }

    public static AppComponent getAppComponent() {
        if( appComponent == null ) {
            throw ( new NullPointerException( "GlobalAppComponent必须在application中进行init初始化" ) );
        }
        return appComponent;
    }
}
