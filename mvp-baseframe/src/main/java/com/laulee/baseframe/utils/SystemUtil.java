package com.laulee.baseframe.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.regex.Pattern;

/**
 * Created by laulee on 17/3/1.
 */

public class SystemUtil {
    private static final String APP_SAVE = "appSave";
    private static final String DEVICE_DENSITY = "deviceDensity";
    private static final String DEVICE_WIDTH = "deviceWidth";
    private static final String DEVICE_HEIGHT = "deviceHeight";
    private static String deviceId;
    private static float deviceDensity = -1;
    private static int deviceWidth = -1;
    private static int deviceHeight = -1;
    private static final String CARD_ID_ERROR = "银行卡号不正确";

    /**
     * 检测网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected( Context context ) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getApplicationContext( ).getSystemService( Context.CONNECTIVITY_SERVICE );
        return connectivityManager.getActiveNetworkInfo( ) != null;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px( Context context, float dpValue ) {
        final float scale = context.getResources( ).getDisplayMetrics( ).density;
        return (int) ( dpValue * scale + 0.5f );
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp( Context context, float pxValue ) {
        final float scale = context.getResources( ).getDisplayMetrics( ).density;
        return (int) ( pxValue / scale + 0.5f );
    }

    /**
     * 获取设备唯一ID
     *
     * @param context
     * @return
     */
    public static String getDeviceOnlyId( Context context ) {
        if( deviceId != null ) {
            return deviceId;
        } else {
            TelephonyManager TelephonyMgr = (TelephonyManager) context
                    .getSystemService( Context.TELEPHONY_SERVICE );
            deviceId = TelephonyMgr.getDeviceId( );
            return deviceId;
        }
    }


    /**
     * 获取app版本
     *
     * @param context
     * @return
     */
    public static String getAPPVersion( Context context ) {
        String version = "";
        try {
            PackageManager manager = context.getPackageManager( );
            PackageInfo info = manager.getPackageInfo( context.getPackageName( ), 0 );
            version = info.versionName;
            return version;
        } catch( Exception e ) {
            e.printStackTrace( );
            return version;
        }
    }

    /**
     * 检查手机号
     *
     * @param phoneNum 手机号码
     * @return
     */
    public static boolean checkPhoneNum( String phoneNum ) {
        String regex = "^1[0-9]{10}$";
        Pattern p = Pattern.compile( regex );
        if( !p.matcher( phoneNum ).matches( ) ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public static boolean checkLoginState() {
        return !TextUtils.isEmpty( SharedPreferencesUtil.getAccessToken( ) );
    }

    /**
     * 获取设备dp值
     *
     * @param context
     * @return
     */
    public static float getDeviceDensity( Context context ) {
        SharedPreferences sp = context.getSharedPreferences( APP_SAVE, Context.MODE_PRIVATE );
        deviceDensity = sp.getFloat( DEVICE_DENSITY, -1 );
        WindowManager windowManager = (WindowManager) context
                .getSystemService( Context.WINDOW_SERVICE );
        DisplayMetrics dm = new DisplayMetrics( );
        windowManager.getDefaultDisplay( ).getMetrics( dm );
        deviceDensity = dm.density;
        deviceWidth = dm.widthPixels;
        deviceHeight = dm.heightPixels;

        SharedPreferences.Editor editor = sp.edit( );
        editor.putFloat( DEVICE_DENSITY, deviceDensity );
        editor.putInt( DEVICE_WIDTH, deviceWidth );
        editor.putInt( DEVICE_HEIGHT, deviceHeight );
        editor.commit( );
        return deviceDensity;
    }

    /**
     * 获取设备宽度
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth( Context context ) {
        SharedPreferences sp = context.getSharedPreferences( APP_SAVE, Context.MODE_PRIVATE );
        deviceWidth = sp.getInt( DEVICE_WIDTH, -1 );
        WindowManager windowManager = (WindowManager) context
                .getSystemService( Context.WINDOW_SERVICE );
        DisplayMetrics dm = new DisplayMetrics( );
        windowManager.getDefaultDisplay( ).getMetrics( dm );
        deviceDensity = dm.density;
        deviceWidth = dm.widthPixels;
        deviceHeight = dm.heightPixels;

        SharedPreferences.Editor editor = sp.edit( );
        editor.putFloat( DEVICE_DENSITY, deviceDensity );
        editor.putInt( DEVICE_WIDTH, deviceWidth );
        editor.putInt( DEVICE_HEIGHT, deviceHeight );
        editor.commit( );
        return deviceWidth;
    }

    /**
     * 获取用户设备高度
     *
     * @param context
     * @return
     */
    public static int getDeviceHeight( Context context ) {
        SharedPreferences sp = context.getSharedPreferences( APP_SAVE, Context.MODE_PRIVATE );
        deviceHeight = sp.getInt( DEVICE_HEIGHT, -1 );
        if( deviceHeight < 0 && context instanceof Activity ) {
            DisplayMetrics dm = new DisplayMetrics( );
            ( (Activity) context ).getWindowManager( ).getDefaultDisplay( ).getMetrics( dm );
            deviceDensity = dm.density;
            deviceWidth = dm.widthPixels;
            deviceHeight = dm.heightPixels;

            SharedPreferences.Editor editor = sp.edit( );
            editor.putFloat( DEVICE_DENSITY, deviceDensity );
            editor.putInt( DEVICE_WIDTH, deviceWidth );
            editor.putInt( DEVICE_HEIGHT, deviceHeight );
            editor.commit( );
            return deviceHeight;
        } else {
            return deviceHeight;
        }
    }

    /**
     * 拨打电话
     *
     * @param number  电话号码
     * @param context
     */
    public static void phoneCall( String number, Context context ) {
        Intent intent = new Intent( Intent.ACTION_CALL, Uri.parse( "tel:" + number ) );
        if( context instanceof Activity )
            context.startActivity( intent );
    }

    /**
     * 隐藏手机号
     * @param phone
     * @return
     */
    public static String hidePhone( String phone ) {
        if( checkPhoneNum( phone ) ) {
            StringBuffer stringBuffer = new StringBuffer( );
            stringBuffer.append( phone.substring( 0, 3 ) );
            stringBuffer.append( "****" );
            stringBuffer.append( phone.substring( 7, phone.length( ) ) );
            return stringBuffer.toString( );
        } else {
            return phone;
        }
    }

    /**
     * 检查银行卡是否合理
     *
     * @param cardId
     * @param context
     * @return
     */
    public static boolean checkBankCard(String cardId, Context context) {
        if (cardId.length() > 15 && cardId.length() < 20) {
            return true;
        } else {
            ToastUtil.showToast(CARD_ID_ERROR);
            return false;
        }
    }
    /**
     * 隐藏银行卡
     *
     * @param bankCardNum 银行卡号
     * @param context
     * @return
     */
    public static String hideBankCardNum(String bankCardNum, Context context) {
        if (checkBankCard(bankCardNum, context)) {

            StringBuffer sb = new StringBuffer();
            sb.append(bankCardNum.substring(0,4));
            sb.append("    ");
            sb.append("****");
            sb.append("   ");
            sb.append("****");
            sb.append("    ");
            if (bankCardNum.length() % 2 == 0) {
                sb.append(
                        bankCardNum.substring(bankCardNum.length() - 4, bankCardNum.length()));
            } else if (bankCardNum.length() % 2 == 1) {
                sb.append(
                        bankCardNum.substring(bankCardNum.length() - 3, bankCardNum.length()));
            }
            return sb.toString();
        } else {
            return bankCardNum;
        }
    }

    /**
     * 隐藏用户姓名
     *
     * @param name
     * @return
     */
    public static String hideUserName( String name ) {
        StringBuffer stringBuffer = new StringBuffer( );
        if( !TextUtils.isEmpty( name ) ) {
            int length = name.length( );
            for( int i = 0; i < length; i++ ) {
                if( i == 0 || ( i == length - 1 && length > 2 ) )
                    stringBuffer.append( name.charAt( i ) );
                else
                    stringBuffer.append( "*" );
            }
            return stringBuffer.toString( );
        } else
            return "";
    }
}
