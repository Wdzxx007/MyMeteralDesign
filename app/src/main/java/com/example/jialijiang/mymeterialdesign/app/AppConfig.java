package com.example.jialijiang.mymeterialdesign.app;

import android.app.Activity;

import com.example.jialijiang.mymeterialdesign.MainActivity;
import com.laulee.baseframe.app.ActivityManager;
import com.laulee.baseframe.utils.SharedPreferencesUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by sean on 15/12/26.
 */
public class AppConfig {
    //用户信息
//    private static UserInfoEntity userInfo;

    //个人中心
//    private static UserCenterEntity userCenter;

    //umeng 推送device token
    private static String umengDeviceToken;
//
//    public static UserInfoEntity getUserInfo() {
//        return userInfo;
//    }
//    public static UserCenterEntity getUseCenter() {
//        return userCenter;
//    }

//    public static void setUserInfo( UserInfoEntity userInfo ) {
//        AppConfig.userInfo = userInfo;
//    }


//    public static void setUserCenter( UserCenterEntity userCenter ) {
//        AppConfig.userCenter = userCenter;
//    }


    public static String getUmengDeviceToken() {
        return umengDeviceToken;
    }


    public static void setUmengDeviceToken( String umengDeviceToken ) {
        AppConfig.umengDeviceToken = umengDeviceToken;
    }

    /**
     * 关闭页面
     */
    public static void closePage() {
        Set<Activity> activitySet = ActivityManager.getAllActivities( );
        Iterator<Activity> iter = activitySet.iterator( ); while( iter.hasNext( ) ) {
            Activity activity = iter.next( );
            if( !activity.getClass( ).getName( ).equals( MainActivity.class.getName( ) ) ) {
                activity.finish( );
            }
        } SharedPreferencesUtil.saveAccessToken( "" );
//        AppConfig.setUserInfo( null );
    }
}
