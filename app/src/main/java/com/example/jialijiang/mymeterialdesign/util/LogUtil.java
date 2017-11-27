package com.example.jialijiang.mymeterialdesign.util;


import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * logger 封装类
 * Created by sean on 15/10/8.
 */
public class LogUtil {

    private static final String SEAN_TAG = "sean beep";

    /**
     * 是否展示log
     *
     * @param isShow
     */
    public static void initLog( boolean isShow ) {

        if( isShow ) {
            Logger.init( SEAN_TAG ).logLevel( LogLevel.FULL );
        } else {
            Logger.init( SEAN_TAG ).logLevel( LogLevel.NONE );
        }
    }

    public static void v( String message ) {
        Logger.v( message );
    }

    public static void d( Object message ) {
        Logger.d( message );
    }

    public static void d( String message, Object... args ) {
        Logger.d( message, args );
    }

    public static void w( String message, Object... args ) {
        Logger.w( message, args );
    }

    public static void e( String message, Object... args ) {
        Logger.e( message, args );
    }


    public static void json( String json ) {
        Logger.json( json );
    }

    public static void xml( String xml ) {
        Logger.xml( xml );
    }


}
