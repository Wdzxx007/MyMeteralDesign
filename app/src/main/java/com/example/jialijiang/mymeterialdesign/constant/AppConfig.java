package com.example.jialijiang.mymeterialdesign.constant;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.example.jialijiang.mymeterialdesign.entity.CompanyEntity;
import com.example.jialijiang.mymeterialdesign.entity.UserInfoEntity;
import com.example.jialijiang.mymeterialdesign.MainActivity;
import com.example.jialijiang.mymeterialdesign.util.SystemUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sean on 15/12/26.
 */
public class AppConfig {
    //Activity储存
    private static Map<String, FragmentActivity> activities
            = new HashMap<String, FragmentActivity>( );

    private static UserInfoEntity userInfo;

    private static List<CompanyEntity> companyEntites;



    public static void addActivity( Context context ) {
        if( context instanceof FragmentActivity ) {
            activities.put( context.getClass( ).getName( ), (FragmentActivity) context );
        }
    }

    public static void removeActivity( Context context ) {
        if( context instanceof FragmentActivity ) {
            activities.remove( context.getClass( ).getName( ) );
        }
    }

    /**
     * 关闭页面
     */
    public static void closePage( Context context ) {
        SystemUtil.saveToken( context.getApplicationContext( ), "" );
        for( Object key : activities.keySet( ) ) {
            FragmentActivity activity = activities.get( key );
            activity.finish( );
        }
        Intent intent = new Intent( context, MainActivity.class );
        context.startActivity( intent );
    }

    public static UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo( UserInfoEntity userInfo ) {
        AppConfig.userInfo = userInfo;
    }

    public static void setUserInfo( UserInfoEntity userInfo, Context context ) {
        AppConfig.userInfo = userInfo;
        //        SystemUtil.saveToken( context, userInfo.getAccess( ).getAccess_token( ) );
        SystemUtil.saveToken( context, userInfo.getAccess( ).getAccess_token( ) );
    }

    public static List<CompanyEntity> getCompanyInfo() {
        return companyEntites;
    }

    public static void setCompanyInfo( List<CompanyEntity> list ) {
        AppConfig.companyEntites = list;
    }

}
