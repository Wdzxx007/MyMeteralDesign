package com.laulee.baseframe.utils;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by laulee on 17/10/25.
 * sharedPreferences工具类
 */

public class SharedPreferencesUtil {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String MOBILE_PHONE = "mobile_phone";
    private static Context mContext;

    public static void init( Context context ) {
        mContext = context.getApplicationContext( );
    }

    /**
     * 获得保存数据
     *
     * @param key
     * @param defValue
     * @return
     */
    private static String getString( String key, @Nullable String defValue ) {
        return PreferenceManager.getDefaultSharedPreferences( mContext ).getString( key, defValue );
    }

    /**
     * 保存字符串
     *
     * @param key
     * @param value
     */
    private static void saveString( String key, @Nullable String value ) {
        PreferenceManager.getDefaultSharedPreferences( mContext ).edit( ).putString( key, value )
                .apply( );
    }

    /**
     * 保存token
     *
     * @param value
     */
    public static void saveAccessToken( String value ) {
        saveString( ACCESS_TOKEN, value );
    }

    /**
     * 获得token
     *
     * @return
     */
    public static String getAccessToken() {
        return getString( ACCESS_TOKEN, "" );
    }


    /**
     * 保存手机号
     *
     * @param value
     */
    public static void saveMobilePhone( String value ) {
        saveString( MOBILE_PHONE, value );
    }

    /**
     * 获得手机号
     *
     * @return
     */
    public static String getMobilePhone() {
        return getString( MOBILE_PHONE, "" );
    }


}
